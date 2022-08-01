package com.atguigu.bigdata.spark

import scala.io.StdIn
import scala.util.control.Breaks._

object BaseClass extends App {
  // TODO 高级函数 将函数作为参数传递给另一个函数，需要使用特殊的声明方式
  def fun(f: (Int) => Int, a: Int, b: Int): Unit = {
    println(f(a) + b)
  }
  def f0(a: Int) = a + 1
  fun(f0, 1, 2)
  // TODO 使用匿名函数，以及简化过程
  fun((x:Int)=>{x+1},1,2)
  fun((x)=>{x+1},1,2)
  fun((x)=>x+1,1,2)
  fun(x=>x+1,1,2)
  fun(_+1,1,2)



}

/*

  def aFun(a: Int) = {
    // TODO 函数可以在内部继续声明函数，如果想要返回函数，则用特殊符号"_"表示
    def bFun(b: Int): Int = {
      // TODO 闭包
      b.+(1) + a
    }
    bFun _
  }
  println(aFun(1)(1))
  def a(): Unit = {
    println("被调用的函数")
  }
  def b() = {
    a _
  }
  b()()
  // TODO 函数柯里化 思想：把一个复杂的问题拆成一个个简单的问题
  def myFun(a: Int, b: Int, c: Int) = a + b + c
  println(myFun(1, 2, 3)) // 6
  def myFun1(a: Int)(b: Int)(c: Int) = a + b + c
  println(myFun1(1)(2)(3)) // 6

  // TODO 完整的函数写法
  def myFun(a: Int, b: Int): Int = {
    return a + b
  }

  // TODO 如果将函数体的最后一行进行返回，那么return关键字可以省略
  def myFun1(a: Int, b: Int): Int = {
    a + b
  }

  // TODO scala可以采用自动推断功能来简化函数的声明，如果可以根据函数的最后一行代码推断类型，那么函数返回值类型可以省略
  def myFun2(a: Int, b: Int) = {
    a + b
  }

  // TODO 继续简化
  def myFun3(a: Int, b: Int) = a + b
  breakable{
      for (i <- 1 to 5) if (i % 2 == 0 ) break else println(i)
    }

  for (i <- 1.until(10) if i % 3 == 0) println(i)
  // TODO 从1遍历到5（不包含5）
  for (i <- 1.until(5)) print(i + " ")
  println()
  for (i <- 1 until 5) print(i + " ")
  println()
  // TODO 从1遍历到5（不包含5）
  for (i <- Range(1, 5)) print(i + " ")
  println()
  // TODO 从1遍历到5（不包含5）,步长2
  for (i <- Range(1, 5,2)) print(i + " ")
  println()
  // TODO 从1遍历到5
  for (i <- 1 to 5) print(i + " ")
  println()
  for (i <- 1.to(5)) print(i + " ")
  println()
  println(1.+(1))
  println(1.-(1))
  var str1:String = "zs"
  println(str1.equals("ls"))
  println(args.toString)
  val floatNum:Float = 1.1f
  val doubleNum:Double = 9.9990090
  val name:String = "zhangsan"
  // TODO 插值字符串
  println(f"浮点数${floatNum}%.2f,${doubleNum}%.3f")
  println(s"name:${name}")
  // TODO 格式化字符串
  printf("name = %s \n",name)
  // TODO 原始打印
  print(raw"name=${name} \n \n")*/
