package dao

import javax.inject.Inject

import models.Task
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

import scala.concurrent.Future

class TaskDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Tasks = TableQuery[TasksTable]

  def all(): Future[Seq[Task]] = db.run(Tasks.result)

  def insert(task: Task): Future[Unit] = db.run(Tasks += task).map { _ => () }

  private class TasksTable(tag: Tag) extends Table[Task](tag, "TASK") {

    def name = column[String]("TITLE", O.PrimaryKey)
    def status = column[String]("STATUS")

    def * = (name, status) <> (Task.tupled, Task.unapply _)
  }

}
