package org.atxscala.injection.existential


import java.util.UUID
import scala.collection.mutable.{HashMap, Map, MultiMap, Set}


trait DbFriendshipModule extends FriendshipModule {

  type User = UuidUser
  type UserId = UUID

  // we'll just pretend like we're using this DB connection, even though we're
  // actually just doing in-memory calculations
  //
  val connection: DbConnection

  def createUser(name: String, email: String) =
    synchronized {
      val user = UuidUser(nextId, name, email)
      users += (user.id -> user)
      user
    }

  def idOf(user: User): UserId = user.id

  def setFriendOf(src: UserId, target: UserId) =
    synchronized { friendships addBinding (src, target) }

  def friendsOf(src: UserId) =
    synchronized {
      (friendships get src).toSet.flatten.flatMap { users get _ }
    }

  case class UuidUser(id: UserId, name: String, email: String)

  private def nextId: UserId = UUID.randomUUID

  private val users: Map[UserId, User] = Map.empty

  private val friendships: MultiMap[UserId, UserId] =
    new HashMap[UserId, Set[UserId]] with MultiMap[UserId, UserId]

}
