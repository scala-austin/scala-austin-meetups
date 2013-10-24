package org.atxscala.injection.simple


import org.springframework.context.annotation.
  AnnotationConfigApplicationContext

import config._
import store.FriendshipStore


object CrawlerApp {

  def test = loadApp(classOf[MemStoreModule])

  def prod = loadApp(classOf[DbStoreModule])

  private def loadApp[C <: StoreModule](storeModule: Class[C]) = {
    new AnnotationConfigApplicationContext(classOf[AppModule], storeModule)
      .getBean(classOf[Runnable])
  }

}


object TestCrawlerApp extends App { CrawlerApp.test.run }
object ProdCrawlerApp extends App { CrawlerApp.prod.run }
