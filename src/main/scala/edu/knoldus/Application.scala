package edu.knoldus


import Operation.{FutureFileComplete, Operation}
import org.apache.log4j.Logger


object Application {
  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val timeForSleep = 1000
    val fileObject = new FutureFileComplete
    val folderName = "/home/knoldus/folder"
    fileObject.getContent(folderName)
    Thread.sleep(timeForSleep)
    log.info("end")
  }
}
