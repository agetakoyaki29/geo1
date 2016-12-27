
val junit = "junit" % "junit" % "4.11" % "test"
val junitinterface = "com.novocode" % "junit-interface" % "0.11" % "test"
val scalactic = "org.scalactic" %% "scalactic" % "2.2.5"
val scalatest = "org.scalatest" %% "scalatest" % "2.2.5" % "test"

lazy val root = (project in file(".")).
  settings(
    // organization := "com.github",
    name := "scala geometry",
    version := "0.0",
    scalaVersion := "2.11.8",

    libraryDependencies += junit,
    // libraryDependencies += junitinterface,
    libraryDependencies += scalactic,
    libraryDependencies += scalatest,

    logBuffered in Test := false
  )
