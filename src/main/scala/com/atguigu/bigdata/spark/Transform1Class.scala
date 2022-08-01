package com.atguigu.bigdata.spark

import scala.language.implicitConversions

object Transform1Class {
  def main(args: Array[String]): Unit = {
    val mysql = new Mysql
    mysql.select()
    // 通过隐式转换后mysql对象可以调用Operator类的方法
    mysql.delete()
  }
  implicit def transform(mysql: Mysql): Operator = {
    new Operator
  }
  //TODO 隐式转换丰富类库功能
  class Mysql {
    def select() {}
  }
  class Operator() {
    def delete() {}
  }
}
