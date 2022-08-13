package com.handler.mysql

import com.handler.getPropertiesUtil
import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

import java.sql.DriverManager

object mysqlHandlerForSpark {
  val userName: String = getPropertiesUtil.getProperties("spark-config.properties", "userName")
  val passWord: String = getPropertiesUtil.getProperties("spark-config.properties", "passWord")
  val url: String = getPropertiesUtil.getProperties("spark-config.properties", "url")

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("connectMysqlBySpark")
    val sc: SparkContext = new SparkContext(sparkConf)
    val jdbcRDD = new JdbcRDD(
      sc,
      () => {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection(url, userName, passWord)
      },
      "select * from emp where empid between ? and ?;",
      1000,
      1003,
      1,
      rs => (rs.getInt(1), rs.getString(2))
    )
    println(jdbcRDD.count())
    println(jdbcRDD.getClass)
    println(jdbcRDD.getPartitions)
    println(jdbcRDD.collect())
    println(jdbcRDD.id)
    println(jdbcRDD.name)
  }
}
