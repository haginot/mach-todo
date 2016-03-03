package repositories.rdb.task
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.H2Driver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Project.schema ++ Task.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Project
   *  @param projectId Database column project_id SqlType(bigint), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(varchar), Length(255,true)
   *  @param userUserId Database column user_user_id SqlType(bigint) */
  case class ProjectRow(projectId: Long, title: String, userUserId: Long)
  /** GetResult implicit for fetching ProjectRow objects using plain SQL queries */
  implicit def GetResultProjectRow(implicit e0: GR[Long], e1: GR[String]): GR[ProjectRow] = GR{
    prs => import prs._
    ProjectRow.tupled((<<[Long], <<[String], <<[Long]))
  }
  /** Table description of table project. Objects of this class serve as prototypes for rows in queries. */
  class Project(_tableTag: Tag) extends Table[ProjectRow](_tableTag, Some("public"), "project") {
    def * = (projectId, title, userUserId) <> (ProjectRow.tupled, ProjectRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(projectId), Rep.Some(title), Rep.Some(userUserId)).shaped.<>({r=>import r._; _1.map(_=> ProjectRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column project_id SqlType(bigint), AutoInc, PrimaryKey */
    val projectId: Rep[Long] = column[Long]("project_id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(varchar), Length(255,true) */
    val title: Rep[String] = column[String]("title", O.Length(255,varying=true))
    /** Database column user_user_id SqlType(bigint) */
    val userUserId: Rep[Long] = column[Long]("user_user_id")

    /** Foreign key referencing User (database name fk_project_user) */
    lazy val userFk = foreignKey("fk_project_user", userUserId, User)(r => r.userId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (title) (database name title_unique_index_1) */
    val index1 = index("title_unique_index_1", title, unique=true)
  }
  /** Collection-like TableQuery object for table Project */
  lazy val Project = new TableQuery(tag => new Project(tag))

  /** Entity class storing rows of table Task
   *  @param taskId Database column task_id SqlType(bigint), PrimaryKey
   *  @param title Database column title SqlType(varchar), Length(255,true)
   *  @param description Database column description SqlType(clob)
   *  @param dueDate Database column due_date SqlType(date)
   *  @param status Database column status SqlType(varchar), Length(45,true)
   *  @param userUserId Database column user_user_id SqlType(bigint)
   *  @param projectProjectId Database column project_project_id SqlType(bigint) */
  case class TaskRow(taskId: Long, title: String, description: Option[java.sql.Clob], dueDate: Option[java.sql.Date], status: String, userUserId: Long, projectProjectId: Long)
  /** GetResult implicit for fetching TaskRow objects using plain SQL queries */
  implicit def GetResultTaskRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Clob]], e3: GR[Option[java.sql.Date]]): GR[TaskRow] = GR{
    prs => import prs._
    TaskRow.tupled((<<[Long], <<[String], <<?[java.sql.Clob], <<?[java.sql.Date], <<[String], <<[Long], <<[Long]))
  }
  /** Table description of table task. Objects of this class serve as prototypes for rows in queries. */
  class Task(_tableTag: Tag) extends Table[TaskRow](_tableTag, Some("public"), "task") {
    def * = (taskId, title, description, dueDate, status, userUserId, projectProjectId) <> (TaskRow.tupled, TaskRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(taskId), Rep.Some(title), description, dueDate, Rep.Some(status), Rep.Some(userUserId), Rep.Some(projectProjectId)).shaped.<>({r=>import r._; _1.map(_=> TaskRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column task_id SqlType(bigint), PrimaryKey */
    val taskId: Rep[Long] = column[Long]("task_id", O.PrimaryKey)
    /** Database column title SqlType(varchar), Length(255,true) */
    val title: Rep[String] = column[String]("title", O.Length(255,varying=true))
    /** Database column description SqlType(clob) */
    val description: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("description")
    /** Database column due_date SqlType(date) */
    val dueDate: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("due_date")
    /** Database column status SqlType(varchar), Length(45,true) */
    val status: Rep[String] = column[String]("status", O.Length(45,varying=true))
    /** Database column user_user_id SqlType(bigint) */
    val userUserId: Rep[Long] = column[Long]("user_user_id")
    /** Database column project_project_id SqlType(bigint) */
    val projectProjectId: Rep[Long] = column[Long]("project_project_id")

    /** Foreign key referencing Project (database name fk_task_project1) */
    lazy val projectFk = foreignKey("fk_task_project1", projectProjectId, Project)(r => r.projectId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing User (database name fk_task_user1) */
    lazy val userFk = foreignKey("fk_task_user1", userUserId, User)(r => r.userId, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Task */
  lazy val Task = new TableQuery(tag => new Task(tag))

  /** Entity class storing rows of table User
   *  @param userId Database column user_id SqlType(bigint), PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(255,true) */
  case class UserRow(userId: Long, name: String)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Long], e1: GR[String]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, Some("public"), "user") {
    def * = (userId, name) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(bigint), PrimaryKey */
    val userId: Rep[Long] = column[Long]("user_id", O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
