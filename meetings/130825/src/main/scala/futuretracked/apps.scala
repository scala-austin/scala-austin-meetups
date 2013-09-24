package org.atxscala.sideeffects
package futuretracked


import scalaz.concurrent.Task


object ScalazTaskNoSideEffects extends App {

  val futures: List[Task[Int]] =
    List(1, 2, 3, 4, 5) map effect

  println("DONE (no side effects, right?)")

}


object ScalazTaskSequence extends App {

  import scalaz.std.list._
  import scalaz.syntax.traverse._

  val work: Task[List[Int]] =
    (List(1, 2, 3, 4, 5) map effect).sequenceU

  report(work)

}


object ScalazTaskGather extends App {

  val work: Task[List[Int]] =
    Task.gatherUnordered(List(1, 2, 3, 4, 5) map effect)

  report(work)

}


object ScalazTaskWithErrors extends App {

  import scalaz.std.list._
  import scalaz.syntax.traverse._

  def toWork(i: Int): Task[Int] =
    if (i == 4) Task { sys.error("4 not allowed") }
    else effect(i)

  val work: Task[List[Int]] =
    (List(1, 2, 3, 4, 5) map toWork).sequenceU

  report(work)

}