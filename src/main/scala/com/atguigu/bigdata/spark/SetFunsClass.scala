package com.atguigu.bigdata.spark

object SetFunsClass extends App {
  val list = List(1, 2, 3, 4)
//  private val i: Int = list.reduce((left, right) => {
//    left + right
//  })
  // TODO 化简
  //  数据减少，不是结果变少
  //  将集合的数据经过逻辑处理后只保留一个结果，具体的结果需要参考逻辑实现
  private val i: Int = list.reduce(_+_)
  println(i) // 10
  println(list.reduceLeft(_-_)) // -8
  println(list.reduceRight(_-_)) // -2
  // TODO 折叠 fold
  private val i1: Int = list.fold(100)(_ + _)
  println(i1) // 110
  private val list2: List[Int] = List(1, 2)
  println(list2.asInstanceOf[List[Int]])



}


/*// TODO 集合常用的函数举例
private val list = List(1, 2, 3, 4)
// TODO 求和
println("sum = " + list.sum) // sum = 10
// TODO 最大值
println("max = " + list.max) // max = 4
// TODO 最小值
println("min = " + list.min) // min = 1
// TODO 乘积
println("product = " + list.product) // product = 24
// TODO 反转（无排序）
println("reverse = " + list.reverse.mkString(" ")) // reverse = 4 3 2 1 函数返回的是集合，需要调用函数格式化

private val list1 = List("11", "12", "23", "21", "11", "12")
//  private val list1: List[String] = List("hello", "world", "scala", "hello", "hello", "world")
// TODO 分组
//  println(list1.grouped(1).mkString(" "))
//  list1.grouped(1).foreach(x=>println(x.mkString(" ")))
println(list1.groupBy(x => x.substring(1)).mkString(",")) // 2 -> List(12, 12),1 -> List(11, 21, 11),3 -> List(23)
// TODO 排序
println(list1.sortBy(x => x.substring(1)).mkString(",")) // 11,21,11,12,12,23
// TODO 升降序排序
println(list1.sortWith((x, y) => x < y).mkString(",")) // 升序 11,11,12,12,21,23
println(list1.sortWith((x, y) => x.substring(1).toInt > y.substring(1).toInt).mkString(",")) // 降序 23,12,12,11,21,11
// TODO 映射
println(list1.map(x => x + "1").mkString(",")) // 111,121,231,211,111,121
println(list1.map(_ + 1).mkString(",")) // 111,121,231,211,111,121
println(list1.map(x => {
  (x, 1)
}).mkString(",")) // (11,1),(12,1),(23,1),(21,1),(11,1),(12,1)
// TODO 简单计数
private val map: Map[String, Int] = list1.groupBy(x => x).map(x => (x._1, x._2.size))
println(map("11"))*/
//  private val tuples = list1.map(x=>(x,1))
// private val map: Map[String, List[(String, Int)]] = tuples.groupBy((t) => {t._1})
// private val map1: Map[String, Int] = map.map((t) => {(t._1, t._2.size)})
//  private val tuples = list1.map(x => (x, 1))
//  private val map: Map[String, List[(String, Int)]] = tuples.groupBy(_._1)
// TODO WordCount
/*private val list: List[String] = List("Hello world", "Hello Scala", "Hello world")
  //  println(list.mkString(",")) // Hello world,Hello Scala,Hello world
  // TODO 将列表扁平化 也就是将一个整体拆成单独的元素
  //  private val flatMapList: List[String] = list.flatMap(x => x.split(" "))
  private val flatMapList: List[String] = list.flatMap(_.split(" "))
  //  println(flatMapList.mkString(",")) // Hello,world,Hello,Scala,Hello,world
  private val map: Map[String, List[String]] = flatMapList.groupBy(x => x)
  println(map.mkString(",")) // world -> List(world, world),Hello -> List(Hello, Hello, Hello),Scala -> List(Scala)
  private val map1: Map[String, Int] = map.map(x => (x._1, x._2.size))
  println(map1.mkString(",")) // world -> 2,Hello -> 3,Scala -> 1
  println(map1.toList.sortWith((left,right)=>{left._2>right._2}).mkString(",")) // (Hello,3),(world,2),(Scala,1)*/


/*  // TODO 过滤 filter
  private val list: List[Int] = List(1, 2, 3, 4)
  //  println(list.filter(x => x % 2 == 0).mkString(",")) // 2,4
  println(list.filter(_ % 2 == 0).mkString(",")) // 2,4
  private val list1: List[Int] = List(4, 5, 6, 7, 8)
  // TODO 拉链 ZIP
  private val list2: List[(Int, Int)] = list.zip(list1)
  println(list2.mkString(",")) // (1,4),(2,5),(3,6),(4,7)

  // TODO 并集 UNION
  println(list.union(list1).mkString(",")) // 1,2,3,4,4,5,6,7,8
  // TODO 交集 intersect
  println(list.intersect(list1).mkString(",")) // 4
  // TODO 差集 diff
  println(list.diff(list1).mkString(",")) // 1,2,3
  // TODO 差集 diff
  println(list.diff(list1).mkString(",")) // 1,2,3*/
