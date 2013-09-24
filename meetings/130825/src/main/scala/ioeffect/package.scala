package org.atxscala.sideeffects


package object ioeffect {

  def effect: Boolean = {
    print("EFFECT!  ")
    true
  }

  val effectVal: Boolean = effect

  def effectDef: Boolean = effect

  def twice[A](a: A): (A, A) = (a, a)

  def twiceByName[A](a: => A): (A, A) = (a, a)

  def go[A](msg: String, a: => A): Unit = {
    print(s"\n${msg}:  ")
    println(s"\nANSWER: ${a}")
  }

  println("PACKAGE INIT OVER")

}
