package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Reduce {
    private static final Logger LOG = LoggerFactory.getLogger(Reduce.class);

    public static void main(String[] args) {
        Reduce reduce = new Reduce();
        reduce.simpleSum();
    }

    private void simpleSum() {
        LOG.info("***************  Simple Sum   *****************");

        List<Integer> numbers = Arrays.asList(1, 3, 45, 33, 2, 1);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        LOG.info("Sum of list: {}", sum);
    }
}
