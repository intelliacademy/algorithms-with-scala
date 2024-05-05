ThisBuild / version := "0.1.1-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "algorithms-with-scala",
    idePackagePrefix := Some("com.intellibucket.lessons")
  )

