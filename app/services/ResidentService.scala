package services

import com.google.inject.Inject
import dao.ResidentDAO

import scala.concurrent.{ExecutionContext, Future}

class ResidentService @Inject()(residentDAO: ResidentDAO)
                               (implicit ex: ExecutionContext) {

  def getAverageValueByTypeInRange(eventType: String, from: Long, to: Long): Future[Double] = {
    residentDAO
      .getAll
      .map(
        seq => {
         val values = seq
           .filter(r => r.name == eventType && r.time >= from && r.time <= to)
           .map(_.value)
          val count = values.length
          val sum = values.sum

          sum / count
        }
      )
  }

}
