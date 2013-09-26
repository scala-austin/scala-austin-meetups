package org.atxscala.sideeffects


import concurrent.Await
import concurrent.Future
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration._


package object futureeffect {

  def effect[A](a: A) =
    Future {
      println(s"EFFECT(${a})")
      a
    }

  def run[A](future: Future[A]) = {
    future.onComplete { res =>
      println(s"RESULT: ${res}")
    }
  }

}
