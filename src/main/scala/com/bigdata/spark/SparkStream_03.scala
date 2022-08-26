package com.bigdata.spark

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

// TODO 从文件读取数据
object SparkStream_03 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))
    // val lineStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("192.168.3.20", 9999)
    // val fileStream: DStream[String] = streamingContext.textFileStream("in")

    // TODO 创建RDD队列
    val rddQueue = new mutable.Queue[RDD[Int]]()
    val inputStream: InputDStream[Int] = streamingContext.queueStream(rddQueue, oneAtATime = false)
    //val wordStream: DStream[String] = fileStream.flatMap(x => x.split(" "))
    val mapStream: DStream[(Int,Int)] = inputStream.map((_,1))
    val reduceStream: DStream[(Int,Int)] = mapStream.reduceByKey((x, y) => x + y)
    reduceStream.print()
    streamingContext.start()
    for(i<-1 to 5){
      rddQueue += streamingContext.sparkContext.makeRDD(1 to  300,10)
      Thread.sleep(2000)
    }
    streamingContext.awaitTermination()
  }
}
