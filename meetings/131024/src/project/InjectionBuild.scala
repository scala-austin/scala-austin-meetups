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
      .aggregate(
        simple,
        simpleSpring,
        simpleCake,
        simpleManual,
        existentialCake,
        oddends)

  lazy val simple =
    Project("di-simple", file("simple"))

  lazy val simpleSpring =
    Project("di-simple-spring", file("simple-spring"))
      .settings(libraryDependencies += springContext)
      .dependsOn(simple)

  lazy val simpleCake =
    Project("di-simple-cake", file("simple-cake"))
      .dependsOn(simple)

  lazy val simpleManual =
    Project("di-simple-manual", file("simple-manual"))
      .dependsOn(simple)

  lazy val existentialCake =
    Project("di-existential-cake", file("existential-cake"))

  lazy val oddends =
    Project("di-oddends", file("oddends"))

}
