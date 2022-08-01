package com.atguigu.bigdata.spark

import scala.collection.mutable


object SetClass extends App {
  // TODO Set集合 无序且不重复 immutable
  val set = Set(5,1, 2, 3, 4, 1)
  println(set.mkString(" ")) // 5 1 2 3 4
  println((set-1).mkString(" ")) // 5 2 3 4
  println((set+11).mkString(" ")) // 5 1 2 3 11 4 新增元素的位置不固定 因为Set集合是无序的
  // TODO mutable
  import scala.collection.mutable
  val mset: mutable.Set[Int] = mutable.Set(1, 2, 3, 4, 1)
  println(mset.mkString(" ")) // 1 2 3 4
  // TODO 增删改查的方法大同小异
}

  /*
  // TODO 队列一定是可变集合 数据变动先进先出
  import scala.collection.mutable
  val queue: mutable.Queue[Int] = mutable.Queue(1, 2, 3, 4)
  println(queue.mkString(" ")) // 1 2 3 4
  // TODO 数据进入队列
  queue.enqueue(5)
  println(queue.mkString(" ")) // 1 2 3 4 5
  // TODO 数据出队
  println(queue.dequeue()) // 1
  println(queue.mkString(" ")) // 2 3 4 5
  // TODO 不可变集合,以下操作基本都会生成新的集合
  val list: List[Int] = List(1, 2, 3, 4) //scala.collection.immutable.List
  val list1: List[Int] = List(5, 6, 7, 8)
  // TODO 增加数据得多种灵活方式
  val list2: List[Int] = list.::(9)
  println(list2.mkString(" ")) // 9 1 2 3 4
  // TODO 冒号的运算顺序是从右到左
  println(list == list2) // false
  println((7 :: 8 :: list).mkString(" "))
  println((9 :: list1 :: list).mkString(" ")) // 9 List(5, 6, 7, 8) 1 2 3 4
  println((9 :: list1 ::: list.::(10)).mkString(" ")) // 9 5 6 7 8 10 1 2 3 4
  println(list.++(list1).mkString(" ")) // 1 2 3 4 5 6 7 8
  println((list ++ list1).mkString(" ")) // 1 2 3 4 5 6 7 8
  // TODO 返回符合条件的元素个数
  println(list.count(_ % 2 == 0)) // 2
  // TODO 修改数据
  println(list.updated(1, 10).mkString(" ")) // 1 10 3 4
  // TODO 删除数据 从头删除n个元素，可以大于集合长度
  println(list.drop(10).mkString(" ")) // NULL

  // TODO 可变集合
  import scala.collection.mutable.ListBuffer
  val listBuffer: ListBuffer[Int] = ListBuffer(1, 2, 3, 4)
  listBuffer.insert(0,0)
  println(listBuffer.mkString(" ")) // 0 1 2 3 4
  val listBuffer1: ListBuffer[Int] = listBuffer.drop(1)
  println(listBuffer1.mkString(" ")) // 1 2 3 4
  listBuffer.update(1,2)
  println(listBuffer.mkString(" ")) // 0 2 2 3 4
  */


