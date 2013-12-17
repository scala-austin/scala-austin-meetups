package org.yourorg.yourapp


import scalaz.{NonEmptyList, Show}
import scalaz.std.list._
import scalaz.std.option._
import scalaz.std.string._
import scalaz.syntax.foldable._
import scalaz.syntax.nel._
import scalaz.syntax.std.option._


sealed abstract class Fault {
  def summary: String
}


private case class ConfigFault
    (summary: String, details: NonEmptyList[Exception])
    extends Fault


object Fault {

  def singleConfig(summary: String)(cause: Exception): Fault =
    config(summary)(cause.wrapNel)

  def config
      (summary: String)
      (details: NonEmptyList[Exception])
      : Fault =
    ConfigFault(summary, details)

  implicit val show: Show[Fault] =
    Show shows { fault =>
      val lines: List[String] = fault match {
        case ConfigFault(summary, details) =>
          List(
            header(summary),
            details
              .map { d => s" - ${d.getMessage}" }
              .intercalate("\n"))
      }
      lines.intercalate("\n")
    }

  private def header(s: String) = s"\n${titleCase(s)}\n" + ("-" * s.length)

  private def titleCase(s: String) = s(0).toUpper + s.substring(1)

}
