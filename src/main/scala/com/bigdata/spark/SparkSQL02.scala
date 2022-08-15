package com.bigdata.spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL02 {
  // DataFrame DSL查询语法
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Spark"))
    // TODO SparkSession内部初始化函数是私有的，无法直接new出来，只能通过函数创建
    val se: SparkSession = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate()
    val dataFrame: DataFrame = se.read.json("in/user.json")
    // TODO 通过对象方法去访问
    dataFrame.printSchema()
    dataFrame.select("id", "name", "age").show()
    dataFrame.filter("age > 18").show()
    dataFrame.where("age > 18").filter("name='wangwu'").show()
    dataFrame.sort("age").select("name", "age").show()
    dataFrame.groupBy("age").count().show()
    dataFrame.agg("age" -> "avg").show()
    se.stop()
  }
}
