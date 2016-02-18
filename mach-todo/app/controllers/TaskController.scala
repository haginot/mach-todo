package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class TaskController @Inject() extends Controller {

  implicit val task = Json.writes[TaskController.Task]

  def list = Action { implicit rc =>

    Ok(Json.toJson(TaskController.tasks))
  }

  def create = TODO

  def delete(taskId: Int) = TODO

}

object TaskController {

  case class Task(id: Long, title: String, status: String)

  val tasks = List(
    Task(1, "Do something", "DONE"),
    Task(2, "Do something else", "DONE"),
    Task(3, "Do more stuff", "NOTDONE"),
    Task(4, "Do that again", "NOTDONE")
  )

}
