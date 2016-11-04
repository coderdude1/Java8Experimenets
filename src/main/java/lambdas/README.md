# Lambdas
Each child package contains a specific functional intefrace defined in the jdk
  * predicate
  * consumer
  * Function
  * filereader - various lamdba file reading tricks
  
Note there are other default types such as supplier, and IntPredicate, etc that I haven't implemented
[here](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) is
a list of ones that are defined as part of the JDK.  YOu can always create your own too by 
creating an interface and annotating it with @FunctionalInterface

The functional definition reads like this:
    (T, U) -> R
The stuff to the left in parens are the parameters, and the stuff to the right is the return type

# Some things to be aware of
## Autoboxing
Several of the default Function interfaces will have specialized subclasses that avoid autoxing of types,
such as IntPredicate vs Predicate, ToIntFunction vs Function
  
  TODO
  * Add default methods to the Functional interfaces
  * replace loops with streams where possible
  * add method references