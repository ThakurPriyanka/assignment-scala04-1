package edu.knoldus.Operation

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.sys.process._

class Operation {
  def getFolderContent(folderName: String): Future[List[String]] = Future {
    val result = s"ls -R $folderName" !!

    if (result == Nil) {
      throw new ReflectiveOperationException("Can not find the path")
    }
    else {
      val list = result.split("\n")
      list.to[List]
    }
  }

  def getFolderContents(folderName: String): Future[List[File]] = Future {
    def getListOfFiles(listOfDirector: List[File], listOfFileContent: List[File]): List[File] = {
      listOfDirector match {
        case director :: tail =>
          if (director.exists && director.isDirectory) {
            getListOfFiles(tail ::: director.listFiles.filter(_.isDirectory).toList, listOfFileContent ::: director.listFiles.filter(_.isFile).toList)
          }
          else if (!director.exists()) {
            throw new Exception("Director does not exits")
          }
          else {
            getListOfFiles(tail, listOfFileContent)
          }
        case Nil => listOfFileContent
      }
    }

    val listOfFinalContent: List[File] = List()
    val listOfDirector: List[File] = List(new File(folderName))
    getListOfFiles(listOfDirector, listOfFinalContent)
  }
}
