package org.atxscala.injection.existential


trait CrawlerApp
    extends App
    with CrawlerModule
    with ExampleCrawlModule 
    with FriendshipModule {

  run

}


object TestCrawlerApp extends MemFriendshipModule with CrawlerApp


object ProdCrawlerApp
    extends {
      val connection = DbConnection fromDriverName "mydatabase.driver.name"
    }
    with DbFriendshipModule with CrawlerApp
