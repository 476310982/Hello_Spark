package com.handler.mysql

import com.handler.getPropertiesUtil
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException

import java.sql.{Connection, DriverManager, ResultSet, Statement}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object mysqlHandler {
  val userName: String = getPropertiesUtil.getProperties("spark-config.properties", "userName")
  val passWord: String = getPropertiesUtil.getProperties("spark-config.properties", "passWord")
  val url: String = getPropertiesUtil.getProperties("spark-config.properties", "url")

  def getSchema(database: String, tableName: String): mutable.Map[String, String] = {
    // TODO　获取表结构
    var schemaMap: mutable.Map[String, String] = mutable.Map()
    try {
      Class.forName("com.mysql.jdbc.Driver")
      val connection: Connection = DriverManager.getConnection(url, userName, passWord)
      val statement: Statement = connection.createStatement()
      val sql = "select * from " + database + "." + tableName + " limit 1;"
      println(sql)
      val resultSet: ResultSet = statement.executeQuery(sql)
      var metaData = resultSet.getMetaData

      for (i <- 1 to metaData.getColumnCount) {
        if (metaData.getColumnTypeName(i) == "INT") {
          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getColumnDisplaySize(i) + ")"))
        }
        else if (metaData.getColumnTypeName(i) == "FLOAT" || metaData.getColumnTypeName(i) == "DOUBLE" || metaData.getColumnTypeName(i) == "DECIMAL") {
          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getPrecision(i) + "," + metaData.getScale(i) + ")"))
        } else if (metaData.getColumnTypeName(i) == "DATE" || metaData.getColumnTypeName(i) == "TIMESTAMP") {
          schemaMap = schemaMap + (metaData.getColumnName(i) -> metaData.getColumnTypeName(i).toLowerCase())
        }
        else if (metaData.getColumnTypeName(i) == "VARCHAR" || metaData.getColumnTypeName(i) == "CHAR") {
          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getColumnDisplaySize(i) + ")"))
        } else if (metaData.getColumnTypeName(i) == "BOOLEAN") {
          schemaMap = schemaMap + (metaData.getColumnName(i) -> metaData.getColumnTypeName(i).toLowerCase())
        }
      }
      //      schemaMap.foreach(println)
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
    schemaMap
  }

  def testArgs(args: String*): Unit = {
    println(args.length)
    args.foreach(println)
  }

  def queryTable(database: String, tableName: String, columns: String*): Unit = {
    val list: ListBuffer[String] = ListBuffer()
    val sql = "select " + columns.mkString(",") + " from " + database + "." + tableName + ";"
    println(sql)
    try {
      Class.forName("com.mysql.jdbc.Driver")
      val connection: Connection = DriverManager.getConnection(url, userName, passWord)
      val statement: Statement = connection.createStatement()
      val resultSet: ResultSet = statement.executeQuery(sql)
      println(columns.mkString("\t"))
      while (resultSet.next()) {
        list.clear()
        for (i <- 1 to resultSet.getMetaData.getColumnCount) {
          list.insert(i-1, resultSet.getObject(i).toString)

        }
//        println(list.mkString("\t"))
      }
    }
    catch {
      case e: MySQLSyntaxErrorException => {
        println(e.getMessage)
      }
      case e: Exception => e.printStackTrace()
    }
  }


  def main(args: Array[String]): Unit = {
    queryTable("test", "emp", "empid", "empname", "sex")


  }
}

//    val schema: mutable.Map[String, String] = getSchema("emp")
//    println(schema.keys.mkString("|"))

//    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("MysqlConnect")
//    val sc = new SparkContext(sparkConf)
//    println(userName, passWord, url)
// TODO 通过RDD方式访问mysql数据，要求上下限的字段必须是数字类型

//    val mysqlRDD = new JdbcRDD(
//      sc,
//      () => {
//        Class.forName("com.mysql.jdbc.Driver")
//        DriverManager.getConnection(url, userName, passWord)
//      },
//      "select empid,empname from emp where empid between ? and ?;",
//      1,
//      1000,
//      1,
//      rs => (rs.getInt(1), rs.getString(2))
//    )
//    println(mysqlRDD.count())
//    mysqlRDD.foreach(println)

// TODO 用java的jdbc方式连接数据库

//    try {
//      Class.forName("com.mysql.jdbc.Driver")
//      val connection: Connection = DriverManager.getConnection(url, userName, passWord)
//      val statement: Statement = connection.createStatement()
//      val sql = "select * from emp limit 1;"
//      val resultSet: ResultSet = statement.executeQuery(sql)
//      val count: Int = resultSet.getMetaData.getColumnCount
//      println("number of columns :" + count)
//      var metaData = resultSet.getMetaData
//      //      val rs: Int = statement.executeUpdate("delete from emp where empid = 1003;")
//      //      statement.executeUpdate("insert into emp (empid,empname,salary) values (1003,'dinghangei',15000.01);")
//
//      var schemaMap: mutable.Map[String, String] = mutable.Map()
//      for (i <- 1 to metaData.getColumnCount) {
//        if (metaData.getColumnTypeName(i) == "INT") {
//          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getColumnDisplaySize(i) + ")"))
//        }
//        else if (metaData.getColumnTypeName(i) == "FLOAT" || metaData.getColumnTypeName(i) == "DOUBLE" || metaData.getColumnTypeName(i) == "DECIMAL") {
//          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getPrecision(i) + "," + metaData.getScale(i) + ")"))
//        } else if (metaData.getColumnTypeName(i) == "DATE" || metaData.getColumnTypeName(i) == "TIMESTAMP") {
//          schemaMap = schemaMap + (metaData.getColumnName(i) -> metaData.getColumnTypeName(i).toLowerCase())
//        }
//        else if (metaData.getColumnTypeName(i) == "VARCHAR" || metaData.getColumnTypeName(i) == "CHAR") {
//          schemaMap = schemaMap + (metaData.getColumnName(i) -> (metaData.getColumnTypeName(i).toLowerCase() + "(" + metaData.getColumnDisplaySize(i) + ")"))
//        } else if (metaData.getColumnTypeName(i) == "BOOLEAN") {
//          schemaMap = schemaMap + (metaData.getColumnName(i) -> metaData.getColumnTypeName(i).toLowerCase())
//        }
//
//        // 获得所有列的数目及实际列数
//        val columnCount = metaData.getColumnCount
//        // 获得指定列的列名
//        val columnName = metaData.getColumnName(i)
//        // 获得指定列的列值
//        val columnType = metaData.getColumnType(i)
//        // 获得指定列的数据类型名
//        val columnTypeName = metaData.getColumnTypeName(i)
//        // 所在的Catalog名字
//        val catalogName = metaData.getCatalogName(i)
//        // 对应数据类型的类
//        val columnClassName = metaData.getColumnClassName(i)
//        // 在数据库中类型的最大字符个数
//        val columnDisplaySize = metaData.getColumnDisplaySize(i)
//        // 默认的列的标题
//        val columnLabel = metaData.getColumnLabel(i)
//        // 获得列的模式
//        val schemaName = metaData.getSchemaName(i)
//        // 某列类型的精确度(类型的长度)
//        val precision = metaData.getPrecision(i)
//        // 小数点后的位数
//        val scale = metaData.getScale(i)
//        // 获取某列对应的表名
//        val tableName = metaData.getTableName(i)
//        // 是否自动递增
//        val isAutoInctement = metaData.isAutoIncrement(i)
//        // 在数据库中是否为货币型
//        val isCurrency = metaData.isCurrency(i)
//        // 是否为空
//        val isNullable = metaData.isNullable(i)
//        // 是否为只读
//        val isReadOnly = metaData.isReadOnly(i)
//        // 能否出现在where中
//        val isSearchable = metaData.isSearchable(i)
//
//        //        println("获得列 " + i + " 所在的 Catalog 名字（库名）:" + catalogName)
//        //        println("获得列 " + i + " 对应的表名:" + tableName)
//        //        println("获得列 " + i + " 的字段名称:" + columnName)
//        //        println("获得列 " + i + " 的数据类型名:" + columnTypeName)
//        //        println("获得列 " + i + " 对应数据类型的 Java 类:" + columnClassName)
//        //
//        //        println("获得列 " + i + " 的类型,返回 SqlType 中的编号:" + columnType)
//        //        println("获得列 " + i + " 在数据库中类型的最大字符个数:" + columnDisplaySize)
//        //        println("获得列 " + i + " 的默认的列的标题:" + columnLabel)
//        //        println("获得列 " + i + " 的模式:" + schemaName)
//        //        println("获得列 " + i + " 类型的精确度(类型的长度):" + precision)
//        //        println("获得列 " + i + " 小数点后的位数:" + scale)
//        //        println("获得列 " + i + " 是否自动递增:" + isAutoInctement)
//        //        println("获得列 " + i + " 在数据库中是否为货币型:" + isCurrency)
//        //        println("获得列 " + i + " 是否为空:" + isNullable)
//        //        println("获得列 " + i + " 是否为只读:" + isReadOnly)
//        //        println("获得列 " + i + " 能否出现在 where 中:" + isSearchable)
//        println()
//      }
//      //      while (resultSet.next()) {
//      //        println("empid = %s,empname = %s".format(resultSet.getInt("empid"), resultSet.getString("empname")))
//      //      }
//
//
//      schemaMap.foreach(println)
//    } catch {
//      case ex: MySQLIntegrityConstraintViolationException => {
//        println("主键不唯一")
//        println("更新失败")
//      }
//      case ex: Exception => ex.printStackTrace()
//    }
