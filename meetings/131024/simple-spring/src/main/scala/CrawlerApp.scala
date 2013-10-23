package org.atxscala.injection.spring


import org.springframework.context.annotation.
  AnnotationConfigApplicationContext

import config._
import store.FriendshipStore


object CrawlerApp {

  def test = loadApp(classOf[TestStoreConfig])

  def prod = loadApp(classOf[ProdStoreConfig])

  private def loadApp[C <: StoreConfig](storeConfig: Class[C]) = {
    new AnnotationConfigApplicationContext(classOf[AppConfig], storeConfig)
      .getBean(classOf[Runnable])
  }

}


object TestCrawlerApp extends App { CrawlerApp.test.run }
object ProdCrawlerApp extends App { CrawlerApp.prod.run }
