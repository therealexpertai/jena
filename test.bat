@echo off

set CP=lib/arq-extra.jar;lib/arq.jar;lib/commons-logging-1.1.1.jar;lib/icu4j_3_4.jar;lib/iri.jar;lib/jena.jar;lib/jenatest.jar;lib/json.jar;lib/junit-4.5.jar;lib/log4j-1.2.12.jar;lib/lucene-core-2.3.1.jar;lib/stax-api-1.0.jar;lib/wstx-asl-3.0.0.jar;lib/xercesImpl.jar

java -classpath %CP% junit.textui.TestRunner com.hp.hpl.jena.test.TestPackage
