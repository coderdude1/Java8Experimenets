package lambdas.predicates;

@FunctionalInterface
public interface SomeKindOfPredicate<T> {
    boolean test(T t );
}
