package org.atxscala.injection.existential


trait FriendshipModule {

  type User
  type UserId

  def createUser(name: String, email: String): User

  def idOf(user: User): UserId

  def setFriendOf(src: UserId, target: UserId): Unit

  def friendsOf(src: UserId): Set[User]

}
