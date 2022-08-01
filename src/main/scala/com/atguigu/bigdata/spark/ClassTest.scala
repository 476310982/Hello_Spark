package com.atguigu.bigdata.spark


object ClassTest {
  class Calcuator {
    var num1: Int = _
    var num2: Int = _
    //    var opt: Char = _

    def sum(num1: Int, num2: Int): Int = {
      num1 + num2
    }

    def min(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    def mul(num1: Int, num2: Int): Int = {
      num1 * num2
    }

    def det(num1: Int, num2: Int): Unit = {
      try {
        println(10 / 3)
      } catch {
        case ex: ArithmeticException => println("除数不能为零")
        case ex: Exception => println("发生未知异常")
      }finally {
        println("计算结束")
      }
    }
  }

  class Calcuator2 {
    var num1: Int = _
    var num2: Int = _
    var opt: Char = _

    def option(num1: Int, num2: Int, opt: Char): Int = {
      if (opt == '+') {
        num1 + num2
      }
      else if (opt == '-') {
        num1 - num2
      } else if (opt == '*') {
        num1 * num2
      } else if (opt == '/') {
        try {
          num1 / num2
        } catch {
          case ex: ArithmeticException => {
            println("除数不能为零")
            -1
          }
        }
      }
      else
        -1
    }
  }

  class Emp {
    def Emp(): Unit = {
      println("This is an emp!")
    }
  }

  object Emp {
    val username = "张三"

    def apply(): Emp = new Emp()
  }

  def main(args: Array[String]): Unit = {
    /*    val calcuator = new Calcuator()
        println(calcuator.sum(1, 2))
        println(calcuator.min(1, 2))
        println(calcuator.mul(1, 2))
        println(calcuator.det(1, 0))
        val calcuator2= new Calcuator2()
        println(calcuator2.option(1, 2,'+'))
        println(calcuator2.option(1, 2,'-'))
        println(calcuator2.option(1, 2,'*'))
        println(calcuator2.option(1, 0,'/'))*/
    //    package test2
    val e1 = new Emp()
    val e = Emp
    println(e.username)
    e1.Emp
  }
}
