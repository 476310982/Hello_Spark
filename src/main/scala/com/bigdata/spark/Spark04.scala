package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import math.max

object Spark04 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("Spark02").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(sparkConf)
    val pairRDD: RDD[(Char, Int)] = sc.makeRDD(List(('a', 3), ('a', 2), ('c', 4), ('b', 3), ('c', 6), ('c', 8)), 2)
    // pairRDD.saveAsTextFile("output")
    println(pairRDD.collect().mkString(" "))
    println(pairRDD.aggregateByKey(0)(max(_, _), _ + _).collect().mkString(" "))
    // pairRDD.saveAsTextFile("output")
  }
}
