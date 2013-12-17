package org.yourorg.yourapp.build


import java.io.File

import sbt._
import sbt.Keys.version


trait GitVersion {

  def gitVersionSettings = Seq(version := gitVersion)

  private def gitVersion = {
    val v = versionFor(gitDescribe)
    ConsoleLogger() info ("derived version from Git: " + v)
    v
  }

  private def versionFor(desc: String) = {
    val regex = """^v(\d+).(\d+)-(\d+)-(.*)$""".r
    desc match {
      case regex(a, b, c, d) => Seq(a, ".", b, ".", c, "-", d).mkString
      case _ =>  "0.1.0-SNAPSHOT"  // alternatively: sys.error(errorMsg)
    }
  }

  private def gitDescribe = Process(descCmd).lines.head

  private val descCmd =
    Seq("git", "describe", "--long", "--always", "--dirty", "--match=v*.*")

  private val errorMsg =
    "couldn't derive version number from Git" +
      " (is there an annotated tag of the form 'v*.*'?)"

}
