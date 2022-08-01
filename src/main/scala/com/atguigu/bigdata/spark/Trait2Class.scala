package com.atguigu.bigdata.spark

object Trait2Class {
  def main(args: Array[String]): Unit = {
    val mysql = new Mysql()
    mysql.insert()
  }
  trait Operator {
    def insert() {
      println("插入数据")
    }
  }
  trait DB extends Operator {
    override def insert(): Unit = {
      print("向数据库")
      super.insert()
    }
  }
  trait File extends Operator {
    override def insert(): Unit = {
      print("向文件")
      super[Operator].insert()
    }
  }
  class Mysql extends DB with File {
    //    println("Mysql")
  }
}

