package org.atxscala.injection.simple
package config


import store._


trait StoreModule {
  def store: FriendshipStore
}


trait DbStoreModule extends StoreModule {

  val connection: DbConnection

  override def store = new DbFriendshipStore(connection)

  def anotherStore = new DbFriendshipStore(connection)

}


trait MemStoreModule extends StoreModule {

  override def store = new MemFriendshipStore

}
