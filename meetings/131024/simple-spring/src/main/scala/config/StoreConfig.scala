package org.atxscala.injection.spring
package config


import org.springframework.context.annotation.{Bean, Configuration}

import store._


trait StoreConfig {
  def store: FriendshipStore
}


@Configuration
class ProdStoreConfig extends StoreConfig {

  val connection = DbConnection

  @Bean override def store = new DbFriendshipStore(connection)

}


@Configuration
class TestStoreConfig extends StoreConfig {

  @Bean override def store = new MemFriendshipStore

}
