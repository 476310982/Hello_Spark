package com.bigdata.spark

import org.apache.hadoop.mapred.lib.HashPartitioner
import org.apache.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05 {
  def main(args: Array[String]): Unit = {
    // TODO partitionBy 重新分区
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")
    val sc = new SparkContext(sparkConf)
    val valueRDD: RDD[(Char, Int)] = sc.makeRDD(List(('a', 1), ('b', 2), ('c', 3), ('d', 4),('a',2)), 4)
    //println(valueRDD.getNumPartitions) // 4
    val valueRDD2: RDD[(Char, Int)] = valueRDD.partitionBy(new spark.HashPartitioner(2))
    //println(valueRDD2.getNumPartitions) // 2
    // TODO groupByKey
    println(valueRDD.groupByKey(2).map(x=>(x._1,x._2.size)).collect().mkString(" ")) // 计数 (d,1) (b,1) (a,2) (c,1)
    println(valueRDD.groupByKey(2).map(x=>(x._1,x._2.sum)).collect().mkString(" ")) // 求和 (d,4) (b,2) (a,3) (c,3)
    // TODO reduceBykey 存在预聚合 combine
    //println(valueRDD.reduceByKey((x, y) => x + y).collect().mkString(" ")) // 求和 (d,4) (b,2) (a,3) (c,3)
    println(valueRDD.reduceByKey(_+_).collect().mkString(" ")) // 求和 (d,4) (b,2) (a,3) (c,3)
    // TODO aggregateByKey 聚和 zeroValue：初始值
    // 13个元组分成4个分区，3 3 3 4
    val valRDD: RDD[(Char, Int)] = sc.makeRDD(List(('a', 1), ('a', 1), ('a', 1), ('a', 1), ('a', 1), ('a', 1), ('a', 1), ('b', 4), ('b', 2), ('b', 1), ('c', 3), ('c', 2), ('c', 1)), 4)
    //valRDD.saveAsTextFile("output")
    //println(valRDD.aggregateByKey(0, 1)(Math.max(_, _), _ + _).collect().mkString(" ")) // (a,3) (b,5) (c,3)

    //println(valRDD.aggregateByKey(0, 1)(_+_,_ + _).collect().mkString(" ")) // (a,7) (b,7) (c,6)
    //println(valRDD.foldByKey(0, 1)(_ + _).collect().mkString(" ")) // (a,7) (b,7) (c,6)
    // TODO combineByKey 要主动声明参数类型
    //println(valRDD.combineByKey(x => x, (x: Int, y: Int) => x + y, (x: Int, y: Int) => x + y).collect().mkString(" ")) // (a,7) (b,7) (c,6)

    // TODO sortByKey
    //println(valRDD.sortByKey(true, 2).collect().mkString(" "))
    // (a,1) (a,1) (a,1) (a,1) (a,1) (a,1) (a,1) (b,4) (b,2) (b,1) (c,3) (c,2) (c,1)

    // TODO mapValues 只对K-V中的V值操作
    println(valRDD.mapValues(_ * 100).collect().mkString(" "))
    // (a,100) (a,100) (a,100) (a,100) (a,100) (a,100) (a,100) (b,400) (b,200) (b,100) (c,300) (c,200) (c,100)

    
  }
}
