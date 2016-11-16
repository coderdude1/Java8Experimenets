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
    }

    /**
     * reduce takes a starting number (0 in this case) and a
     * BinaryOperaator<T> that has a functional descriptor of
     * (T, T) -> T  The input to the firstin this example is a=0, b=1
     */
    private void simpleSum() {
        LOG.info("***************  Simple Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        LOG.info("Sum of list: {}", sum);
    }

    private void methodRefSum() {
        LOG.info("***************  methodRdfSum Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        int sum = numbers.stream().reduce(0, Integer::sum);
        LOG.info("Sum of list: {}", sum);
    }
}
