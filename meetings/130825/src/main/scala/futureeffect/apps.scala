package org.atxscala.sideeffects
package futureeffect


import concurrent.Future
import concurrent.ExecutionContext.Implicits.global


object StdFutureSideEffects extends App {

  val futures: List[Future[Int]] =
    List(1, 2, 3, 4, 5) map effect

}


object StdFutureControlledEffects extends App {

  val work: Future[Vector[Int]] =
    List(1, 2, 3, 4, 5)
      .foldLeft(Future{Vector.empty[Int]}) { (acc, i) =>
        acc flatMap { is => effect(is :+ i) }
      }

  report(work)

}


object StdFutureControlledEffectsWithErrors extends App {

  val work: Future[Vector[Int]] =
    List(1, 2, 3, 4, 5)
      .foldLeft(Future { Vector.empty[Int] }) { (acc, i) =>
        acc flatMap { is =>
          if (i == 4)
            Future { sys.error("4 not allowed") }
          else
            effect(is :+ i)
        }
      }

  report(work)

}
