* *Builds* [![Build
Status](https://travis-ci.org/IASI-SAKS/groucho.svg?branch=master)](https://travis-ci.org/IASI-SAKS/groucho)

# GROUCHO

Building
-------
GROUCHO is (mainly) a maven project. Build and install it with `mvn install`. In order to correctly build GROUCHO, the variable `JAVA_HOME` has to be set, for more details see the section [Java Home](https://github.com/IASI-SAKS/groucho#java-home).

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
For safety reasons, GROUCHO does not apply its instrumentation on the whole set of Java classes in the class path. More specifically, and in addition to all the classes that CROCHET does not instrument, any agent built from inheritance of the ``it.cnr.iasi.saks.groucho.instrument.AbstractClassTranformer``  does not apply to the instrumentation the classes belonging to the following packages:
 * ``java.*``
 * ``sun.*``
 * ``it.cnr.iasi.saks.groucho.*``
 * ``org.junit.*``
 * ``junit.framework.*``
 * ``org.apache.maven.*``
 
If for some reasons you may need to programmatically exclude some package/class form the instrumentation, please modify the class ``it.cnr.iasi.saks.groucho.instrument.AbstractClassTranformer`` and build the project again.
For example during some local test under Eclipse we had to add the following package to the list of classes to be locally ignored:
 * ``org.eclipse.jdt.internal.junit.* ``
 
An easier alternative is to configure the list of classes subject to exclusion by setting a specific property (e.g. as an entry of a property file). The following example:

`` groucho.transformer.disable.classesList=org/objectweb/asm,com/fasterxml/jackson`` 

shows how to disable the classes in both the packages:
  * ``org.objectweb.asm.*``
  * ``com.fasterxml.jackson.*``
  
 Most of the times the JVM notifies an error like:
 ``Java.lang.linkage error when instrumenting XXX``
 it is possible a class that have been already loaded (and instrumented) is going to be processed again. If the class ``XXX`` is really not relevant for the Invivo testing campaign, thus it may be useful to exclude it as described above.

How to Enable Invivo Tests
-------

Each method that could be subject to In Vivo testing must be annotated as `TestableInVivo`

```java
	@TestableInVivo(invivoTestClass = "it.cnr.iasi.saks.groucho.invivotests.DummyInvivoTest",
			invivoTest = "invivoTestMethodName")
	public void thisIsFoo() {
		/*
		 * To Something here
		 */
	}
```

In case the source code of a class is not available for modification, the injection can be specified by means of a JSON record. An example is reported in:
 * [modelResource.json](https://github.com/IASI-SAKS/groucho/blob/master/groucho-lab/src/test/resources/modelResource.json)
 * [testingConf.properties](https://github.com/IASI-SAKS/groucho/blob/master/groucho-lab/src/test/resources/testingConf.properties) by setting the property `groucho.lab.intrument.jsonFile` to the path of the file of the JSON record
 
An example on how to apply the annotations reported in a given JSON report:
 * is enacted by the agent [`${groucho-lab.build.directory}/${groucho-lab.build.finalName}.jar`](https://github.com/IASI-SAKS/groucho/tree/master/groucho-lab/src/main/java/it/cnr/iasi/saks/groucho/lab/instrument)
 * and it can be used as reported in this [pom.xml](https://github.com/IASI-SAKS/groucho/blob/master/groucho-lab/pom.xml#L274-L276)

About QA Aspects
-------
Some quality gates are defined and monitored by means of SonarCloud and Jacoco. As GROUCHO is a multi-module maven project, there are few
issues important to remember:
* Currently the token credential for Sonar has beed set in the ``SONAR_TOKEN`` environmental variable from the Travis-CI UI 
* The test for [State Carving](groucho-core/src/test/java/it/cnr/iasi/saks/groucho/carvingStateTests/) have been disabled during the QA analysis. This configuration is currently needed even if the tests pass on a "regular" build. Indeed, the injection by Jacoco will cause these tests fail because it modifies the result of the tests so that to mismatch their respective expected outcome.
* While configuring MVN it may be the case to modify the arguments passed to the JVM, for example this may happen frequently when using plugins such as ``surefire`` or ``failsafe``. In those cases, Jacoco may wrongly count and report data on the coverage. The solution is to properly configure ``surefire``/``failsafe`` in order to interact with the ``jacoco:prepare-agent`` plugin. For example do not forget to append new arguments my referring the MVN variable ``@{argLine}``. Further details from the [official documentation](https://www.eclemma.org/jacoco/trunk/doc/prepare-agent-mojo.html). Please note that it is strongly recommended to 
always include in the ``POM`` an empty definition of the property argLine. Indeed if ``@{argLine}`` is used but never initialised thus surfire/failsafe causes the build to fail.
* Jacoco does not really support the analysis of multi-module projects. The work-around is to:
   1. create an artificial module depending from all the others subject to analysis that will actually host the reports
   1. to properly configure all the modules so that to redirect the analysis in such an artificial module.

   Within GROUCHO the module [groucho-sonar](groucho-sonar) has such intent. The followed documentation is:
    * [Maven Multi-Module Builds](https://github.com/jacoco/jacoco/wiki/MavenMultiModule#maven-multi-module-builds)
    * [Multi-module Apache Maven example](https://github.com/SonarSource/sonar-scanning-examples/tree/master/sonarqube-scanner-maven/maven-multimodule)
