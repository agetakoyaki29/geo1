
val junit = "junit" % "junit" % "4.11" % "test"
val junitinterface = "com.novocode" % "junit-interface" % "0.11" % "test"
val scalatest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val root = (project in file(".")).
  settings(
    // organization := "com.github",
    name := "scala geometry",
    version := "0.0",
    scalaVersion := "2.12.0",

    libraryDependencies += junit,
    // libraryDependencies += junitinterface,
    libraryDependencies += scalatest
  )
