package org.yourorg.yourapp.build


import sbt.Keys.mainClass

import sbtassembly.Plugin
import sbtassembly.Plugin.AssemblyKeys


trait Assembly {

  private val mainClassName = "org.yourorg.yourapp.YourApp"

  val assembly = AssemblyKeys.assembly

  val assemblySettings =
    Plugin.assemblySettings :+ (mainClass in assembly := Some(mainClassName))

}
