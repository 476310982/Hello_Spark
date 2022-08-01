package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val wordCount: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(wordCount)
    val lines: RDD[String] = sc.textFile("in")
    val unit: RDD[String] = lines.flatMap(_.split(" "))
    val word: RDD[(String, Int)] = unit.map((_, 1))
    val res: Array[(String, Int)] = word.reduceByKey(_ + _).collect()
    res.foreach(println)
  }
}
