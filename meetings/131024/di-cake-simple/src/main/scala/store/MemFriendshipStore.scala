package org.atxscala.injection.spring
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

  def setFriendOf(src: Long, target: Long) =
    synchronized { friendships addBinding (src, target) }

  def friendsOf(src: Long) =
    synchronized {
      (friendships get src).toSet.flatten.flatMap { users get _ }
    }

  private var nextId: Long = 1L

  private val users: Map[Long, User] = Map.empty

  private val friendships: MultiMap[Long, Long] =
    new HashMap[Long, Set[Long]] with MultiMap[Long, Long]

}
