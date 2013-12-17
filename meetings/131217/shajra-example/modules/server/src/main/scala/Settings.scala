package org.yourorg.yourapp


import com.typesafe.config.ConfigException

import scalaz.ValidationNel
import scalaz.syntax.apply._

import org.yourorg.extn.typesafeconfig.Configuration


case class ApiId(value: String) extends AnyVal
case class ApiKey(value: String) extends AnyVal
case class Settings(apiId: ApiId, apiKey: ApiKey)


object Settings {

  def fromConfig(config: Configuration)
      : ValidationNel[ConfigException, Settings] = {
    val apiId = config.getVNel[String]("yourapp.api_id") map ApiId
    val apiKey = config.getVNel[String]("yourapp.api_key") map ApiKey
    (apiId |@| apiKey)(Settings.apply)
  }

}
