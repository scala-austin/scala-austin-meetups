package org.atxscala.sideeffects


package object ioeffect {

  def effectDef: Boolean = {
    print("EFFECT!  ")
    true
  }

  val effectVal: Boolean = effectDef

  def twice[A](a: A): (A, A) = (a, a)

  def twiceByName[A](a: => A): (A, A) = (a, a)

  def run[A](msg: String, a: => A): Unit = {
    print(s"\n${msg}:  ")
    println(s"\nANSWER: ${a}")
  }

  println("PACKAGE OBJECT INIT OVER")

}
