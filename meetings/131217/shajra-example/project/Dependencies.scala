package org.yourorg.yourapp.build


import sbt._


trait Dependencies {

  private object v {
    val scalaZ = "7.1.0-M3"
    val typesafeConfig = "1.0.2"
  }

  val scalazCore = "org.scalaz" %% "scalaz-core" % v.scalaZ
  val scalazEffect = "org.scalaz" %% "scalaz-effect" % v.scalaZ
  val typesafeConfig = "com.typesafe"  % "config" % v.typesafeConfig

}
