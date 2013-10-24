package org.atxscala.injection.existential


trait ExampleCrawlModule extends Runnable {

  self: CrawlerModule with FriendshipModule =>

  def run(): Unit = {

    val a = createUser("A", "a@example")
    val b = createUser("B", "b@example")
    val c = createUser("C", "c@example")
    val d = createUser("D", "d@example")

    setFriendOf(idOf(a), idOf(b))
    setFriendOf(idOf(b), idOf(c))
    setFriendOf(idOf(c), idOf(d))
    setFriendOf(idOf(d), idOf(a))

    println(friendsOf(idOf(a), 0))
    println(friendsOf(idOf(a), 1))
    println(friendsOf(idOf(a), 2))
    println(friendsOf(idOf(a), 3))
    println(friendsOf(idOf(a), 4))
    println(friendsOf(idOf(a), 10000000))

  }

}
