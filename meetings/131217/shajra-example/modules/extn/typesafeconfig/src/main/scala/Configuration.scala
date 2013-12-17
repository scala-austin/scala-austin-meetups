package org.yourorg.extn.typesafeconfig


import java.io.File

import scala.collection.JavaConverters._

import com.typesafe.config.{
  Config, ConfigException, ConfigFactory, ConfigParseOptions, ConfigObject,
  ConfigResolveOptions}

import scalaz.{\/, EitherT, Validation, ValidationNel}
import scalaz.effect.IO
import scalaz.std.option._
import scalaz.syntax.std.option._
import scalaz.syntax.validation._


final class Configuration private[typesafeconfig] (val asTypesafe: Config) {

  def apply[A: ConfigAccessor](path: String): Validation[ConfigException,A] =
    get(path)

  def get[A: ConfigAccessor](path: String): Validation[ConfigException, A] =
    implicitly[ConfigAccessor[A]].apply(asTypesafe, path)

  def getVNel[A: ConfigAccessor](path: String)
      : ValidationNel[ConfigException, A] =
    get(path).toValidationNel

  def getOrMissing[A: ConfigAccessor](path: String)
      : Validation[ConfigException, Option[A]] = {
    def ifLeft(e: ConfigException) =
      e match {
        case _: ConfigException.Missing => none.success
        case _ => e.fail
      }
    def ifRight(a: A) = a.some.success
    get(path) fold (ifLeft, ifRight)
  }

  def keys(path: String): Validation[ConfigException, Iterable[String]] =
    get[ConfigObject](path) map { _.keySet.asScala }

  def overriding(as: (String, String)*): Configuration =
    new Configuration(
      ConfigFactory.parseMap(as.toMap.asJava).withFallback(asTypesafe))

  def withFallback(other: Configuration): Configuration =
    new Configuration(asTypesafe withFallback other.asTypesafe)

}


object Configuration {

  def loadStandard: EitherT[IO, ConfigException, Configuration] =
    safeLoad { ConfigFactory load }

  def load
      (resourceBasename: String)
      : EitherT[IO, ConfigException, Configuration] =
    safeLoad { ConfigFactory load (resourceBasename, parseOpts, resolveOpts) }

  def loadFile(file: File): EitherT[IO, ConfigException, Configuration] =
    safeLoad { ConfigFactory parseFileAnySyntax (file, parseOpts) }

  implicit def unwrap(config: Configuration): Config = config.asTypesafe

  private val parseOpts = ConfigParseOptions.defaults.setAllowMissing(false)

  private val resolveOpts = ConfigResolveOptions.defaults

  private def safeLoad(config: => Config)
      : EitherT[IO, ConfigException, Configuration] =
    EitherT(
      IO {
        new Configuration(config)
      } catchSomeLeft {
        case e: ConfigException => Some(e)
        case _ => None
      }
    )

}
