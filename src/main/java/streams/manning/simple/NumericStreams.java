package streams.manning.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import java.util.stream.IntStream;

public class NumericStreams {
    private static final Logger LOG = LoggerFactory.getLogger(NumericStreams.class);

    public static void main(String[] args) {
        NumericStreams numericStreams = new NumericStreams();
        numericStreams.summingTheWrongWay();
        numericStreams.summingWithIntStream();
    }

    private void summingTheWrongWay() {
        LOG.info("***************  Sum of calories the wrong way  **********");

        int totalCalories = Dish.createMenu().stream()
                .map(Dish::getCalories)//causes an autobxing to happen for every dish on the stream, expensive
                .reduce(0, Integer::sum);

        LOG.info("sum of calories the wrong way: {}", totalCalories);
    }

    private void summingWithIntStream() {
        LOG.info("***************  Sum of calories intStream  **********");

        int totalCalories = Dish.createMenu().stream()
                .mapToInt(Dish::getCalories)//causes an autobxing to happen for every dish
                .sum();//default value is 0 if the stream is empty

        LOG.info("sum of calories with intStream: {}", totalCalories);
    }

    private void squares() {
        int a = 5;

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

    }
}
