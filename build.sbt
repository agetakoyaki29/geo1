
lazy val root = (project in file("."))
  .settings(
    name := "scala geometry",
    version := "0.0",
    scalaVersion := "2.12.0",

    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.11" % "test"
    )
  )
