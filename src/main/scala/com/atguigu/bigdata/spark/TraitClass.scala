package com.atguigu.bigdata.spark

object TraitClass {
  def main(args: Array[String]): Unit = {
    val student = new Student
    println(student.sex)
    student.sex = "woman"
    println(student.sex)
    student.test()
    student.abstactTest()
  }
  class Person {    println("Person")  }
  trait Sex {
    println("Sex")
    var sex: String = "man"
    def test() = println("test...")
  }
  trait Age {
    println("Age")
    def abstactTest()
  }
  class Student extends Person with Sex with Age {
    println("Student")
    override def abstactTest(): Unit = {
      println("重写特质内的抽象类")
    }
  }
}

