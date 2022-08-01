package com.atguigu.bigdata.spark

object TupleClass extends App {
  // TODO 元组 将无关的数据当成一个整体 tupLeN,N代表内部元数个数
  // 内部最多22个元素
  private val tuple3: (String, Int, String) = ("zs", 18, "man")
  println(tuple3.toString()) // (zs,18,man)
  // TODO 循环遍历
  tuple3.productIterator.foreach(println)
  // TODO 如果元组元素个数只有2个，称之为对偶，类似Map中的键值对
  private val tuple2: (String, Int) = ("zs", 18)
  println(tuple2._1 + " = " + tuple2._2) // zs = 18
  println(tuple2.toString()) // (zs,18)
  private val map = Map(tuple2,"a"->1)
  map.foreach(t=>println(t._1 + " = " + t._2)) // zs = 18 a = 1
  private val iterator: Iterator[Any] = tuple2.productIterator
  iterator.foreach(println)
}
