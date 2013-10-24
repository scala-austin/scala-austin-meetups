package org.atxscala.injection.simple
package store


trait DbConnection


object DbConnection {
  def fromDriverName(driverName: String): DbConnection = {
    println(s"Loaded Driver: $driverName")
    new DbConnection() {}
  }
}
