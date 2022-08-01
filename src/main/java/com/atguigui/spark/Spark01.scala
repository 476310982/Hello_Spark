package com.atguigui.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)
    // TODO 读取文件数据，分析每个省份广告点击数的TOP3
    // TODO 第一步：读取文件数据
    val fileRDD: RDD[String] = sc.textFile("in")
    fileRDD.take(3).foreach(println)
    /*    1516609143867 6 7 64 16
        1516609143869 9 4 75 18
        1516609143869 1 7 87 12*/
    // TODO 第二步：提取关键数据
    val lineRDD: RDD[((String, String), Int)] = fileRDD.map { lines =>
      val fields = lines.split(" ")
      ((fields(1), fields(4)), 1)
    }
    lineRDD.take(3).foreach(println)
    /*    ((6,16),1)
        ((9,18),1)
        ((1,12),1)*/
    // TODO 第三步：求出每个（省份，广告）的总数
    val sumRDD: RDD[((String, String), Int)] = lineRDD.reduceByKey((x, y) => x + y)
    sumRDD.take(3).foreach(println)
    /*    ((1,25),15)
        ((0,7),5)
        ((5,10),15)*/
    // TODO 第四步：改变数据结构，由（（省份，广告），数量）=>（省份，（广告，数量）），便于统计
    val mapRDD: RDD[(String, (String, Int))] = sumRDD.map(x => (x._1._1, (x._1._2, x._2)))
    mapRDD.take(3).foreach(println)
    /*    (1,(25,15))
        (0,(7,5))
        (5,(10,15))*/
    // TODO 第五步：排序并取TOP3
    val resRDD: RDD[(String, List[(String, Int)])] = mapRDD.groupByKey().mapValues(
      x =>
        x.toList.sortWith((x, y) => x._2 > y._2).take(3)
    )
  }
}
