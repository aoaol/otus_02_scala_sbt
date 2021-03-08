import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

val circeVersion = "0.12.0"

lazy val root = (project in file("."))
  .settings(
    name := "africa"

  , libraryDependencies += scalaTest % Test
  , libraryDependencies += "io.circe" %% "circe-core" % circeVersion
  , libraryDependencies += "io.circe" %% "circe-generic" % circeVersion
  , libraryDependencies += "io.circe" %% "circe-parser" % circeVersion
  , libraryDependencies += "io.circe" %% "circe-optics" % circeVersion

//    libraryDependencies ++= Seq(
//      "io.circe"    %% "circe-iteratee" % "0.13.0-M2",
//      "io.iteratee" %% "iteratee-files" % "0.19.0"
//    ).map(_ % circeVersion)
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
