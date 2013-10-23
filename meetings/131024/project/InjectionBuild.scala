package com.rackspace.dcx.deployer.build


import sbt._
import sbt.Keys._


object InjectionBuild extends Build with Dependencies {

  override lazy val settings =
    super.settings ++
      Seq(
        scalaVersion := "2.10.3",
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
        testOptions in Test += Tests.Argument("-oDS")
      )

  lazy val root =
    Project("di", file("."))
      .aggregate(spring, cakeSimple)

  lazy val spring =
    Project("di-spring", file("di-spring"))
      .settings(libraryDependencies += springContext)

  lazy val cakeSimple =
    Project("di-cake-simple", file("di-cake-simple"))

}
