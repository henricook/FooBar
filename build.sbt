import sbt._

enablePlugins(ScalaJSPlugin, SbtWeb)

name := "FooBar"
version := "0.1"
scalaVersion := "2.11.8"
resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.scala-js"    %%% "scalajs-dom"    % "0.9.0",
  "com.lihaoyi"     %%% "scalatags"      % "0.5.4",
  "be.doeraene"     %%% "scalajs-jquery" % "0.9.0",

  "org.scalatest"   %%% "scalatest"      % "3.0.0-M7"  % "test"
)

// scala.js
skip in packageJSDependencies := false
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
persistLauncher in Compile := true
persistLauncher in Test := false

// sbt-less
LessKeys.compress in Assets := false
includeFilter in (Assets, LessKeys.less) := "main.less"