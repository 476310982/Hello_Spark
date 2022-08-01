package com.atguigu.bigdata.spark

import scala.language.implicitConversions

object TransformClass extends App {
  // TODO Scala默认情况下支持数值类型的自动转换
  // byte->short->int->long
  // TODO Scala默认情况下支持多态语法中的类型自动转换
  // child->parent->trait(interface)
  // TODO Scala也允许开发人员自定义类型转换规则
  // 将两个无关的类型通过编程手段让它们可以自动转换
  // TODO　原始方法
  var i: Int = 5.0.toInt
  // TODO　Scala自动识别开发人员自定义的转换规则，可自定义函数名称，但是在相同作用域下不允许有两个相同类型的转换规则
  implicit def transform(d: Double): Int = d.toInt
  var j: Int = 6.0
}
