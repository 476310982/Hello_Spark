package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Serializable {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)
    val arrayRDD: RDD[String] = sc.makeRDD(Array("Hadoop", "Hive", "Spark", "Flink"))
    val search = new Search("H")
    val match1: RDD[String] = search.getMatch1(arrayRDD)
    val match2: RDD[String] = search.getMatch2(arrayRDD)
    match1.collect().foreach(println)
    match2.collect().foreach(println)


    sc.stop()
  }

  class Search(query: String) /*extends Serializable*/ {
    def isMatch(s: String): Boolean = {
      s.contains(query)
    }

    def getMatch1(rdd: RDD[String]): RDD[String] = {
      rdd.filter(isMatch)
    }

    def getMatch2(rdd: RDD[String]): RDD[String] = {
      rdd.filter(x => x.contains(query))
    }

  }

}