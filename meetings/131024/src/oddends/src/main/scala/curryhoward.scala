package org.atxscala.injection.oddends


/** Some exercises illustrating the
  * [[http://en.wikipedia.org/wiki/Curry%E2%80%93Howard_correspondence Curry-Howard correspondence]].
  * These problems were designed by
  * [[http://apocalisp.wordpress.com/ Rúnar Óli Bjarnason.]]
  */
package object curryhoward


package curryhoward {

  /** Types that correspond to useful and interesting first order logic. */
  object Logic {

    type Not[A] = A => Nothing

    trait Forall[F[_]] {
      def apply[A]: F[A]
    }

    trait Exists[F[_]] {
      type A
      def apply: F[A]
    }

  }

  /** Implementations that "prove" the theorems stated in the type signature.
    *
    * The Curry-Howard correspondence relates issomorphically type signatures
    * and expressions to theorems and proofs.  The theorem corresponding to a
    * type signature is true if the type is inhabited by at least one
    * expression.  So by writing expressions to the following signatures that
    * type check with the compiler, we have essentially proven theorems.
    */
  object Proofs {

    import Logic._

    /** `(∃x.f(x)) ⇒ ¬(∀x.¬f(x))`. */
    def notNotExists[F[_]](e: Exists[F]):
        Not[Forall[({type f[x] = Not[F[x]]})#f]] =
      (a: Forall[({type f[x] = Not[F[x]]})#f]) => a.apply[e.A](e.apply)

    /** `(∀x.f(x)) ⇒ ¬(∃x.¬f(x))`. */
    def notNotForall[F[_]](a: Forall[F]):
        Not[Exists[({type f[x] = Not[F[x]]})#f]] =
      (e: Exists[({type f[x] = Not[F[x]]})#f]) => e.apply(a.apply[e.A])

    /** `((∃x.f(x))⇒a) ⇒ (∀x.f(x)⇒a)`. */
    def curry[F[_], A](f: Exists[F] => A):
        Forall[({ type f[x] = F[x] => A })#f] =
      new Forall[({ type f[x] = F[x] => A })#f] {
        def apply[B] =  { g: F[B] =>
          f(new Exists[F] { type A = B; def apply = g })
        }
      }

    /** `(∀x.f(x)⇒a) ⇒ ((∃x.f(x))⇒a)`. */
    def uncurry[F[_], A](f: Forall[({ type f[x] = F[x] => A })#f]):
        Exists[F] => A =
      (e: Exists[F]) => f.apply[e.A](e.apply)

  }

}
