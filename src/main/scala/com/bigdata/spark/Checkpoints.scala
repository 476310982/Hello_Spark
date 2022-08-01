package com.bigdata.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Checkpoints {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)
    //    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4,4),2)
    //    val mapRDD: RDD[(Int, Int)] = listRDD.map((_, 1))
    //    val reduceRDD: RDD[(Int, Int)] = mapRDD.reduceByKey((_ + _))
    //    println(reduceRDD.collect().mkString(" "))
    // TODO RDD缓存
    val seqRDD: RDD[Char] = sc.makeRDD("ai")
    val mapRDD: RDD[String] = seqRDD.map((_.toString + System.currentTimeMillis()))
    println(mapRDD.collect().mkString(",")) // a1658760390452,i1658760390453
    println(mapRDD.collect().mkString(",")) // a1658760390689,i1658760390668
    val mapRDD2: RDD[String] = seqRDD.map((_.toString + System.currentTimeMillis())).cache()
    println(mapRDD2.collect().mkString(",")) // a1658760390807,i1658760390813
    println(mapRDD2.collect().mkString(",")) // a1658760390807,i1658760390813
  }
}