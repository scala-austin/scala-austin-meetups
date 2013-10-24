package org.atxscala.injection.simple
package config


trait AppModule {

  self: StoreModule =>

  def app: Runnable = new ExampleCrawl(store)

}
