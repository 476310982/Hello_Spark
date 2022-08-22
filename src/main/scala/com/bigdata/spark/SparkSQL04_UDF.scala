package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL04_UDF {
  // DataFrame SQL查询语法
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Spark"))
    // TODO SparkSession内部初始化函数是私有的，无法直接new出来，只能通过函数创建
    val se: SparkSession = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate()
    // TODO RDD、DataFrame、DataSet三者间的转换需要导入该对象的 implicits 方法
    import se.implicits._
    sc.makeRDD(List((1, "zhangsan", 18), (2, "lisi", 19), (3, "wangwu", 20))).toDF("id","name","age").createOrReplaceTempView("user")
    val addName: UserDefinedFunction = se.udf.register("addName", (x: String) => {
      "Name:" + x
    })
    se.sql("select id,addName(name),age from user").show()
    se.stop()
  }
  // TODO 创建一个案例类
  case class User(id: Int, name: String, age: Int)

}
