package com.bigdata.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction, UserDefinedFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL05_UDAF {
  // DataFrame SQL查询语法
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Spark"))
    // TODO SparkSession内部初始化函数是私有的，无法直接new出来，只能通过函数创建
    val se: SparkSession = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate()
    // TODO RDD、DataFrame、DataSet三者间的转换需要导入该对象的 implicits 方法
    import se.implicits._
    sc.makeRDD(List((1, "zhangsan", 18L), (2, "lisi", 19L), (3, "wangwu", 20L))).toDF("id","name","age").createOrReplaceTempView("user")
    val udaf = new MyAverage()
    se.udf.register("myAvgAge",udaf)
    se.sql("select myAvgAge(age) from user").show()

    se.stop()
  }

  class MyAverage extends UserDefinedAggregateFunction{
    // 函数输入的数据结构
    override def inputSchema: StructType = {
      val structType = new StructType()
      structType.add("age",LongType)
      structType
    }

    // 计算时缓冲区的数据结构
    override def bufferSchema: StructType = {
      new StructType().add("sum",LongType).add("count",LongType)
    }

    // 返回类型
    override def dataType: DataType = DoubleType

    // 函数是否稳定
    override def deterministic: Boolean = true

    // 计算前缓冲区数据结构初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      // 按结构顺序初始化
      buffer(0) = 0L
      buffer(1) = 0L
    }

    // 更新缓冲区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer(0) = buffer.getLong(0) + input.getLong(0)
      buffer(1) = buffer.getLong(1) + 1
    }

    // 将多个节点的缓冲区合并
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0).toDouble / buffer.getLong(1)
    }
  }
}
