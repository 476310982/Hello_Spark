package com.handler

import java.util.Properties

object getPropertiesUtil {

  def getProperties(fileName: String, key: String): String = {
    val properties = new Properties()
    val inputStream = getPropertiesUtil.getClass.getClassLoader.getResourceAsStream(fileName)
    properties.load(inputStream)
    val value: String = properties.get(key).toString
    value
  }

  //  def setProperties(fileName:String,key:String,value:String): Unit ={
  //    val properties = new Properties()
  //    properties.load(getPropertiesUtil.getClass.getClassLoader.getResourceAsStream(fileName))
  //    properties.setProperty(key, value)
  //
  //  }
  //
  //  def main(args: Array[String]): Unit = {
  //    println(getProperties("spark-config.properties", "userName"))
  //    setProperties("spark-config.properties","userName","root1")
  //    println(getProperties("spark-config.properties", "userName"))
  //
  //  }
}
