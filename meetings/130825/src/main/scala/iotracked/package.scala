package org.atxscala.sideeffects


package object iotracked {

  def effect: IO[Boolean] =
    IO {
      print("EFFECT!  ")
      true
    }

  val effectVal: IO[Boolean] =
    effect

  def effectDef: IO[Boolean] =
    effect

  def twice[A](a: IO[A]): IO[(A, A)] =
    a map { v =>  (v, v) }

  def twiceByName[A](a: => IO[A]): IO[(A, A)] =
    a map { v => (v, v) }

  def go[A]
      (msg: String,
        a: => { def unsafePerformIO(): A })
      : Unit = {
    print(s"\n${msg}:  ")
    println(s"\nANSWER: ${a.unsafePerformIO}")
  }

  println("PACKAGE INIT OVER")

}
