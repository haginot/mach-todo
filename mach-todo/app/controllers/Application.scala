package controllers

import javax.inject.Inject

import dao.TaskDAO
import models.Task
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class Application @Inject()(taskDAO: TaskDAO) extends Controller {

  implicit val taskWrites = Json.writes[Task]
  implicit val taskReads  = Json.reads[Task]

  def index = Action {
    Ok("Mach ToDo is ready.")
  }

  def list = Action.async {
    taskDAO.all().map { tasks => Ok(Json.toJson(tasks)) }
  }

  def create = Action.async(parse.json) { implicit rs =>
    rs.body.validate[Task].fold(
      error => Future(BadRequest(Json.obj("message" -> error.toString))),
      form  => taskDAO.insert(form).map { _ => Ok(Json.obj("message" -> "Data has been inserted.")) }
    )
  }

}
