package com.atguigu.bigdata.spark

object ObjectClass extends App {
  private val student = new Student("zhangsan",18)
  student.printInfo()
  println(student.name)
  // TODO 调用伴生类对象属性
  Student.printInfo()
  println(Student.sex)

  class Student(var name:String,var age:Int) {
    def printInfo(): Unit = {
      println(s"学生姓名:${name},今年${age}岁.")
    }
  }
// TODO 创建伴生类，相当于java中的静态类
  object Student{
    var sex:String = "Man"
    def printInfo(): Unit = {
      println(s"学生性别:${sex}.")
    }
  }
}
