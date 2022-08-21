package com.bigdata.spark

import com.handler.getPropertiesUtil
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL07_JDBC {
  def main(args: Array[String]): Unit = {
    System.setProperty("java.security.krb5.conf", "krb5.conf")
    val se: SparkSession = SparkSession.builder().master("local[*]").appName("mySpark")
//      .config("spark.kerberos.keytab", "apex/apex@apexsoft.com")
//      .config("spark.kerberos.principal", "apex.keytab")
      .getOrCreate()
    // TODO 通过JDBC读取mysql数据
    val dataFrame: DataFrame = se.read.format("jdbc")
      .option("url", "jdbc:mysql://192.168.3.20:3306/test")
      .option("dbtable", "emp")
      .option("user", "root")
      .option("password", "apexsoft")
      .load()
    dataFrame.show()
    // TODO 通过JDBC写入数据到mysql
    getPropertiesUtil.getProperties("spark-config.properties")
    dataFrame.write.jdbc("jdbc:mysql://192.168.3.20:3306/test", "emp2", getPropertiesUtil.getProperties("spark-config.properties"))
    // TODO 写入数据方法2
    dataFrame.write.format("jdbc")
      .option("url", "jdbc:mysql://192.168.3.20:3306/test")
      .option("dbtable", "emp3")
      .option("user", "root")
      .option("password", "apexsoft")
      .save()
  }
}
