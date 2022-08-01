package com.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import java.sql.DriverManager
import org.apache.spark.rdd.JdbcRDD

object Spark06 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")
    val sc = new SparkContext(sparkConf)
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://192.168.3.20:3306/test"
    val userName = "root"
    val passWd = "apexsoft"

    val rdd = new JdbcRDD(sc, () => {
      Class.forName(driver)
      DriverManager.getConnection(url, userName, passWd)
    },
      "select empid,empname from emp where empid between ? and ?;",
      1,
      10000,
      1,
      r => (r.getInt(1), r.getString(2))
    )
    println(rdd.count())
    rdd.foreach(println)
    sc.stop()
  }
}
