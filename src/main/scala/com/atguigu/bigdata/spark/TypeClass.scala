package com.atguigu.bigdata.spark

import com.atguigu.bigdata.spark.TypeClass.{Cats, Colors}

import scala.io.StdIn

object TypeClass {
  def main(args: Array[String]): Unit = {
//    println(Colors.values)
//    Colors.values.foreach(
//      color => println(s"ID:${color.id}\tColor:${color}")
//    )

    var list = (1, 2, 3, 4)
    List(list).foreach(i=>{println(i)})
  }

  // TODO 必须是Object伴生对象，否则无法直接调用
  object Colors extends Enumeration {
    val RED, ORANGE, YELLOW, GREEN, WHITE, BLACK = Value
    val PINK: Colors.Value = Value(10, "PINK")
  }

  /*    val in: String = StdIn.readLine("请输入小猫的名字:")
    val cat = new Cat(in)
    if (cat.bool) {
      cat.cry()
      cat.run()
      cat.cal
    }*/

  object Cats extends Enumeration {
    val XIAOHUA: Cats.Value = Value(0, "XIAOHUA")
    val XIAOBAI: Cats.Value = Value(1, "XIAOBAI")
  }

  class Cat() {
    var age: Int = _
    var color: String = _
    var bool: Boolean = false

    def this(name: String) {
      this()
      if (name == "XIAOHUA" || name == "XIAOBAI") {
        printInfo(name, age, color)
        bool = true
      } else {
        println("查无此猫")
      }
    }

    def printInfo(name: String, age: Int, color: String) {
      if (name == "XIAOHUA") println(s"小猫的名字是${name},今年10岁,毛色是${Colors.BLACK}")
      else if (name == "XIAOBAI") println(s"小猫的名字是${name},今年3岁,毛色是${Colors.WHITE}")
      else println("查无此猫")
    }

    def cry(): Unit = {
      println("小猫会喵喵叫")
    }

    def run(): Unit = {
      println("小猫会蹦蹦跳")
    }

    def cal(): Unit = {
      println("小猫会算算术")
    }
  }
}
