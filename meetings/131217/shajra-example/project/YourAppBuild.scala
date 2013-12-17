package org.yourorg.yourapp.build


import sbt._
import sbt.Keys._


object YourAppBuild extends Build
    with Assembly
    with Dependencies
    with DependencyGraph
    with GitVersion
    with Unidoc {

  override lazy val settings =
    super.settings ++
    gitVersionSettings ++
      Seq(

        organization := "org.yourorg",
        organizationName := "Your Team - Your Org",
        startYear := Some(2013),
        scalaVersion := "2.10.3",
        resolvers += "spray repo" at "http://repo.spray.io/",

        scalacOptions ++= Seq(
          "-feature",
          "-language:existentials",
          "-language:higherKinds",
          "-language:implicitConversions",
          "-language:postfixOps",
          "-optimize",
          "-deprecation",
          "-Xlint",
          "-Xfatal-warnings",
          "-Ywarn-all",
          "-Yinline-warnings"
          //"-Xlog-implicits"
          ),

        // D - show durations
        // S - show short stack traces
        // F - show full stack traces
        // W - without color
        testOptions in Test += Tests.Argument("-oDS"))

  lazy val root =
    Project("yourorg-yourapp", file("."))
      .settings(run <<= run in (server, Compile))
      .aggregate(extn, server, unidoc)

  lazy val unidoc =
    Project("yourorg-yourapp-unidoc", file("unidoc"))
      .settings(unidocSettings: _*)
      .aggregate(
        extnScalaz,
        extnTypesafeConfig,
        server)

  lazy val server =
    Project("yourorg-yourapp-server", file("modules/server"))
      .settings(assemblySettings: _*)
      .settings(graphSettings: _*)
      .settings(
        libraryDependencies ++=
          Seq(
            scalazCore,
            scalazEffect,
            typesafeConfig))
      .dependsOn(
        extnScalaz, extnTypesafeConfig)

  lazy val extn =
    Project("yourorg-extn", file("modules/extn"))
      .aggregate(extnScalaz, extnTypesafeConfig)

  lazy val extnScalaz =
    Project("yourorg-extn-scalaz", file("modules/extn/scalaz"))
      .settings(libraryDependencies += scalazCore)

  lazy val extnTypesafeConfig =
    Project("yourorg-extn-typesafeconfig", file("modules/extn/typesafeconfig"))
      .settings(
        libraryDependencies ++= Seq(typesafeConfig, scalazEffect, scalazCore))
      .dependsOn(extnScalaz)

}
