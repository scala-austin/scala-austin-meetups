package com.rackspace.dcx.deployer.build


import sbt._


trait Dependencies {

  private object v {
    val guava = "15.0"
    val spring = "3.2.4.RELEASE"
    val scalaCheck = "1.10.1"
    val scalaTest = "2.0.RC1-SNAP4"
    val scalaZ = "7.1.0-M3"  // pinned by Argonaut
  }

  val guava = "com.google.guava" % "guava" % v.guava
  val scalaCheck = "org.scalacheck" %% "scalacheck" % v.scalaCheck % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % v.scalaTest % "test"
  val scalazConcurrent = "org.scalaz" %% "scalaz-concurrent" % v.scalaZ
  val scalazCore = "org.scalaz" %% "scalaz-core" % v.scalaZ
  val scalazEffect = "org.scalaz" %% "scalaz-effect" % v.scalaZ
  val scalazScalacheck = "org.scalaz" %% "scalaz-scalacheck-binding" % v.scalaZ
  val springContext = "org.springframework" % "spring-context" % v.spring

}
