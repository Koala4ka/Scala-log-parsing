package dao

import model.Resident

import scala.concurrent.Future

trait ResidentDAO {

  def save(resident: Resident): Future[Option[Int]]

  def getAll: Future[Seq[Resident]]

  def saveWithoutDuplicates(resident: Resident): Future[Option[Int]]
}
