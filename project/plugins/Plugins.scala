import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  // repositories to use
  val christophHenkelmannsRepo = "Christoph's Maven Repo" at "http://maven.henkelmann.eu/"

  // plugins
  val junitXml = "eu.henkelmann" % "junit_xml_listener" % "0.2"
}
