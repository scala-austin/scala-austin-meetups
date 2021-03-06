package org.atxscala.injection.simple


import scala.annotation.tailrec

import store.FriendshipStore


class Crawler(store: FriendshipStore) {

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
                if (d > 0) store friendsOf i map { (_, d - 1) } else List.empty
            }.filter { case (u, d) => ! acc.contains(u.id) }
        val nextAcc = acc ++ (next map { case (u, d) => (u.id, u) })
        val nextWorkList =
          next filter { case (_, d) => d > 0 } map { case (u, d) => (u.id, d) }
        loop(nextAcc, nextWorkList)
      }
    }
    loop(Map.empty, List((id, degree)))
  }

}
