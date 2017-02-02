package com.example.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by vikesh on 2/1/2017.
  */
object labassignment2 {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("wordCount") .set("spark.eventLog.enabled", "true")
    System.setProperty("hadoop.home.dir", "C:/Users/Vikesh/Documents/UMKC Subjects//PB/hadoopforspark/")

    val inputFile = "data/textfile.txt"

    val outputFile = "data/wordcount"

    val sc = new SparkContext(conf)

    val input =  sc.textFile(inputFile)

    val words = input.flatMap(line => line.split("\\W+"))
    words.foreach(f=>println(f))

    val counts = words.map(words => (words, 1)).reduceByKey{case (x, y) => x + y}

    val tf=counts.sortBy(f=>f._2,ascending = false)
    tf.foreach(f=>println(f))
    tf.saveAsTextFile(outputFile)

    tf.take(10).foreach(f=>println(f))
    sc.stop()

  }

}
