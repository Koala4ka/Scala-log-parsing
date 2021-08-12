package model


import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Resident(time: Long,
                    name: String,
                    value: Double) {


}

object Resident {
  implicit val format: OFormat[Resident] = Json.format[Resident]

  implicit val writer: BSONDocumentWriter[Resident] = Macros.writer[Resident]
  implicit val reader: BSONDocumentReader[Resident] = Macros.reader[Resident]
}
