name := """mach-todo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1",
  "com.h2database" % "h2" % "1.4.191"
)

resolvers ++= Seq(
// "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
 "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

play.PlayImport.PlayKeys.playDefaultPort := 9090

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// register manual sbt command
slick <<= slickCodeGenTask

// code generation task
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (baseDirectory, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "app").getPath // place generated files in sbt's managed sources folder
  val url = "jdbc:h2:mem:play;mode=mysql;INIT=runscript from 'conf/migration.sql'" // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run
  val jdbcDriver = "org.h2.Driver"
  val slickDriver = "slick.driver.H2Driver"
  val pkg = "models"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
  val fname = outputDir + "/models/Tables.scala"
  Seq(file(fname))
}