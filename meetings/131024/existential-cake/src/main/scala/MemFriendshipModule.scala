package org.atxscala.injection.existential


import scala.collection.mutable.{HashMap, Map, MultiMap, Set}


trait MemFriendshipModule extends FriendshipModule {

  type User = LongUser
  type UserId = Long

  def createUser(name: String, email: String) =
    synchronized {
      val user = LongUser(nextId, name, email)
      users += (user.id -> user)
      nextId = nextId + 1
      user
    }

  def idOf(user: User): UserId = user.id

  def setFriendOf(src: UserId, target: UserId) =
    synchronized { friendships addBinding (src, target) }

  def friendsOf(src: UserId) =
    synchronized {
      (friendships get src).toSet.flatten.flatMap { users get _ }
    }

  case class LongUser(id: UserId, name: String, email: String)

  private var nextId: UserId = 1L

  private val users: Map[UserId, User] = Map.empty

  private val friendships: MultiMap[UserId, UserId] =
    new HashMap[UserId, Set[UserId]] with MultiMap[UserId, UserId]

}
