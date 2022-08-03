package com.handler.mysql

import com.handler.getPropertiesUtil
import org.apache.spark.SparkConf

object mysqlHandlerForSpark {
  val userName: String = getPropertiesUtil.getProperties("spark-config.properties", "userName")
  val passWord: String = getPropertiesUtil.getProperties("spark-config.properties", "passWord")
  val url: String = getPropertiesUtil.getProperties("spark-config.properties", "url")

  def main(args: Array[String]): Unit = {
    new SparkConf().setMaster("")
  }
}
