package com.atguigu.bigdata.spark

object ExtendsClass {
  def main(args: Array[String]): Unit = {
    val emp = new Emp("zs")
    println(emp.name)
    emp.printName()

    //    val student = new Student("lisi")
  }
  class Emp(var name: String) {
    def printName(): Unit ={
      println(name)
    }
  }

  class Person(name: String) {
    println("Person:" + name)
  }

  class Student(string: String) extends Person("zs") {
    //class Student(string: String) extends Person(string){
    println("Student:" + string)
  }

}
