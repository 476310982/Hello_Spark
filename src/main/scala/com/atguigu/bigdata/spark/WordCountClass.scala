package com.atguigu.bigdata.spark

object WordCountClass extends App {
  private val list: List[(String, Int)] = List(("Hello World", 4), ("Hello Scala", 3), ("Hello Spark", 1), ("Hello World", 2))
  // TODO 思路讲解
  //  1.先将列表中的元组数据扁平化 flatMap
  //  2.进行分类 groupBy
  //  3.映射+求和 map+sum
  //  4.排序+ sortwith
  private val tupleList: List[(String, Int)] = list.flatMap(
    t => {
      // =>("Hello World", 4)
      t._1.split(" ").map(w => (w, t._2))
      // => Hello,World => (Hello,4),(World,4)
    }
  ) //  => [(Hello,4),(World,4),(Hello,3),(Scala,3),(Hello,1),(Spark,1),(Hello,2),(World,2)]
  private val map1 = tupleList.groupBy(t => t._1)
  // =>Map(Hello -> List((Hello,4), (Hello,3), (Hello,1), (Hello,2)), Spark -> List((Spark,1)), Scala -> List((Scala,3)), World -> List((World,4), (World,2)))
  private val sumMap: Map[String, Int] = map1.mapValues(
    list => list.map(
      // => List((Hello,4), (Hello,3), (Hello,1), (Hello,2))
      tuple => tuple._2
      // =>(Hello,4) => 4
    ).sum
  )
  //  println(sumMap.mkString(",")) // Hello -> 10,Spark -> 1,Scala -> 3,World -> 6
  println(sumMap.toList.sortWith((left, right) => {
    left._2 > right._2
  }).take(3))
}

