package edu.knoldus.Operation

import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


class FutureFileComplete {

  def getContent(directoryName: String): Unit = {
    val operationObject = new Operation
    val log = Logger.getLogger(this.getClass)
    operationObject.getFolderContents(directoryName) onComplete  {
      case Success(folderContent) => log.info(folderContent)
      case Failure(ex) =>  log.info(ex.getMessage)
    }
  }
}
