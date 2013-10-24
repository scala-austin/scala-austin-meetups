package org.atxscala.injection.simple
package config


import store._


trait StoreConfig {
  def store: FriendshipStore
}


trait DbStoreConfig extends StoreConfig {

  val connection: DbConnection

  override def store = new DbFriendshipStore(connection)

  def anotherStore = new DbFriendshipStore(connection)

}


trait MemStoreConfig extends StoreConfig {

  override def store = new MemFriendshipStore

}
