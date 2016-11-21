# Stuff mostly from the Manning Java In Action

# filtering
use .filter and pass it a predicate lambda or method reference.  There are other types of filtering such as 
.distinct, .limit, .skip

## anyMatch, allMatch, noneMatch, findFirst and findAny
these are specialized filters that take a lambda predicate.  These 3 operations perform short-circuiting, which means
that it does not need to process the whole stream to return a result, ie the first occrance that violates the predicate
will cause the stream process to stop

## findFirst vs findAny
according to the API docs:

stream.findAny
> The behavior of this operation is explicitly nondeterministic; it is free to select any element in the stream. 
> This is to allow for maximal performance in parallel operations; the cost is that multiple invocations on the 
> same source may not return the same result. (If a stable result is desired, use findFirst() instead.

tldr; if you don't care which element is returned, use findAny, esp if you are using parallel streams as findAny is 
not guaranteed to return the first match, whereas findFirst will (but may be slower).  findFirst works better when streams
have encounter order, ie generated from a source that ensures order like a list.

## Distinct
From the [javadoc on distinct](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--) 
> API Note:
  Preserving stability for distinct() in parallel pipelines is relatively expensive (requires that the operation
  act as a full barrier, with substantial buffering overhead), and stability is often not needed. Using an 
  unordered stream source (such as generate(Supplier)) or removing the ordering constraint with 
  BaseStream.unordered() may result in significantly more efficient execution for distinct() in parallel pipelines,
  if the semantics of your situation permit. If consistency with encounter order is required, and you are 
  experiencing poor performance or memory utilization with distinct() in parallel pipelines, switching to 
  sequential execution with BaseStream.sequential() may improve performance.

# mapping
the .map method allows you to select inforomation from the objects.  This is similar to the select statement in
sql.  Note that it does not modify the actual objects in the stream but creates a new object.  You can pass in a 
lambda or a method reference.  Note that the stream output of the .map (and .flatMap) are of the type that is returned, ie

```.map(blargh::getName)

```
would return a stream<whatever type getName returns>

# flatMap
used to coallesce multiple streams into a single stream

# stream methods so far
pg 260

Operation Type 

Return type

Type/functional interface used

Function descriptor
<end of header>

filter
Intermediate
Stream<T>
Predicate<T>
T -> boolean

distinct
Intermediate (stateful-unbounded)
Stream<T>



skip
Intermediate (stateful-bounded)
Stream<T>
long


limit
Intermediate (stateful-bounded)
Stream<T>
long


map
Intermediate
Stream<R>
Function<T, R>
T -> R

flatMap
Intermediate
Stream<R>
Function<T, Stream<R>>
T -> Stream<R>

sorted
Intermediate (stateful-unbounded)
Stream<T>
Comparator<T>
(T, T) -> int

anyMatch
Terminal
boolean
Predicate<T>
T -> boolean

noneMatch
Terminal
boolean
Predicate<T>
T -> boolean

allMatch
Terminal
boolean
Predicate<T>
T -> boolean

findAny
Terminal
Optional<T>



findFirst
Terminal
Optional<T>



forEach
Terminal
void
Consumer<T>
T -> void

collect
terminal
R
Collector<T, A, R>


reduce
Terminal (stateful-bounded)
Optional<T>
BinaryOperator<T>
(T, T) -> T

count
Terminal
long




# TODO
* add functional descriptors to all examples
* add some good reference links
* cleanup .md files to be more useful
* look into regrouping the classes to be more logical groupings



