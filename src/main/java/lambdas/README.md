# Lambdas
Each child package contains a specific functional interface defined in the jdk
  * predicate
  * consumer
  * Function
  * filereader - not an SDK lamba, just various lamdba file reading tricks
  
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
  
## Exceptions
the default set of functional interfaces do not throw checked exeptions, you will need to either wrap
it in a try/catch loop, or create an new functional interface and declare the exception

## Local Variables
Local variables in a lambda are implcitly final.  You can access variables that are in scope of where
the lambda is defined, but not in the lambda (like in an anonymous class

## Method References
These are shorthand way of accessing a method, I think within the context of a lambda

    apple::getWeight
    
There are 3 kinds of method references
* referencing a static method, like 

    Integer::parseInt
* method on an Instance of a arbitrary type

    String::length
    
* Method on an existing in scope object instance
 
## Constructor Reference

0 argument constructor matches the Supplier interface

    Supplier<Apple> a = Apple::new
    Apple apple = a.get();
    
A 1 arg constructor matches a function interface

    Function<<Integer, Apple> a = Apple::new
    Apple apple - a.apply(142) //weight
    
A 2 arg constructor matches a BiFunction.  Any more args and you will have to create your own
function, say TriFunction or QuadFunction

## Lamda composition