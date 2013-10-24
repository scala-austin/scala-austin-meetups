package org.atxscala.injection.existential


import scala.annotation.tailrec


trait CrawlerModule {

  self: FriendshipModule =>

  type Degree = Int

  def friendsOf(id: UserId, degree: Degree): Set[User] = {
    @tailrec
    def loop
        (acc: Map[UserId, User], workList: List[(UserId, Degree)])
        : Set[User] = {
      if (workList.isEmpty)
        acc.values.toSet
      else {
        val next =
          workList
            .flatMap {
              case (i, d) =>
                if (d > 0) friendsOf(i) map { (_, d - 1) } else List.empty
            }.filter { case (u, d) => ! acc.contains(idOf(u)) }
        val nextAcc = acc ++ (next map { case (u, d) => (idOf(u), u) })
        val nextWorkList =
          next
            .filter { case (_, d) => d > 0 }
            .map { case (u, d) => (idOf(u), d) }
        loop(nextAcc, nextWorkList)
      }
    }
    loop(Map.empty, List((id, degree)))
  }

}
