package org.atxscala.sideeffects


package object iotracked {

  def effectDef: IO[Boolean] =
    IO {
      print("EFFECT!  ")
      true
    }

  val effectVal: IO[Boolean] =
    effectDef

  def twice[A](a: IO[A]): IO[(A, A)] =
    a map { v => (v, v) }

  def twiceByName[A](a: => IO[A]): IO[(A, A)] =
    a map { v => (v, v) }

  def run[A]
      (msg: String,
        a: => { def unsafePerformIO(): A })
      : Unit = {
    print(s"\n${msg}:  ")
    println(s"\nANSWER: ${a.unsafePerformIO}")
  }

  println("PACKAGE OBJECT INIT OVER")

}
