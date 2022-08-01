package com.atguigui.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Actions {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Actions")
    val sc: SparkContext = new SparkContext(sparkConf)
    // TODO reduce 聚合所有元素数据，先聚合分区内，再聚合分区间
    // TODO collect 显示所有元素
    // TODO first 获取第一个元素
    // TODO take(n) 取n个元素
    // TODO takeOrdered(n) 排序后取前n个元素

    //    val rangeRDD: RDD[Int] = sc.makeRDD(Range(1, 100), 2)
    //    //    val i: Int = rangeRDD.reduce((x, y) => x + y)
    //    val i: Int = rangeRDD.reduce(_ + _)
    //    println(i)
    //    val arrayRDD: RDD[(String, Int)] = sc.makeRDD(Array(("a", 1), ("b", 2), ("c", 3), ("d", 4)), 4)
    //    println(arrayRDD.reduce((x, y) => (x._1 + y._1, x._2 + y._2)))
    //    val listRDD: RDD[Int] = sc.makeRDD(List(10, 1, 2, 3, 7, 6, 4), 2)
    //    println(listRDD.reduce(_ + _)) // 33
    //    println(listRDD.map(x => x.toString).reduce(_ + _)) // 字符串拼接 10123764
    //    println(listRDD.collect().mkString(" ")) // 10 1 2 3 7 6 4
    //    println(listRDD.first()) // 10
    //    println(listRDD.take(3).mkString(" ")) // 10 1 2
    //    println(listRDD.takeOrdered(3).mkString(" ")) // 1 2 3
    val arrayRDD: RDD[Int] = sc.makeRDD(1 to 10, 2)
    //    println(arrayRDD.collect().mkString(" "))
    // TODO aggregate 聚合，分区内聚合再分区间聚合，根据分区数N个，初始值会被计算N+1次（分区内计算N次，分区间计算1次）
    //    println(arrayRDD.aggregate(10)(_ + _, _ + _)) // 85
    //    println(arrayRDD.aggregate(0)(_ + _, _ + _)) // 55
    //    val tupleRDD: RDD[(Char, Int)] = sc.makeRDD('a' to 'j', 2).zip(sc.makeRDD(1 to 10, 2))
    //    println(tupleRDD.collect().mkString(",")) // (a,1),(b,2),(c,3),(d,4),(e,5),(f,6),(g,7),(h,8),(i,9),(j,10)
    //    println(tupleRDD.aggregateByKey(10)(_ + _, _ + _).collect().mkString(",")) // (d,14),(b,12),(h,18),(f,16),(j,20),(e,15),(a,11),(i,19),(g,17),(c,13)
    // TODO aggregateByKey 对键值对的数据进行分区内和分区间聚合，根据分区数N个，初始值会被计算N次，分区间聚合初始值不参与
    //    val tupleRDD: RDD[(Char, Int)] = sc.makeRDD(1 to 10, 2).map(x => ('a', x))
    //    println(tupleRDD.collect().mkString(",")) // (a,1),(a,2),(a,3),(a,4),(a,5),(a,6),(a,7),(a,8),(a,9),(a,10)
    //    println(tupleRDD.aggregateByKey(10)(_ + _, _ + _).collect().mkString(",")) // (a,75)
    // TODO fold 与aggregate功能类似，但是只需提供一个方法
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10, 2)
    listRDD.saveAsTextFile("output1")
    listRDD.saveAsObjectFile("output2")
    //listRDD.saveAsSequenceFile("output3")
    //    val value: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex {
    //      case (num, x) => {
    //        x.map((_, "分区号:" + num))
    //      }
    //    }
    //    println(value.collect().mkString(",")) // (1,分区号:0),(2,分区号:0),(3,分区号:0),(4,分区号:0),(5,分区号:0),(6,分区号:1),(7,分区号:1),(8,分区号:1),(9,分区号:1),(10,分区号:1)
    //    println(listRDD.fold(10)(_ + _)) // 85
    //    println(listRDD.fold(10)(_ - _)) // 45
    // TODO 计算过程为 10 -（10-1-2-3-4-5）-（10 -6-7-8-9-10）

  }

}