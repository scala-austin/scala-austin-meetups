package org.atxscala.injection.oddends


trait A { val i: Int }


trait BVal extends A { val j = i }

trait BLazyVal extends A { lazy val j = i }


object ValInitApp extends App with BVal {
  val i = 3
  println(j)
}


object LazyValInitApp extends App with BLazyVal {
  val i = 3
  println(j)
}


object EarlyInitApp
    extends { val i = 3 }
    with App
    with BVal {
  println(j)
}
