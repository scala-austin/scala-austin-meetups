package org.atxscala.sideeffects
package iotracked


import annotation.tailrec


sealed class IO[A](run: => A) {

  def unsafePerformIO(): A =
    run

  def map[B](f: A => B): IO[B] =
    IO(f(unsafePerformIO))

  def flatMap[B](f: A => IO[B]): IO[B] =
    IO(f(unsafePerformIO).unsafePerformIO)

  def repeat(times: Int): IO[A] = {
    @tailrec
    def loop (io: IO[A], n: Int): IO[A] =
      if (n > 1)
        loop(io flatMap { _ => this }, n - 1)
      else
        io
    loop(this, times)
  }

}


object IO {
  def apply[A](a: => A): IO[A] = new IO(a)
}
