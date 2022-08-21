package com.bigdata.spark

import org.apache.spark.sql.SparkSession

object SparkSQL08_accessHive {
  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder().appName("SparkAccessHive").master("local[*]").getOrCreate()
    
    sparkSession.sql("show tables").show()
  }
}
