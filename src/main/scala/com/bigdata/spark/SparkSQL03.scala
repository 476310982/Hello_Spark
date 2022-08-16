package com.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL03 {
  // DataFrame SQL查询语法
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Spark"))
    // TODO SparkSession内部初始化函数是私有的，无法直接new出来，只能通过函数创建
    val se: SparkSession = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate()
    // TODO RDD、DataFrame、DataSet三者间的转换需要导入该对象的 implicits 方法
    import se.implicits._
    val listRDD: RDD[(Int, String, Int)] = sc.makeRDD(List((1, "zhangsan", 18), (2, "lisi", 19), (3, "wangwu", 20)))
    listRDD.foreach(println)
    // TODO RDD <- -> DataFrame
    val dataFrame: DataFrame = listRDD.toDF("id", "name", "age")
//    dataFrame.show()
//    dataFrame.printSchema()
//    val rdd: RDD[Row] = dataFrame.rdd
//    rdd.foreach(println)
    // TODO RDD <- -> DataSet
    val mapRDD: RDD[User] = listRDD.map(t => User(t._1, t._2, t._3))
    mapRDD.foreach(println)
    val ds: Dataset[User] = mapRDD.toDS()
    ds.show()
    ds.select("id","name").show()
    val rdd: RDD[User] = ds.rdd
    // TODO DataFrame  <- -> DataSet
    val ds2: Dataset[User] = dataFrame.as[User]
    val dataFrame2: DataFrame = ds2.toDF()
    se.stop()
  }
  // TODO 创建一个案例类
  case class User(id: Int, name: String, age: Int)

}
