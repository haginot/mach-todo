package repositories.rdb.task

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import repositories.rdb.task.Tables._
import repositories.rdb.task.Tables.profile.api._

import scala.concurrent.ExecutionContext

class ProjectRepository @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  /**
    * Fetching records from Project
    * TODO filter by conditions
    */
  def fetchProject() = db.run(Project.result)

  /**
    * To save Project
    * TODO FixedSqlAction as return type
    */
  def saveProject(model: ProjectModel) = {
    val row = ProjectRow(
      projectId = 0,
      title = "",
      userUserId = 0
    )
    Project += row
  }

}
