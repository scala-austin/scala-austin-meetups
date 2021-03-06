package org.atxscala.sideeffects
package iotracked


object IoWithRt extends App {
  run("twice(effectVal)", twice(effectVal))
  run("twiceByName(effectVal)", twiceByName(effectVal))
  run("twice(effectDef)", twice(effectDef))
  run("twiceByName(effectDef)", twiceByName(effectDef))
}


object SmallStackSafe extends App {
  run("repeat(3)", effectDef.repeat(3))
}


object LargeStackUnsafe extends App {
  run("repeat(100000)", effectDef.repeat(100000))
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

  run("repeat(100000)", repeat(zEffect, 100000))

}


object MonadicIo extends App {

  def effect(i: Int): IO[Int] =
    IO { print(s"EFFECT($i) "); i }

  val ioFib: IO[(Int, Int, Int, Int, Int)] =
    for {
      a <- effect(1)
      b <- effect(1)
      c <- effect(a + b)
      d <- effect(b + c)
      e <- effect(c + d)
    } yield (a, b, c, d, e)

  val ioFibDesugared: IO[(Int, Int, Int, Int, Int)] =
    effect(1) flatMap { a =>
      effect(1) flatMap { b =>
        effect(a + b) flatMap { c =>
          effect(b + c) flatMap { d =>
            effect (c + d) map { e =>
              (a, b, c, d, e)
            }
          }
        }
      }
    }

  run("ioFib", ioFib)

}
