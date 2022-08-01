package com.atguigu.bigdata.spark

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

object ArrrayClass {
  def main(args: Array[String]): Unit = {
    val ints: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    ints+=(5,6,10)
    println(ints.mkString(" ")) // 1 2 3 4 5 6 10
    ints-=(5,6,7)
    println(ints.mkString(" ")) // 1 2 3 4 10

    val ints1: Array[Int] = Array(1, 2, 3, 4)
    // TODO 可变数组to不可变
    val array: Array[Int] = ints.toArray
    // TODO 不可变数组to可变
    val buffer: mutable.Buffer[Int] = ints1.toBuffer
  }
}


/*
    println(ints.mkString(" "))
    // TODO 向指定位置增加元素
    ints.insert(1,5)
    println(ints.mkString(" ")) // 1 5 2 3 4
    // TODO 删除指定位置的元素，或者从指定位置开始删除n个元素
    val i: Int = ints.remove(1) // 有返回值
    println(s"删除了指定元素：${i},当前数组内容为：${ints.mkString(" ")}")
    // 删除了指定元素：5,当前数组内容为：1 2 3 4
    ints.remove(1, 2) // 无返回值
    println(s"当前数组内容为：${ints.mkString(" ")}") // 当前数组内容为：1 4

    val ints: Array[Int] = Array(1, 2, 3, 4)
    println(ints.mkString(" ")) // 1 2 3 4
    // TODO 更新数组，注意：数组是不可变对象，指的是内存地址不可变，但是内容是可以变更的
    ints.update(0,5)
    println(ints.mkString(" ")) // 5 2 3 4
    // TODO 增加数组 可以通过特殊的符号来创建一个新的数组,":"要紧贴数组对象
    val ints1 = ints:+6
    println(ints1.mkString(" ")) // 5 2 3 4 6
    val ints2 = 6+:ints
    println(ints2.mkString(" ")) // 6 5 2 3 4
// TODO Scala中的数组，使用Array对象实现，使用中括号声明数组类型
val ints: Array[Int] = Array[Int](1, 2, 3, 4)
  // TODO 通过简单的方式(apply)创建数组对象
  val ints1: Array[Int] = Array(1, 2, 3, 4)
  // TODO 使用小括号，增加索引的方式来访问数组
  println(ints(0))
  ints.update(0,5)
  //    println(ints.length)
  //    println(ints.last)
  println(ints.mkString("|"))
  //    println(ints.+(" "))
  ints.foreach(print)
//    ints1.foreach(print(_))
//    for (i <- ints) print(i + " ")*/
