# Stuff mostly from the Manning Java In Action

# filtering
use .filter and pass it a predicate lambda or method reference.  There are other types of filtering such as 
.distinct, .limit, .skip

From the [javadoc on distinct](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--) 
> API Note:
  Preserving stability for distinct() in parallel pipelines is relatively expensive (requires that the operation act as a full barrier, with substantial buffering overhead), and stability is often not needed. Using an unordered stream source (such as generate(Supplier)) or removing the ordering constraint with BaseStream.unordered() may result in significantly more efficient execution for distinct() in parallel pipelines, if the semantics of your situation permit. If consistency with encounter order is required, and you are experiencing poor performance or memory utilization with distinct() in parallel pipelines, switching to sequential execution with BaseStream.sequential() may improve performance.

# mapping
the .map method allows you to select inforomation from the objects.  This is similar to the select statement in
sql.  Note that it does not modify the actual objects in the stream but creates a new object.  You can pass in a 
lambda or a method reference.  Note that the stream output of the .map (and .flatMap) are of the type that is returned, ie

```.map(blargh::getName)

```
would return a stream<whatever type getName returns>



