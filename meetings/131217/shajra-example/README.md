Example Project to Illustrate SBT Practices
===========================================

This project is an example project to illustrate some practices with SBT to
help keep the build clean and expressive.

This project was created by taking a real production application and ripping
out almost all the domain-specific components, to help focus more on the build
and less on application complexity.  The resultant project illustrates a few
points of complexity for an SBT build including:

    * a multi-module project
    * organizing the project directory with traits
    * gathering version information from SBT
    * integration of Debian package with SBT building


The Application
---------------

This application just reads configuration information from files and prints
what configuration has been read to the screen.  It uses the Typesafe Config
library, which is ironically not very type-safe.  So we've made an auxilary
library to wrap it.  The application also uses the Scalaz library for further
type-safety.

The application comes in two flavors, a "dev" one and a production one.  You
can try both of these versions by running the "run" task in SBT.  Both flavors
read default settings from the reference.conf file on the classpath.  The dev
application additionally reads from dev.conf, also on the classpath.  The dev
application also reads from a private.conf file in the root directory.  This is
only for sensitive configuration (like passwords) that should not be checked
into source version control.
