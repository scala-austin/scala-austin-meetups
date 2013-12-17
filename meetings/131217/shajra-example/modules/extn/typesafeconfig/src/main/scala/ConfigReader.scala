package org.yourorg.extn.typesafeconfig


import scalaz.syntax.validation._

import org.yourorg.extn.scalaz.ReaderNel


object ConfigReader {

  def apply[A : ConfigReader]: ConfigReader[A] =
    implicitly[ConfigReader[A]]

  def read[A : ConfigAccessor](path: String): ConfigReader[A] =
    ReaderNel { ConfigAccessor[A] apply (_, path) toValidationNel }

  def readOrMissing[A : ConfigAccessor](path: String)
      : ConfigReader[Option[A]] =
    ReaderNel { _.getOrMissing(path).toValidationNel }

  def ask: ConfigReader[Configuration] =
    ReaderNel { _.success }

  def const[A](a: A): ConfigReader[A] =
    ReaderNel { _ => a.success }

}
