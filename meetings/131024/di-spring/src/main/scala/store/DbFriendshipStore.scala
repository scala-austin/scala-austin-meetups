package org.atxscala.injection.spring
package store


trait DbConnection
object DbConnection extends DbConnection


final class DbFriendshipStore(conn: DbConnection) extends FriendshipStore {

  // for lazy pedagogy, we delegate to our test in-memory implementation, but
  // imagine that we could use the DbConnection instead
  //
  private val delegate = new MemFriendshipStore

  def createUser(name: String, email: String) =
    delegate createUser (name, email)

  def setFriendOf(src: Long, target: Long) =
    delegate setFriendOf (src, target)

  def friendsOf(src: Long) =
    delegate friendsOf src

}
