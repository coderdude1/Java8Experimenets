# Overview
This is a collection of experiments and POC's.  A lot of these came from the manning 'java 8 in action'
by Raoul-Gabriel Urma, Mario Fusco, and Alan Mycroft, and other assorted places, including me just experiementing
around after reading the JDK API's, blogs, and such.

## Lambdas
experiments with lambdas.  Start here since the other stuff will require knowledge of lambdas, and the default ones 
in the SDK.  You should probably find a tutorial to read up on stuff like the FunctionalInterface, 
type matching and function descriptors

tldr function descriptor is the input type and output types

    T -> boolean is a predicate descriptor of the Predicate functional interface that ships with the SDK

[Functional Interfaces included with the java 8 SDK](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
## Preconditions
some stuff using the guava Preconditions class, and the Objects.requireNotNull

## Streams
started as a very quick and dirty experiment with stream processing, and has grown to include more specific examples, mostly from 
the java 8 book mentioned in the overview.