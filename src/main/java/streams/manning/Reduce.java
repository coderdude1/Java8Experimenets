package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Reductions are used to calculate stuff, sums, statistics, etc from a
 * stream.  In functional programming this is often called a fold, ie folding
 * a piece of paper into smaller squares, resulting in a single value
 */
public class Reduce {
    private static final Logger LOG = LoggerFactory.getLogger(Reduce.class);

    public static void main(String[] args) {
        Reduce reduce = new Reduce();
        reduce.simpleSum();
        reduce.methodRefSum();
        reduce.showOverloadedReduceWithNoInitialValue();
        reduce.showMinMax();
        reduce.showMapReduceAsCount();
    }

    /**
     * reduce takes a starting number (0 in this case) and a
     * BinaryOperaator<T> that has a functional descriptor of
     * (T, T) -> T  The input to the firstin this example is a=0, b=1
     *
     * then the next step would be a=1 (previous output from reduce, and b=3 (next value from stream)
     */
    private void simpleSum() {
        LOG.info("***************  Simple Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        LOG.info("Sum of list: {}", sum);
    }

    /**
     * Same as simpleSum but using a method reference on the Integer class sum that is part of java 8
     */
    private void methodRefSum() {
        LOG.info("***************  methodRdfSum Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        int sum = numbers.stream().reduce(0, Integer::sum);
        LOG.info("Sum of list: {}", sum);
    }

    /**
     * This shows the variant of reduce that accepts no initial value.  It returns an optional because if the stream is
     * empty it would otherwise return null.
     */
    private void showOverloadedReduceWithNoInitialValue() {
        LOG.info("***************  noInitialValue Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        numbers.stream().reduce((a, b) -> a + b)
                .ifPresent(total -> LOG.info("sum: {}", total));
    }

    /**
     * Returns an optional since we are using the no intial value, therefore if stream is empty it would otherwise
     * return null
     *
     * min is same as (x,  y) -> x<y?x:y
     */
    private void showMinMax() {
        LOG.info("***************  min max demo   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);

        numbers.stream().reduce(Integer::min) //returns optional
                .ifPresent(min -> LOG.info("min: {}", min));

        numbers.stream().reduce(Integer::max) //returns optional, can't reuse streams (demo in later code)
            .ifPresent(max -> LOG.info("max: {}", max));
    }

    /**
     * This is the same as .count() using map/reduce
     */
    private void showMapReduceAsCount() {
        int count = Dish.createMenu().stream()
                .map(dish -> 1) //Always returns 1 (think of it as the increment
                .reduce(0, (a, b) -> a + b);//adds 1 from map to the previous value
        LOG.info("MapReduce count: {}", count);
    }
}
