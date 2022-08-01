package com.atguigu.bigdata.spark


object MapClass extends App {
  // TODO 不可变Map
  private val map = Map("a" -> 1, "b" -> 2, "c" -> 3) // 初始化Map
  println(map.keys) // Set(a, b, c)
  println(map.values) // MapLike(1, 2, 3)
  // 增加数据
  println((map + ("d" -> 4)).mkString("|")) // a -> 1|b -> 2|c -> 3|d -> 4
  // 删除数据 删除key即可
  println((map - ("c")).mkString("|")) // a -> 1|b -> 2
  // 删除不存在的kv键值对 不会报错
  println((map - ("f")).mkString("|")) // b -> 2|a -> 1|c -> 3
  // 修改数据
  println(map.updated("c", 4).mkString("|")) // a -> 1|b -> 2|c -> 4
  println((map + ("a" -> 4)).mkString("|")) // a -> 4|b -> 2|c -> 3
  // 查询数据
  // TODO Scala为了防止集合出现空指针问题，提供了一个特殊的类Option，有两个特殊的对象{Some,None},如果获取不到数据，可以提供默认值
  println(map.get("a")) // Some(1)
  println(map("a")) // 1
  println(map.getOrElse("e", -999)) // -999
  println(map.getOrElse("e", "ElemNotFound")) // ElemNotFound

  // TODO 可变Map
  import scala.collection.mutable
  private val map1: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
  println(map1.mkString("|")) // b -> 2|a -> 1|c -> 3
}
