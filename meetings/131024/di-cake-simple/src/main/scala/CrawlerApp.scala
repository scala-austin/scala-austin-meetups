package org.atxscala.injection.spring


import config._
import store.FriendshipStore


trait CrawlerApp extends App with AppConfig with StoreConfig {
  app.run
}


object TestCrawlerApp extends CrawlerApp with TestStoreConfig
object ProdCrawlerApp extends CrawlerApp with ProdStoreConfig
