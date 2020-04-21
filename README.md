* *Builds* [![Build
Status](https://travis-ci.org/IASI-SAKS/groucho.svg?branch=master)](https://travis-ci.org/IASI-SAKS/groucho)

# GROUCHO

Building
-------
GROUCHO is (mainly) a maven project. Build and install it with `mvn install`. In order to correctely build GROUCHO, the variable `JAVA_HOME` has to be set, for more details see the section [Java Home](https://github.com/IASI-SAKS/groucho#java-home).

GROUCHO relies on an instrumented JVM (provided by [CROCHET](https://github.com/gmu-swe/crochet)) that will be located in `groucho-crochet/target/jre-inst/`.

Some O.S. libraries may be required in order to build and run GROUCHO. The detailed list can be found in the file `.travis.yaml`.

Java Home
-------
Remember that the variable JAVA_HOME has to be defined and propely set.
Possible hints are:
 * Linux: ```export JAVA_HOME=`dirname $(dirname $(readlink -f $(which javac)))` ```
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

About QA Aspects
-------
Some quality gates are defined and monitored by means of SonarCloud and Jacoco. As GROUCHO is a multi-module maven project, there are few
issues important to remeber:
* Jacoco does not really support the analysis of multi-module projects. The work-around is to:
* 1 . create an artificial module depending from all the others subject to analysis that will actually host the reports
* 2 . to properly configure all the modules so that to redirect the analysis in such an artificial module
 Within GROUCHO the module [groucho-sonar](groucho-sonar) has such intent. The followed documentation is:
  * [Maven Multi-Module Builds](https://github.com/jacoco/jacoco/wiki/MavenMultiModule#maven-multi-module-builds)
  * [Multi-module Apache Maven example](https://github.com/SonarSource/sonar-scanning-examples/tree/master/sonarqube-scanner-maven/maven-multimodule)
* Currently the token credential for Sonar has beed set in the SONAR_TOKEN environmental variable from the Travis-CI UI 
* The test for [State Carving](groucho-core/src/test/java/it/cnr/iasi/saks/groucho/carvingStateTests/) have been disabled during the QA analysis. This configuration is currently needed even if the test pass on a "regular" build. Indeed, the injection by Jacoco will cause these test fail because it modifies the result of the tests so that to mismatch their respective expected outcome.
 
 
