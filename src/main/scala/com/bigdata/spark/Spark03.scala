package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("Spark02").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(sparkConf)
    val arrayRDD: RDD[List[Int]] = sc.makeRDD(Array(List(1, 2), List(3, 4)))
    val intRDD: RDD[Int] = arrayRDD.flatMap(datas => datas.map(_+1))
//    arrayRDD.collect().foreach(println)
    intRDD.collect().foreach(println)
  }
}
