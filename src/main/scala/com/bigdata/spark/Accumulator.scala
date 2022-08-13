package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Accumulator {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Actions")
    val sc: SparkContext = new SparkContext(sparkConf)
    // TODO 案例一：使用累计器统计文件中空行数量
    val textRDD: RDD[String] = sc.textFile("in/word.txt")
    // TODO 定义累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")
    // TODO 数据集扁平化处理时发现空行则累加器计数+1
    val flatMapRDD: RDD[String] = textRDD.flatMap(x => {
      if (x == "") {
        sum.add(1)
      }
      x.split(" ")
    })
    // TODO 案例二：自定义单词累加器
    val wordAccumulator = new WordAccumulator()
    // TODO spark需要注册该变量才能使用
    sc.register(wordAccumulator)
    val listRDD: RDD[String] = sc.makeRDD(List("Hello", "Scala", "Hello", "Spark", "Hello"), 4)
    listRDD.foreach {
      case word => {
        //        println(word)
        wordAccumulator.add(word)
      }
    }
    println(wordAccumulator.count)
  }


  // TODO 自定义单词累加器
  class WordAccumulator() extends org.apache.spark.util.AccumulatorV2[String, java.util.ArrayList[String]] {
    val list = new java.util.ArrayList[String]()

    // 判断累加器是否为空
    override def isZero: scala.Boolean = {
      list.isEmpty
    }

    // 复制累加器
    override def copy(): org.apache.spark.util.AccumulatorV2[String, java.util.ArrayList[String]] = {
      new WordAccumulator()
    }

    // 重置累加器
    override def reset(): scala.Unit = {
      list.clear()
    }

    // 往累加器添加数据
    override def add(word: String): scala.Unit = {
      if (word.toUpperCase().contains("H")) {
        list.add(word)
      }
    }

    // 查看累加器长度
    def count: scala.Long = {
      list.size()
    }

    // 合并累加器
    override def merge(other: org.apache.spark.util.AccumulatorV2[String, java.util.ArrayList[String]]): scala.Unit = {
      list.addAll(other.value)
    }

    // 返回列表
    override def value = {
      list
    }
  }
}