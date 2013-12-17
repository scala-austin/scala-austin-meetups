package org.yourorg.yourapp


import java.io.File

import scalaz.EitherT
import scalaz.effect.{IO, SafeApp}
import scalaz.syntax.bind._
import scalaz.syntax.traverse._
import scalaz.std.list._

import org.yourorg.extn.typesafeconfig.Configuration


import com.typesafe.config.ConfigException


sealed abstract class YourAppBase
    (mainConfig: EitherT[IO, ConfigException, Configuration]*)
    extends SafeApp {

  override def runl(configPaths: List[String]): IO[Unit] = {
    val work = loadConfig(configPaths) >>= parseSettings >>= doWork
    work.run >>= { _ fold (handleFaults, _ => IO.ioUnit) }
  }

  private def loadConfig
      (configFilePaths: List[String]): EitherT[IO, Fault, Configuration] = {
    val custom = configFilePaths map { Configuration loadFile new File(_) }
    val all = custom ++ mainConfig
    all.sequenceU
      .map { _ reduceLeft { _ withFallback _ } }
      .leftMap(Fault singleConfig "problem loading a configuration file")
  }

  private def parseSettings
      (config: Configuration): EitherT[IO, Fault, Settings] =
    EitherT(IO {
      Settings.fromConfig(config)
        .leftMap(Fault config "problem parsing configurations")
        .disjunction
    })

  private def doWork(settings: Settings): EitherT[IO, Fault, Unit] =
    EitherT right (
      IO.putStrLn(s"API ID: ${settings.apiId.value}") *>
        IO.putStrLn(s"API key: ${settings.apiKey.value}")
    )

  private def handleFaults(result: Fault): IO[Unit] =
    IO putLn result

}


object YourApp extends YourAppBase(Configuration.loadStandard)


object DevYourApp
  extends YourAppBase(
    Configuration.loadFile(new File("private.conf")),
    Configuration.load("dev"))
