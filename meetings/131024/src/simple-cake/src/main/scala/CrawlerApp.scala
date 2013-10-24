package org.atxscala.injection.simple


import config._
import store._


trait CrawlerApp extends App with AppConfig with StoreConfig {
  app.run
}


object TestCrawlerApp extends CrawlerApp with MemStoreConfig


object ProdCrawlerApp
    extends {
      val connection = DbConnection fromDriverName "mydatabase.driver.name"
    }
    with CrawlerApp with DbStoreConfig
