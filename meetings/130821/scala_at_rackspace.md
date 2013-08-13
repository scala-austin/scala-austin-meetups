Welcome to Austin Scala Enthusiasts
-----------------------------------

-:|-|:-
![meetup.logo] | | <http://www.meetup.com/Austin-Scala-Enthusiasts>
\ |\ \ \ |
![googlegroup.logo] | | <https://groups.google.com/forum/#!forum/austin-scala-enthusiasts>
\ | |
![gmane.logo] | | <http://dir.gmane.org/gmane.org.user-groups.austin.scala>

[meetup.logo]: images/logo.meetup.png
[googlegroup.logo]: images/logo.google_groups.png
[gmane.logo]: images/logo.gmane.png


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



a little about Scala at Rackspace
---------------------------------

- Scala is relatively new to Rackspace
- we automate/integrate legacy systems in our DCs
- our domain + legacy application ⇒ complexity
- correctness problems surface more than performance problems


why Scala for the team, at first
--------------------------------

- fresh reboot with new code
- benefits of JVM as a platform (coming from Ruby/Rails)
- perhaps an improved talent pool
- plurality of programming paradigms
- actor system libraries
- improved correctness from type safety
- interest in FP


why stay with Scala
-------------------

- correctness
- benefits of JVM (though it's not perfect)
- modularity and extensibility with types
- typed highly-general abstractions
    - hard to do without types
    - reuse with less coupling
    - elegant, composeable APIs (DSLs)
- a _lot_ of these benefits derive from FP. . .
- . . . the rest from type-checking


still seeing how these pan out
------------------------------

- improved talent pool
    - TODO: flesh out
- need for actor system libraries
    - TODO: flesh out


apprehensive of now
-------------------

- plurality of programming paradigms
    - loss of composition
    - loss in correctness
    - loss of human comprehensibility
- we subset our use of Scala to get more out of types
    - no side-effects
    - no variance
    - TODO: list more
- we work hard to program with functions


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

  private def main =
    for {
      s <- loadService
      _ <- startService(s)
    } yield ()

  // or with ScalaZ:
  //
  // loadService >>= startService

...

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


Scala helps us FP
-----------------
- immutable collections
- libraries like Scalaz


that's all for now. next time?
------------------------------

- style/archtecture in Scala
- abstracting intelligently
- mastering implicits
- effect tracking (sync and async) in Scala
- for-yield comprehensions (basic monads)
- error handling
- dependency injection in Scala
- SBT
- Spark
