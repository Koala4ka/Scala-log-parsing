package dao.impl

import com.google.inject.Inject
import dao.ResidentDAO
import model.Resident
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.{ExecutionContext, Future}
import model.Resident._
import reactivemongo.api.bson.{BSONDocument, ElementProducer}
import reactivemongo.api.bson.collection.BSONCollection

class ResidentDAOImpl @Inject()(reactiveMongoApi: ReactiveMongoApi)
                               (implicit ex: ExecutionContext) extends ResidentDAO {

  import reactivemongo.play.json.compat
  import compat.json2bson._

  implicit val collection: Future[BSONCollection] =
    reactiveMongoApi.database.map(_.collection("resident_collection"))

  def getAll: Future[Seq[Resident]] = getMany()

  def getMany(filter: ElementProducer*): Future[Seq[Resident]] = getManyFilter(BSONDocument(filter: _*))

  def getManyFilter(filter: BSONDocument): Future[Seq[Resident]] = collection.flatMap(
    _.find(filter)
      .cursor[Resident]()
      .collect[Seq]()
  )

  override def save(resident: Resident): Future[Option[Int]] = collection.flatMap {
    _.insert.one(resident).map(_.code)
  }

  override def saveWithoutDuplicates(resident: Resident): Future[Option[Int]] = for {
    existingColl <- getAll
    contains = existingColl.contains(resident)
    resultCode <- contains match {
      case true => Future {
        None
      }
      case false =>
        collection.flatMap {
          _.insert.one(resident)
            .map(_.code)
        }
    }
  } yield resultCode
}








