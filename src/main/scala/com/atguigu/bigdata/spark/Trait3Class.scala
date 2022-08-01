package com.atguigu.bigdata.spark

import com.sun.xml.internal.bind.v2.TODO

object Trait3Class {
  def main(args: Array[String]): Unit = {
    val mysql = new Mysql()
    mysql.insert()
  }

  trait Operator extends DB{
    def insert() {
      println(this.printDBType + "插入数据")
    }
  }

  class DB{
    def printDBType() = "mysql"
  }

  class Mysql extends Operator {}
}

