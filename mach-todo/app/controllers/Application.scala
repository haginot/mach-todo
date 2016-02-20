package controllers

import javax.inject.Inject

import dao.TaskDAO
import play.api.mvc._

class Application @Inject()(taskDAO: TaskDAO) extends Controller {

  def index = Action {
    Ok("Mach ToDo is ready.")
  }

}
