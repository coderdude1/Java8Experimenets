package lambdas.predicates;

@FunctionalInterface
public interface SomeKindOfPredicate<T> {
    boolean someTestCondition(T t );
}
