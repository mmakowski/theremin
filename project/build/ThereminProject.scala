import sbt._

class ThereminProject(info: ProjectInfo) extends DefaultProject(info) {
  // repositories to use
  val scalaToolsReleases = ScalaToolsReleases

  // dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"
  
  // test listeners
  def junitXmlListener: TestReportListener = new eu.henkelmann.sbt.JUnitXmlTestsListener(outputPath.toString)
  override def testListeners: Seq[TestReportListener] = super.testListeners ++ Seq(junitXmlListener)
}

