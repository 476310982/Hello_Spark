package com.atguigu.bigdata.spark

import scala.io.Source

object FromFileWordCount extends App {
  private val list: List[Int] = List(1, 2, 3, 4)
  private val list1: List[Int] = List(5,6,7,8)
  // TODO 拉链
  private val tuples: List[(Int, Int)] = list.zip(list1)
  println(tuples.mkString(",")) //(1,5),(2,6),(3,7),(4,8)
  // TODO 过滤
  println(list.filter(x => x % 2 == 0)) // List(2, 4)


}


/*
private val lines: List[String] = Source.fromFile("in/word.txt").getLines().toList
  // println(lines.mkString(",")) //Hello Scala,Hello Spark,Hello World,Hello Hadoop,Hello Scala
  println(lines.flatMap(_.split(" ")).groupBy(t => t).mapValues(list => list.size).toList.sortWith((left, right) => left._2 > right._2).mkString(","))
*/
