Welcome to Austin Scala Enthusiasts
-----------------------------------

-:|-|:-
![meetup.logo] | | <http://meetup.com/Austin-Scala-Enthusiasts>
\ |\ \ \ |
![googlegroup.logo] | | <http://groups.google.com/forum/#!forum/austin-scala-enthusiasts>
\ | |
![gmane.logo] | | <http://dir.gmane.org/gmane.org.user-groups.austin.scala>
\ | |
![github.logo] | | <http://github.com/scala-austin>

[meetup.logo]: images/logo.meetup.png
[googlegroup.logo]: images/logo.google_groups.png
[gmane.logo]: images/logo.gmane.png
[github.logo]: images/logo.github.png


to kickstart a discussion. . .
------------------------------

<font size=30>
<center>
-:|:-:|:-
![scala.logo] |\ \ \ \ \ | ![rackspace.logo]
Scala | @ | Rackspace
</center>
</font>

[scala.logo]: images/logo.scala.png
[rackspace.logo]: images/logo.rackspace.png


a little about me
-----------------

Name: Sukant Hajra

Twitter: @shajra

Freenode (IRC): tnks

- developed Java for 5+ years
- studying functional programming for many of those years
- functional programming professionally for around 2 years
- programming Scala professionally for 6 months


a disclaimer
------------

The views of this presentation are mine, and not Rackspace's.

Even my team members may have slightly different interpretations, so please
feel free to talk to them too.

Here's a few that have been working in the Scala code base:

- Brad Brown
- Curtis Carter
- Franco Lazzarino

We've learned a lot. . . but we're still learning.


a little about Scala at Rackspace
---------------------------------

- Scala is relatively new to Rackspace
- we automate/integrate legacy systems in our DCs
- our domain + legacy application ⇒ complexity
- correctness problems surface more than performance problems


why Scala for the team, at first
--------------------------------

- benefits of JVM as a platform (coming from Ruby/Rails)
- relatively modern language features
- plurality of programming paradigms
- actor system libraries
- improved correctness from type safety
- interest in FP
- fresh reboot with new code
- perhaps an improved talent pool


why stay with Scala
-------------------

- correctness
- relatively modern language features
- benefits of JVM (though it's not perfect)
- modularity and extensibility with types
- typed highly-general abstractions
    - hard to do without types
    - reuse with less coupling
    - elegant, composeable APIs (DSLs)
- a _lot_ of these benefits derive from
    - FP
    - an expressive type system


still seeing how these pan out
------------------------------

- need for actor system libraries
    - our algorithms don't require mutable state
    - currently Akka abandons much type-safety
- improved talent pool
    - the pool is definitely smaller
    - the Scala community at large if has much talent
    - general JVM expertise ports to Scala
    - hard to find local expertise in Scala's type system
    - this meetup is part of the exploration
    - talk to me if you're interested in our team


apprehensive of now
-------------------

- plurality of programming paradigms
    - loss of composition
    - loss in correctness
    - loss of human comprehensibility
- we subset our use of Scala to get more out of types
- we work hard to program with functions


things we avoid
---------------
- side-effects
- nulls (replace with Option)
- exceptions (replace with ScalaZ's \\/ or EitherT)
- partial functions
- reflection (type erasure is good!)
- subtyping/variance
- overloading


things we embrace
-----------------
- both algebraic data types (case classes) _and_ objects
- implicits (esp. for type classes)
- higher-kinded types
- existential and method-dependent types
- partially-applied types (type lambdas)
- post-fix notation
- type enrichments with implicits
- ScalaZ


rich APIs (DSLs)
----------------

for-yield works with lists. . . (they're monads)

~~~~ {.scala .numberLines}
scala> for {
     |   a <- List(1,2)
     |   b <- List(3,4)
     | } yield (a, b)

res0: List[(Int, Int)] =
    List((1,3), (1,4), (2,3), (2,4))
~~~~


rich APIs (DSLs)
---------------

for-yield works with other things. . . (that are also monads)

~~~~ {.scala .numberLines}
scala> for { a <- Some(1); b <- Some(2) }
     | yield (a + b)

res0: Option[Int] = Some(3)

scala> for {
     |   a <- Some(1)
     |   b <- None: Option[Int]
     | } yield (a + b)

res1: Option[Int] = None
~~~~


rich APIs (DSLs)
----------------

for-yield works for _your_ things!  Here's a "free" monad example.

~~~~ {.scala .numberLines}
val plan =
  for {
    a <- call App1 1
    b <-
      fork(call App2 f(a), call App3 g(a))
    c <- call App4 b retry 4.times
  } yield h(a, b, c)

val task = execute(plan)
val planReport = graph(plan)
~~~~

- like an OOP command pattern, but better
- does nothing; we write interpreters for plans
- we can write multiple interpreters (like execute or graph)




functional programming
----------------------

- programming with functions

~~~~ {.scala}
        def myFunc(d: Domain): Range
~~~~

- a function maps _every_ value in their domain to a value in their
  range
    - always the same value
    - no exceptions thrown
- higher-order and closures alone aren't enough
- you can do this in Java or Ruby, but it's not fun


all functions, all the way down
-------------------------------

~~~~ {.scala .numberLines}
object OurService extends App {

  // our only side-effecting call
  main.unsafePerformIO

  private def main: IO[Unit] =
    for {
      s <- loadService
      _ <- startService(s)
    } yield ()

  ...

}

~~~~


or with ScalaZ
--------------

~~~~ {.scala .numberLines}
object OurService extends SafeApp {

  override def runc: IO[Unit] =
    loadService >>= startService

  ...

}

~~~~


dangers of not using functions
------------------------------

If you have this

~~~~ {.scala}
    val tasks: List[Task] = ...
~~~~

are the tasks running yet?

- with side-effects, they _might_ be
- with functions, they _never_ are


headaches that bother us
------------------------

- weak type inference (relative to Damas-Milner in Haskell)
- weak tail call elimination
- global classpath (dependencies more easily conflict)
- verbosity (compared to Haskell)
- abusive use of OOP

in Scala:

~~~~ {.scala}
    sealed trait Option[+A]
    case class Some[+A](a: A) extends Option[A]
    case object None extends Option[Nothing]
~~~~

in Haskell:

~~~~ {.haskell}
    data Option a = Some a | None
~~~~


headaches that bother us less
-----------------------------

- learning curve
- buggy IDEs
- resource-intensive IDEs
- performance problems with FP
- slow compiler times


that's all for now; next time?
-------------------------------

- style/archtecture in Scala
- abstracting intelligently
- mastering implicits
- effect tracking (sync and async) in Scala
- for-yield comprehensions
- error handling
- dependency injection in Scala
- SBT
- Spark
