package org.atxscala.injection.simple
package store


trait FriendshipStore {

  def createUser(name: String, email: String): User

  def setFriendOf(src: UserId, target: UserId): Unit

  def friendsOf(src: UserId): Set[User]

}
