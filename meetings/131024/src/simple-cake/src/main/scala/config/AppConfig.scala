package org.atxscala.injection.simple
package config


trait AppConfig {
  
  self: StoreConfig =>

  def app: Runnable = new ExampleCrawl(store)

}
