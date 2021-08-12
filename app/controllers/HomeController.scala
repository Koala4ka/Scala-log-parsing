package controllers

import dao.impl.ResidentDAOImpl
import play.api.libs.json.Json
import javax.inject._
import play.api.mvc._
import services.ResidentService

import scala.concurrent.ExecutionContext


@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               residentDAO: ResidentDAOImpl,
                               residentService: ResidentService)(implicit ex: ExecutionContext)
  extends AbstractController(cc) {

  def hello(name: String): Action[AnyContent] = Action {
    Ok(s"Hello, $name")
  }

  def getAll: Action[AnyContent] = Action.async {
    implicit request =>
      residentDAO.getAll.map {
        arg => Ok(Json.toJson(arg))
      }
  }

  def getByEventTypeInRange(eventType: String, from: Long, to: Long): Action[AnyContent] = Action.async { implicit request =>
    residentService
      .getAverageValueByTypeInRange(eventType, from, to)
      .map(avg => Ok(Json.toJson(avg)))
  }
}
