package org.atxscala.injection.simple


import config._
import store._


trait CrawlerApp extends App with AppModule with StoreModule {
  app.run
}


object TestCrawlerApp extends CrawlerApp with MemStoreModule


object ProdCrawlerApp
    extends {
      val connection = DbConnection fromDriverName "mydatabase.driver.name"
    }
    with CrawlerApp with DbStoreModule
