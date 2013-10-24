package org.atxscala.injection.simple
package store


final class DbFriendshipStore(conn: DbConnection) extends FriendshipStore {

  // for lazy pedagogy, we delegate to our test in-memory implementation, but
  // imagine that we could use the DbConnection instead
  //
  private val delegate = new MemFriendshipStore

  def createUser(name: String, email: String) = {
    assert(conn != null)
    delegate createUser (name, email)
  }

  def setFriendOf(src: UserId, target: UserId) = {
    assert(conn != null)
    delegate setFriendOf (src, target)
  }

  def friendsOf(src: UserId) = {
    assert(conn != null)
    delegate friendsOf src
  }

}
