* *Builds* [![Build
Status](https://travis-ci.org/IASI-SAKS/groucho.svg?branch=master)](https://travis-ci.org/IASI-SAKS/groucho)

# GROUCHO

Building
-------
GROUCHO is (mainly) a maven project. Build and install it with `mvn install`. In order to correctely build GROUCHO, the variable `JAVA_HOME` has to be set, for more details see the section [Java Home](https://github.com/IASI-SAKS/groucho#java-home).

GROUCHO relies on an instrumented JVM (provided by [CROCHET](https://github.com/gmu-swe/crochet)) that will be located in `groucho-crochet/target/jre-inst/`.

Java Home
-------
Remember that the variable JAVA_HOME has to be defined and propely set.
Possible hints are:
 * Linux: ```export JAVA_HOME= `dirname $(dirname $(readlink -f $(which javac)))` ```
 * Mc OS: ```export JAVA_HOME=$(/usr/libexec/java_home)```

About the Java Instrumentation
-------
For safety reasons, GROUCHO does not apply its instrumentation on the whole set of Java classes in the class path. More specifically, and in attition to all the classes that CROCHET does not instrument, the ``GrouchoClassTransformer`` agent does not apply to the instumentation the classes belonging to the following packages:
 * ``java.*``
 * ``sun.*``
 * ``it.cnr.iasi.saks.groucho.*``
 * ``org.junit.*``
 
Unforntunately the configuration of this list with a conf file is not supported yet. If for some reasons you may need to exclude some package/class form the instrumentation, please modify the class ``it.cnr.iasi.saks.groucho.instrument.GrouchoClassTransformer`` and build the project again.
For example for some local test under Eclipse we had to add the following package to the list of classes to be locally ignored:
 * ``org.eclipse.jdt.internal.junit.* ``

