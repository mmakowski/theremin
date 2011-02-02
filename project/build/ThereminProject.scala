import sbt._

class ThereminProject(info: ProjectInfo) extends DefaultProject(info) {
  // repositories to use
  val scalaToolsReleases = ScalaToolsReleases

  // dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.2"
}

