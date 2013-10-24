package org.atxscala.injection.simple
package store


import scala.collection.mutable.{HashMap, Map, MultiMap, Set}


final class MemFriendshipStore extends FriendshipStore {

  def createUser(name: String, email: String) =
    synchronized {
      val user = new User(nextId, name, email)
      users += (user.id -> user)
      nextId = nextId + 1
      user
    }

  def setFriendOf(src: UserId, target: UserId) =
    synchronized { friendships addBinding (src, target) }

  def friendsOf(src: UserId) =
    synchronized {
      (friendships get src).toSet.flatten.flatMap { users get _ }
    }

  private var nextId: UserId = 1L

  private val users: Map[UserId, User] = Map.empty

  private val friendships: MultiMap[UserId, UserId] =
    new HashMap[UserId, Set[UserId]] with MultiMap[UserId, UserId]

}
