package com.bigdata.spark

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object BoradCast {
  // TODO 广播变量
  def main(args: Array[String]): Unit = {
    val sc:SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("myTest"))
    val listRDD: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c"), (4, "d"), (5, "e")))
    val list: List[(Int, Int)] = List((1, 1), (2, 2), (3, 3), (4, 4))
    val broadcast: Broadcast[List[(Int, Int)]] = sc.broadcast(list)
    println(broadcast.value)

    val mapRDD: RDD[(Int, (String, Any))] = listRDD.map {
      case (key, value) => {
        var v2: Any = null
        for (i <- broadcast.value) {
          if (key == i._1) {
            v2 = i._2
          }
        }
        (key, (value, v2))
      }
    }
    println(mapRDD.collect().mkString(" "))
  }
}
