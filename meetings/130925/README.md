Effects Without Side-effects Meeting Content
============================================

For:
    Austin Scala Enthusiasts

Given:
    September 25, 2013


Slides
------

Clone this repository, and just look at the index.html file in a browser.  Use
arrow keys to navigate.  The `o` key toggles between the presentation view and
a slide gallery.


Source
------

An "sbt" Shell script has been provided for convenience for Mac/Unix users.
Look through the code under the "src" directory, and run `sbt run` to see how
these small code examples execute.

Most importantly, note how in untracked code with side effects, you need to
understand the evaluation policy of the language to know when the effect (a
simple println call) executes.

When using types and FP to track effects, we get more control of how we manage
effects.  We just compose calls that _describe_ effects, and at the "end of the
world" we call a "do it" function (like scalaz.effect.IO#unsafePerformIO or
scalaz.effect.Task#attemptRun) that runs the one side effect (composed of
possibly many side effects).
