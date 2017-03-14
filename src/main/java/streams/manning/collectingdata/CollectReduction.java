package streams.manning.collectingdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

public class CollectReduction {
    private static final Logger LOG = LoggerFactory.getLogger(CollectReduction.class);

    public static void main(String[] args) {
        CollectReduction collectReduction = new CollectReduction();
        collectReduction.simpleReductions();

    }

    private void simpleReductions() {
        //using reduction to calc total calories
        int totalCalories = Dish.createMenu().stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j
        ));
        //0 is the starting point, and is returend if the stream is empty
        //2nd arg is to call the getCalories on each element in the stream
        //lambda BinaryOperator aggregates 2  items into a single value
        LOG.info("Reduction SUM: {}", totalCalories);

        //intellij suggests to use stream().reduce() but there are differences in behaviour, ie with nulls
        //TODO implement both and compare them to understand the diffs
        Optional<Dish> mostCalorieDish = Dish.createMenu().stream()
                .collect(reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
// note there is no intial starting point passed in, 1st item instream is starting point and an identity function
        LOG.info("MostCalorieDish: {}", mostCalorieDish);

        int otherTotalCalories = Dish.createMenu().stream()
                .collect(reducing(0,
                Dish::getCalories,
                Integer::sum));

        LOG.info("OtherMostCalDish: {}", otherTotalCalories);

    }
}
