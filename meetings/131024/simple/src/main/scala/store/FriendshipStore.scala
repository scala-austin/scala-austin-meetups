package org.atxscala.injection.spring
package store


trait FriendshipStore {

  def createUser(name: String, email: String): User

  def setFriendOf(src: Long, target: Long): Unit

  def friendsOf(src: Long): Set[User]

}
