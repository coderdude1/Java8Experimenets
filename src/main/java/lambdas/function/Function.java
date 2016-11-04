package lambdas.function;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
