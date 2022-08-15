package com.bigdata.spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL01 {
  // DataFrame SQL查询语法
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Spark"))
    // TODO SparkSession内部初始化函数是私有的，无法直接new出来，只能通过函数创建
    val se: SparkSession = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate()
    // TODO RDD、DataFrame、DataSet三者间的转换需要导入该对象的 implicits 方法
    // TODO 从json文件中读取数据
    val dataFrame: DataFrame = se.read.json("in/user.json")
    // TODO 定义或重定义视图名称
    //  dataFrame.createOrReplaceTempView("user")
    //  val dataFrame1: DataFrame = se.sql("select name,age from user")
    // TODO 创建全局都可以访问的视图,加上 global_temp 前缀
    dataFrame.createGlobalTempView("user")
    se.sql("select * from global_temp.user").show()
    // TODO 创建新的会话验证是否能访问到这张全局视图
    se.newSession().sql("select name,age from global_temp.user").show()
    se.stop()
  }
}
