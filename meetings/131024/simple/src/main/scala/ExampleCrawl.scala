package org.atxscala.injection.simple


import store.FriendshipStore


class ExampleCrawl(store: FriendshipStore) extends Runnable {

  def run(): Unit = {

    val crawler = new Crawler(store)

    val a = store.createUser("A", "a@example")
    val b = store.createUser("B", "b@example")
    val c = store.createUser("C", "c@example")
    val d = store.createUser("D", "d@example")

    store.setFriendOf(a.id, b.id)
    store.setFriendOf(b.id, c.id)
    store.setFriendOf(c.id, d.id)
    store.setFriendOf(d.id, a.id)

    println(crawler.friendsOf(a.id, 0))
    println(crawler.friendsOf(a.id, 1))
    println(crawler.friendsOf(a.id, 2))
    println(crawler.friendsOf(a.id, 3))
    println(crawler.friendsOf(a.id, 4))
    println(crawler.friendsOf(a.id, 10000000))

  }

}
