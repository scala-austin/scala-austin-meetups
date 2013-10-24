package org.atxscala.injection.existential


trait DbConnection


object DbConnection {
  def fromDriverName(driverName: String): DbConnection = {
    println(s"Loaded Driver: $driverName")
    new DbConnection() {}
  }
}
