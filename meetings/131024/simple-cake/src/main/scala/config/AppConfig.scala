package org.atxscala.injection.spring
package config


trait AppConfig { self: StoreConfig =>
  def app: Runnable = new ExampleCrawl(store)
}
