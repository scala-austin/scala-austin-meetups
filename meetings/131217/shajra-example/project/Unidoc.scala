package org.yourorg.yourapp.build


import sbt._
import sbt.Keys.{scalacOptions, doc, sourceDirectory}

import sbtunidoc.Plugin


trait Unidoc {

  private val rootDocPath = "main/scaladoc/rootdoc.txt"

  def unidocSettings = Plugin.unidocSettings :+ rootContentSetting

  private def rootContentSetting =
    scalacOptions in (Compile, doc) <++=
      sourceDirectory map { sd =>
        Seq("-doc-root-content", (sd / rootDocPath).getAbsolutePath) }


}
