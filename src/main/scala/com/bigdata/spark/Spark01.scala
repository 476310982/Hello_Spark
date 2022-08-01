package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01 {
  def main(args: Array[String]): Unit = {
    val wordCount: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // TODO 创建上下文对象，在Spark中非常关键
    val sc: SparkContext = new SparkContext(wordCount)
    // TODO 创建RDD
    //  1）从内存中创建 makeRDD,底层实现就是parallelize
    //  2）从内存中创建 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val arrayRDD: RDD[String] = sc.parallelize(Array("1", "2", "3", "4"))
    listRDD.collect().foreach(x => {print(x + " ")})
    arrayRDD.collect().foreach(println)
    // TODO 从外部存储中创建RDD
    //  默认情况下，可以读取项目路径，也可以读取HDFS路径
    //  默认从文件中读取的数据都是字符串类型
    //  读取文件按时，传递的分区参数为最小分区数，但不一定是这个分区数，取决于hadoop读取文件分片规则
    val fileRDD: RDD[String] = sc.textFile("in", 2)
    val mapRDD: RDD[(String, Int)] = fileRDD.flatMap(x => x.split(" ")).map(x => (x, 1)).reduceByKey((left, right) => {
      left + right
    })
    //    mapRDD.collect().foreach(println)
    // TODO 将结果保存到文件当中
    mapRDD.saveAsTextFile("output")
  }
}
