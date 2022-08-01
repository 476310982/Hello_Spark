package com.atguigu.bigdata.spark

import scala.util.control.Breaks.{break, breakable}
import scala.io.StdIn


object ScalaTest {
  def main(args: Array[String]): Unit = {
    //    print("请输入您的年龄:")
    //    val inputAge: Int = scala.io.StdIn.readInt()
    //    printf("您的年龄是 %s\n",inputAge)
    //    if (inputAge >= 18)
    //      {
    //        print("恭喜您已成年")
    //      }
    //      else {
    //      print("您未成年")
    //    }
    //    var num = 10
    //    val result = if (num >=18 ) "恭喜您已成年！" else if (num >= 10 ) "该上学啦" else "您还未成年"
    //    println(result)
    //    for(i <- 1 to 3; j = 4 - i) {
    //      print(j + " ")
    //    }
    //    打印1~100之间所有是9的倍数的整数的个数及总和
    //    var num = 0
    //    var sum = 0
    //    breakable {
    //      for (i <- 1 to 100 if i % 9 == 0) {
    //        num += 1
    //        sum += i
    //        println(i, sum)
    //        if (sum > 100) break()
    //      }
    //    }
    //    printf("1~100之间所有是9的倍数的整数的个数为%s个，总和为%s\n",num,sum)
    //      for (i<- 0 to 6;j=6-i){
    //        printf("%s + %s = %s\n",i,j,i+j)
    //      }
    //    var res = for(i <- 1 to 10) yield i * 2
    //    println(res)
    //    for (i <- res if i % 3 == 0) {println(i)}
    //    打印1—100之间所有能被3整除的数
    //    var i = 1
    //    breakable {
    //      while (i <= 100) {
    //        if (i % 3 == 0) println(i)
    //        i += 1
    //        if (i > 50) {
    //          break()
    //        }
    //      }
    //    }
    //  }
    //    打印40—200之间所有的偶数
    //    var j = 40
    //    do {
    //      if (j % 2 == 0) println(j)
    //      j += 1
    //    }
    //    while (j>=40 && j <= 200)
    //    100以内的数求和，求出当和 第一次大于20的当前数是多少？
    //    breakable {
    //      var sum: Int = 0
    //      for (i <- Range(1, 101)) {
    //        sum += i
    //        if (sum > 20) {
    //          println(sum)
    //          break()
    //        }
    //      }
    //    }
    //    实现登录验证，有三次机会，如果用户名为”张无忌” ,密码”888”提示登录成功，否则提示还有几次机会，请使用for 循环完成
    //    breakable {
    //      for (i <- 1 to 3) {
    //        print("用户张无忌，请输入密码：")
    //        val pass: Int = StdIn.readInt()
    //        if (pass == 888) {
    //          println("登陆成功！")
    //          break()
    //        }
    //        else if (3 - i == 0) {
    //          println("登陆失败,禁止登陆1小时")
    //        }
    //        else {
    //          printf("密码错误，剩余%s次机会\n", 3 - i)
    //        }
    //      }
    //
    //    def calTool(num1:Int,num2:Int,opt:Char): AnyVal = {
    //      return if (opt == '+') num1 + num2 else if (opt == '-') num1 - num2 else if (opt == '*') num1 * num2 else if (opt == '/') num1 / num2
    //    }
    //    println(calTool(1,2,'+'))
    //    println(calTool(1,2,'-'))
    //    println(calTool(1,2,'*'))
    //    println(calTool(1,2,'/'))
    //    给你一个整数n，求出它的斐波那契数是多少？ 1,1,2,3,5,8,13...
    //    def DeGuiFun(num: Int): Int = {
    //      if (num == 1 || num == 0) return num else return DeGuiFun(num - 1) + DeGuiFun(num - 2)
    //    }

    //    for (i <-1 to 10) print(DeGuiFun(i).toString + " ")
    //    猴子吃桃子问题有一堆桃子，猴子第一天吃了其中的一半，并再多吃了一个！以后每天猴子都吃其中的一半，然后再多吃一个。当到第十天时，想再吃时（还没吃），发现只有1个桃子了。问题：最初共多少个桃子？
    //    def MonkeyEatPeach(num: Int): Int = {
    //      if (num == 1)
    //        return 1
    //      else
    //        return MonkeyEatPeach(num - 1) * 2 + 1
    //    }
    //    println(MonkeyEatPeach(10))
    //    函数的形参传入，传入默认参数
    //    def mysqlCon(url: String = "jdbc:mysql://localhost",
    //                 port: Int = 3306,
    //                 user: String = "root",
    //                 pwd: String = "root"): Unit = {
    //      println("host=" + url)
    //      println("port=" + port)
    //      println("user=" + user)
    //      println("pwd=" + pwd)
    //    }
    //    mysqlCon("xxx",8808,pwd = "888")
    //    函数传入可变形参
    //    def ArgsTest(num:Int,args:Int*): Unit ={
    //      println(num)
    //      for (i <- args) println(i)
    //    }
    //    ArgsTest(1,2,3,4,5)
    //    匿名函数
    //    def f1(): Int = {100}
    //    var f2 = f1()
    //    println(f1,f2)
    //    println(f1())
    // 输入字符串和Double值, 变成整数求和.
    // 完整的写法:
    //    val f2: (String, Double) => Int = (a: String, b: Double) => a.toInt + b.toInt
    //    // 简化版1,  后面的函数写法省略全部类型
    //    val f3: (String, Double) => Int = (a, b) => a.toInt + b.toInt
    //    // (String, Double) => Int 是一个数据类型(函数签名)
    //    // 简化版2 , 省略函数签名
    //    val f4 = (a: String, b: Double) => a.toInt + b.toInt
    //    println(f4("11", 12.0))
    //    val f5 = (num1: Int, num2: Int, opt: Char) => {
    //      if (opt == '+') num1 + num2 else if (opt == '-') num1 - num2 else if (opt == '*') num1 * num2 else if (opt == '/') num1 / num2
    //    }
    //    println(f5(1, 2, '+'))

    //    def f1 = "venassa"
    //
    //    println(f1) //
    //
    //    传值调用与传名调用
    //    var money = 100
    //
    //    def buy(): Int = {
    //      money -= 10
    //      money
    //    }
    //
    //    def test1(a: Int) = {
    //      println(a)
    //      println(a)
    //    }
    //    def test2(a: => Int) = {
    //      println(a)
    //      println(a)
    //    }
    //    test1(buy)
    //    test2(buy)
    //    高阶函数
    //    def fmtInt(n:Int):String = "[整数值{"+n+"}]"
    //    def apply(f:Int=>String,n:Int):String = f(n)
    //    println(apply(fmtInt,10))
    //    def costMoney(m: Int) = {
    //      (d: Double) => m * (1 - d)
    //    }
    //    println(costMoney(10)(0.2))
    def f5(s: String): Int = 5

    def f4(): Int = 4

    def f6(f: () => Int): Int = f() + 5

    //    print(f6(f4))
    def f7(f: (String) => Int): Int = f("C") + 5

    //    print(f7(f5))
    def f8(f: () => Unit): Unit = {
      f()
    }

    //    f8(()=>print("xxxx"))
    val l = List(1, 2, 3, 4)
    l.map((x) => x + 1)
    println(l.map((x) => x + 1))
    //    for (i <- l){
    //      println(i)
    //    }

    def sum(n1: Int, n2: Int): Int = {
      println("sum() 执行了..")
      return n1 + n2
    }

    lazy val res = sum(10, 20)
    //    println("-----------------")
    //    println("res=" + res)
    //    try{
    //      var r = 10 / 1
    //    }catch {
    //      case exception: ArithmeticException => {
    //        println("这是一个计算错误")
    //        println("哦吼")
    //      }
    //      case exception: Exception=> {
    //        println("这是其他报错")
    //      }
    //    }
    //    finally {
    //      println("不管怎样都要执行")
    //    }

    /*@throws(classOf[NumberFormatException])
    def f11()  = {
      "abc".toInt
    }
    try {
      f11()
    }catch {
      case exception: NumberFormatException => print("数字格式异常")
    }*/


    def fun1(x: Int)(y: Int): Int = x * x * y


    val f = fun1(1)(2)
    print(f)

  }
}
