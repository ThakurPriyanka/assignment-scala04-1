package edu.knoldus


import Operation.Operation
import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Application {
  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val timeForSleep = 1000
    val operationObject = new Operation
    val folderName = "/home/knoldus/folder"
    operationObject.getFolderContents(folderName) onComplete {
      case Success(folderContent) => log.info(folderContent)
      case Failure(ex) => log.info(ex.getMessage)
      }
    Thread.sleep(timeForSleep)
    log.info("end")
  }
}
