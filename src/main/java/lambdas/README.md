# Lambdas
Each child package contains a specific functional intefrace defined in the jdk
  * predicate
  * consumer
  *
  *
  * filereader - various lamdba file reading tricks
  
# Some things to be aware of
## Autoboxing
Several of the default Function interfaces will have specialized subclasses that avoid autoxing of types,
such as IntPredicate vs Predicate, ToIntFunction vs Function
  
  TODO
  * Add default methods to the Functional interfaces
  * replace loops with streams where possible
  * add method references