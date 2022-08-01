package com.atguigu.bigdata.spark

import scala.language.implicitConversions

object Transform3Class {
  def main(args: Array[String]): Unit = {
    val user = new User
    user.insert()
    user.delete()
  }

  class User(){
    def insert(){}
  }
  // TODO Scala2.10版本后支持隐式类
  //  其所带的构造参数有且只能有一个
  //  隐式类必须被定义在“类”或“伴生对象”或“包对象”里，即隐式类不能是 顶级的(top-level  objects)。
  //  隐式类不能是case class（case class在后续介绍 样例类）
  //  作用域内不能有与之相同名称的标识符
  implicit class Peron(u:User){
    def delete(){}
  }
}
