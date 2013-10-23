package org.atxscala.injection.spring
package config


import store._


trait StoreConfig {
  def store: FriendshipStore
}


trait ProdStoreConfig extends StoreConfig {

  val connection = DbConnection

  override def store = new DbFriendshipStore(connection)

}


trait TestStoreConfig extends StoreConfig {

  override def store = new MemFriendshipStore

}
