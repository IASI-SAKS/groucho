* *Builds* [![Build
Status](https://travis-ci.org/IASI-SAKS/groucho.svg?branch=master)](https://travis-ci.org/IASI-SAKS/groucho)

# GROUCHO

Building
-------
GROUCHO is (mainly) a maven project. Build and install it with `mvn install`. In order to correctely build GROUCHO, the variable `JAVA_HOME` has to be set, for more details see the section [Java Home](https://github.com/gulyx/groucho/blob/updatingReadme/README.md#java-home).

GROUCHO relies on an instrumented JVM (provided by [CROCHET](https://github.com/gmu-swe/crochet)) that will be located in `groucho-crochet/target/jre-inst/`.

Java Home
-------
Remember that the variable JAVA_HOME has to be defined and propely set.
Possible hints are:
 * Linux: ```export JAVA_HOME= `dirname $(dirname $(readlink -f $(which javac)))` ```
 * Mc OS: ```export JAVA_HOME=$(/usr/libexec/java_home)```

