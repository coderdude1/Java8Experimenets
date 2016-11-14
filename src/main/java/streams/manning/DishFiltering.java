package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * At the moment these are mostly from the manning java8 book ch5.1 which focuses on filtering
 *
 * Order is only guarnteed if the underlying source is has order, ie like a list, vs a set
 */
public class DishFiltering {
    private static final Logger LOG = LoggerFactory.getLogger(DishFiltering.class);
    public static final String COUNT_LOGGER = "count: {}";

    public static void main(String[] args) {
        DishFiltering dishFiltering = new DishFiltering();
        dishFiltering.simpleMethodReferencePredicate();
        dishFiltering.simpleEvenFilterWithDistinct();
        dishFiltering.truncateWithLimit();
        dishFiltering.useSkip();
        dishFiltering.filterTwoMeat();
    }

    private void simpleMethodReferencePredicate() {
        List<Dish> retval =  Dish.createMenu().stream()
                .filter(Dish::isVegetarian) //method reference predicate
                .collect(Collectors.toList());

        LOG.info("MethodRef: {}", retval);
    }

    private void simpleEvenFilterWithDistinct() {
        Integer[] numbers = {1, 2, 2, 3, 3, 2, 4, 5, 6, 5, 6};

        List<Integer> retval = Arrays.stream(numbers)
                .filter(i -> i %2 == 0)
                .distinct()
                .collect(Collectors.toList());

        LOG.info("distinct filter: {}", retval);
    }

    private void truncateWithLimit() {
        int calorieCount = 300;
        int skipCount = 3;

        List<Dish> retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieCount)
                .limit(skipCount)
                .collect(Collectors.toList());

        LOG.info("truncWithLimit: {}", retval);
        LOG.info("size: {}", retval.size());
    }

    private void useSkip() {
        int calorieThreshold = 200;
        int skipCount = 2;
        int limitCount = 2;

        List<Dish> retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .skip(skipCount)
                .collect(Collectors.toList());
        LOG.info("skip {} : {}", skipCount, retval);
        LOG.info(COUNT_LOGGER, retval.size());

        retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .skip(skipCount)
                .limit(limitCount)
                .collect(Collectors.toList());

        LOG.info("skipBeforeLimit: {}", retval);
        LOG.info(COUNT_LOGGER, retval.size());

        retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .limit(limitCount)
                .skip(skipCount)
                .collect(Collectors.toList());

        LOG.info("limitBeforeSkip: {}", retval);
        LOG.info(COUNT_LOGGER, retval.size());
    }

    private void filterTwoMeat() {
        List<Dish> retval = Dish.createMenu().stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        LOG.info("filter first 2 meat: {}", retval);
        LOG.info("size: {}", retval.size());
    }
}
