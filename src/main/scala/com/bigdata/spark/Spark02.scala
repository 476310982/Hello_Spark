package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("Spark02").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(sparkConf)
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10,2)
    //    val mapRDD: RDD[Int] = listRDD.map(x => x * 2)
    val mapRDD: RDD[Int] = listRDD.map(_ * 2)
    // TODO 作用：类似于map，但独立地在RDD的每一个分片上运行，
    //  因此在类型为T的RDD上运行时，func的函数类型必须是Iterator[T] => Iterator[U]。
    //  假设有N个元素，有M个分区，那么map的函数的将被调用N次,而mapPartitions被调用M次,一个函数一次处理所有分区。
    //  mapPartitions 可以对一个RDD中的所有分区进行遍历，效率高于map算子，减少了发送到执行器执行交互的次数
    //  缺点是容易造成内存溢出（OOM）
    val partRDD: RDD[Int] = listRDD.mapPartitions(datas => datas.map(_ * 2))
    // TODO 作用：类似于mapPartitions，但func带有一个整数参数表示分片的索引值，
    //  因此在类型为T的RDD上运行时，func的函数类型必须是(Int, Interator[T]) => Iterator[U]；
    //  分区索引index跟task号有关系
    val valueRDD: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex {
      case (num, datas) => {
        datas.map((_, "分区号:" + num))
      }
    }
    valueRDD.collect().foreach(println)
  }
}
