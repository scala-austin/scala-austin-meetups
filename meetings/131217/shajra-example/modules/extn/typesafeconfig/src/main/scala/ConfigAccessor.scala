package org.yourorg.extn.typesafeconfig


import java.io.File

import scala.collection.JavaConverters.asScalaBufferConverter

import com.typesafe.config.{Config, ConfigException, ConfigObject}

import scalaz.Validation
import scalaz.syntax.validation._


trait ConfigAccessor[A]
    extends ((Config, String) => Validation[ConfigException, A])


object ConfigAccessor {

  def apply[A : ConfigAccessor]: ConfigAccessor[A] =
    implicitly[ConfigAccessor[A]]

  implicit object IntAccessor extends ConfigAccessor[Int] {
    def apply(c: Config, s: String) = tryCatch { c getInt s }
  }

  implicit object StringAccessor extends ConfigAccessor[String] {
    def apply(c: Config, s: String) = tryCatch { c getString s }
  }

  implicit object SeqStringAccessor extends ConfigAccessor[Seq[String]] {
    def apply(c: Config, s: String) = tryCatch { c getString s split "," }
  }

  implicit object ListStringAccessor extends ConfigAccessor[List[String]] {
    def apply(c: Config, s: String) =
      tryCatch { c.getStringList(s).asScala.toList }
  }

  implicit object LongAccessor extends ConfigAccessor[Long] {
    def apply(c: Config, s: String) = tryCatch { c getLong s }
  }

  implicit object BooleanAccessor extends ConfigAccessor[Boolean] {
    def apply(c: Config, s: String) = tryCatch { c getBoolean s }
  }

  implicit object FileAccessor extends ConfigAccessor[File] {
    def apply(c: Config, s: String) = tryCatch { new File(c getString s) }
  }

  implicit object ConfigAccessor extends ConfigAccessor[Config] {
    def apply(c: Config, s: String) = tryCatch { c getConfig s }
  }

  implicit object ConfigurationAccessor extends ConfigAccessor[Configuration] {
    def apply(c: Config, s: String) =
      tryCatch { new Configuration(c getConfig s) }
  }

  implicit object ConfigObjectAccessor extends ConfigAccessor[ConfigObject] {
    def apply(c: Config, s: String) = tryCatch { c getObject s }
  }

  private def tryCatch[A](a: => A): Validation[ConfigException, A] =
    try { a.success }
    catch { case e: ConfigException => e.fail }

}
