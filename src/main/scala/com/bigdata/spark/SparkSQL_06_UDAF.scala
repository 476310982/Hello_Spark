package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructType}
import org.apache.spark.sql.{Encoder, Encoders, Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

// TODO 定义自定义函数的三种方式
object SparkSQL_06_UDAF {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("mySparkRDD"))
    val se: SparkSession = SparkSession.builder().appName("mySparkSQL").master("local[*}").getOrCreate()

    val listRDD: RDD[(Int, String, Int)] = sc.makeRDD(List((1, "zhangsan", 18), (2, "lisi", 19), (3, "wangwu", 18), (4, "zhaoliu", 20), (5, "ruanqi", 32)))
    //    listRDD.foreach(println)
    import se.implicits._
    listRDD.toDF("id", "name", "age").createTempView("user")
    // TODO 注册简单的函数
    se.udf.register("myUpperCase", (str: String) => {
      str.head.toUpper + str.substring(1, str.length)
    })
    se.sql("select id,myUpperCase(name),age from user").show()

    // TODO 注册弱定义函数
    val aggFunt = new myAggFunt()
    se.udf register("myMax", aggFunt)
    se.sql("select myMax(age) from user").show()

  }
  // TODO 注册弱类型自定义聚合函数
  class myAggFunt extends UserDefinedAggregateFunction {
    override def inputSchema: StructType = {
      new StructType().add(
        "num", LongType
      )
    }

    override def bufferSchema: StructType = {
      new StructType().add(
        "maxNum", LongType
      )
    }

    override def dataType: DataType = LongType

    override def deterministic: Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      if(buffer.getLong(0) < input.getLong(0)){
        buffer(0) = input.getLong(0)
      }
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      if(buffer1.getLong(0) < buffer2.getLong(0)){
        buffer1(0) = buffer2.getLong(0)
      }
    }

    override def evaluate(buffer: Row): Long = {
        buffer.getLong(0)
    }
  }

  case class Employee(name: String, salary: Long)
  case class Average(var sum: Long, var count: Long)

  // TODO 注册强类型自定义聚合函数
  object MyAverage extends Aggregator[Employee, Average, Double] {
    // 定义一个数据结构，保存工资总数和工资总个数，初始都为0
    def zero: Average = Average(0L, 0L)
    // Combine two values to produce a new value. For performance, the function may modify `buffer`
    // and return it instead of constructing a new object
    def reduce(buffer: Average, employee: Employee): Average = {
      buffer.sum += employee.salary
      buffer.count += 1
      buffer
    }
    // 聚合不同execute的结果
    def merge(b1: Average, b2: Average): Average = {
      b1.sum += b2.sum
      b1.count += b2.count
      b1
    }
    // 计算输出
    def finish(reduction: Average): Double = reduction.sum.toDouble / reduction.count
    // 设定之间值类型的编码器，要转换成case类
    // Encoders.product是进行scala元组和case类转换的编码器
    def bufferEncoder: Encoder[Average] = Encoders.product
    // 设定最终输出值的编码器
    def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }

}
