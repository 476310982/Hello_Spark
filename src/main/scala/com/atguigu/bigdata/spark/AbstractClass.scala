package com.atguigu.bigdata.spark

object AbstractClass {
  def main(args: Array[String]): Unit = {
    val person = new Student()
    person.printlnName()
    person.printlnPwd()
  }

  abstract class Person {
    val username: String = "li"
    var password: String

    def printlnName()

    def printlnPwd(): Unit = {
      println("Person:" + password)
    }
  }

  class Student extends Person {
    override val username: String = "zs"
    var password: String = "*********"

    def printlnName(): Unit = {
      println(username)
    }

    override def printlnPwd(): Unit = {
      super.printlnPwd()
      println("Child's password:" + "111111")
    }
  }
}
