package org.atxscala.injection.simple


import org.springframework.context.annotation.
  AnnotationConfigApplicationContext

import config._
import store.FriendshipStore


object CrawlerApp {

  def test = loadApp(classOf[MemStoreConfig])

  def prod = loadApp(classOf[DbStoreConfig])

  private def loadApp[C <: StoreConfig](storeConfig: Class[C]) = {
    new AnnotationConfigApplicationContext(classOf[AppConfig], storeConfig)
      .getBean(classOf[Runnable])
  }

}


object TestCrawlerApp extends App { CrawlerApp.test.run }
object ProdCrawlerApp extends App { CrawlerApp.prod.run }
