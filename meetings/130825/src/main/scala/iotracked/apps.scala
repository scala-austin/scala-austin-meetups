package org.atxscala.sideeffects
package iotracked


object IoWithRt extends App {
  go("twice(effectVal)", twice(effectVal))
  go("twiceByName(effectVal)", twiceByName(effectVal))
  go("twice(effect)", twice(effect))
  go("twice(effectDef)", twice(effectDef))
  go("twiceByName(effect)", twiceByName(effect))
  go("twiceByName(effectDef)", twiceByName(effectDef))
}


object SmallStackSafe extends App {
  go("repeat(3)", effect.repeat(3))
}


object LargeStackUnsafe extends App {
  go("repeat(10000)", effect.repeat(10000))
}


object ScalazStackSafe extends App {

  import scalaz.effect.{ IO => ZIO }

  def repeat[A](io: ZIO[A], times: Int): ZIO[A] = {
    @annotation.tailrec
    def loop(next: ZIO[A], n: Int): ZIO[A] =
      if (n > 1)
        loop(next flatMap { _ => io }, n - 1)
      else
        next
    loop(io, times)
  }

  def zEffect: ZIO[Boolean] =
    ZIO {
      print("EFFECT!  ")
      true
    }

  go("repeat(10000)", repeat(zEffect, 10000))

}


object MonadicIo extends App {

  def sideEffecting(i: Int): IO[Int] =
    IO { print(s"EFFECT($i) "); i }

  val ioFib: IO[(Int, Int, Int, Int, Int)] =
    for {
      a <- sideEffecting(1)
      b <- sideEffecting(1)
      c <- sideEffecting(a + b)
      d <- sideEffecting(b + c)
      e <- sideEffecting(c + d)
    } yield (a, b, c, d, e)

  go("ioFib", ioFib)

}