package org.atxscala.sideeffects


import scalaz.concurrent.Task


package object futuretracked {

  def effect[A](a: A) =
    Task {
      println(s"EFFECT(${a})")
      a
    }

  def run[A](task: Task[A]) = {
    val res = task.attemptRun
    println(s"RESULT: ${res}")
  }

}
