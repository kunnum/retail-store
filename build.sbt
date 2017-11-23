lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging)

name := "retal-store"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

publishArtifact in (Test, packageBin) := true

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )
}

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports")
