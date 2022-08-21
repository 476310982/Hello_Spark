package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL_06_UDAF {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("mySparkRDD"))
    val se: SparkSession = SparkSession.builder().appName("mySparkSQL").master("local[*}").getOrCreate()

    val listRDD: RDD[(Int, String, Int)] = sc.makeRDD(List((1, "zhangsan", 18), (2, "lisi", 19), (3, "wangwu", 18), (4, "zhaoliu", 20), (5, "ruanqi", 32)))
    //    listRDD.foreach(println)
    import se.implicits._
    listRDD.toDF("id", "name", "age").createTempView("user")
    se.udf.register("myUpperCase", (str: String) => {
      str.head.toUpper + str.substring(1, str.length)
    })
    se.sql("select id,myUpperCase(name),age from user").show()
    val aggFunt = new myAggFunt()
    se.udf register("myMax", aggFunt)
    se.sql("select myMax(age) from user").show()

  }

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
}
