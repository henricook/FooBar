import NativePackagerKeys._

val app = crossProject.settings(
  name := "FooBar",
  version := "0.1",
  scalaVersion := "2.11.8",
  unmanagedSourceDirectories in Compile += baseDirectory.value  / "shared" / "src" / "main" / "scala",
  libraryDependencies ++= Seq(
    "com.lihaoyi"                  %%% "scalatags"     % "0.5.0",
    "com.lihaoyi"                  %%% "upickle"       % "0.4.1",
    "com.github.japgolly.scalacss" %%% "core"          % "0.4.1",
    "com.github.japgolly.scalacss" %%% "ext-scalatags" % "0.4.1"
  )
).jsSettings(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0"
  )
).jvmSettings(
  libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-dsl"          % "0.14.1a",
    "org.http4s" %% "http4s-blaze-server" % "0.14.1a"
  )
)

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
  (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data
)