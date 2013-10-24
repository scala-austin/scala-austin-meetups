package org.atxscala.injection.simple


import store._


object TestCrawlerApp extends App {
  val store = new MemFriendshipStore
  val app = new ExampleCrawl(store)
  app.run
}


object ProdCrawlerApp extends App {
  val driverName = "mydatabase.driver.name"
  val connection = DbConnection fromDriverName driverName
  val store = new DbFriendshipStore(connection)
  val app = new ExampleCrawl(store)
  app.run
}
