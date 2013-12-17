package org.yourorg.extn


import com.typesafe.config.ConfigException

import org.yourorg.extn.scalaz.ReaderValidationNel


/** Functionality to assist working with the Typesafe Config library. */
package object typesafeconfig {

  type ConfigReader[A] = ReaderValidationNel[Configuration, A, ConfigException]


}
