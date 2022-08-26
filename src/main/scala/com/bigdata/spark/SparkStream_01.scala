package com.bigdata.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

// TODO 简单的wordcount用例
object SparkStream_01 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))
    val lineStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("192.168.3.20", 9999)
    val wordStream: DStream[String] = lineStream.flatMap(x => x.split(" "))
    val mapStream: DStream[(String, Int)] = wordStream.map((_, 1))
    val reduceStream: DStream[(String, Int)] = mapStream.reduceByKey((x, y) => x + y)
    reduceStream.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
