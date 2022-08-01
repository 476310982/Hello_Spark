package com.atguigu.bigdata.spark

import scala.language.implicitConversions

object Transform2Class {
  def main(args: Array[String]): Unit = {
    // TODO 隐式值
    implicit var username:String = "wangwu"
/*  implicit var username1:String = "wangwu"
    // TODO 定义相同类型的隐式值，运行时会报错
    ambiguous implicit values:
      both variable myAge of type String
    and variable username of type String
    match expected type String
    Error occurred in an application involving default arguments.*/
    // TODO 隐式参数
    def test(implicit name:String ): Unit ={
      println("Hello " + name)
    }
    // TODO 方法调用时，使用小括号会使隐式值无法传递
//    test() // Hello zhangsan
    // TODO 使用相同类型的隐式值,如果没有定义隐式值就会使用默认值
    test   // Hello wangwu
  }
}
