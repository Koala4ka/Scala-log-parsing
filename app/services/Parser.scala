package services

import dao.ResidentDAO
import model.Resident

import java.io.File
import scala.io.Source
import monix.execution.Scheduler.{global => scheduler}

import scala.concurrent.duration.DurationInt

class Parser (residentDAO:ResidentDAO) {

  def fileReader(): Stream[Resident] = {
    val filePath = "app/resources/resident-samples.log"
    val file = new File(filePath)
    lazy val allLines = Source.fromFile(file).mkString.split("\n").toStream
    allLines
      .map(s => {
        val fields = s.split(",")
        Resident(
          fields(0).trim.toLong,
          fields(1).trim,
          fields(2).trim.toDouble,
        )
      })
  }
    scheduler.scheduleWithFixedDelay(0.minute, 2.minutes) {
      println("PROCESSING...")
     fileReader().map(s => {
       residentDAO.saveWithoutDuplicates(s)
     })
    }

}
