package org.atxscala.injection.simple
package config


import org.springframework.beans.factory.config.ConfigurableBeanFactory._
import org.springframework.context.annotation.{Bean, Configuration, Scope}


import store._


trait StoreConfig {
  def store: FriendshipStore
}


@Configuration
class DbStoreConfig extends StoreConfig {

  @Bean @Scope(SCOPE_SINGLETON)
  def connection = DbConnection.fromDriverName("hello")

  @Bean override def store = new DbFriendshipStore(connection)

  @Bean def anotherStore = new DbFriendshipStore(connection)

}


@Configuration
class MemStoreConfig extends StoreConfig {

  @Bean override def store = new MemFriendshipStore

}
