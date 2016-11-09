package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * At the moment these are mostly from the manning java8 book ch5
 */
public class DishFiltering {
    private static final Logger LOG = LoggerFactory.getLogger(DishFiltering.class);

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
                .filter(Dish::isVegetarian) //method refeerence predicate
                .collect(Collectors.toList());

        LOG.info("MethodRef: {}", retval);
    }

    private void simpleEvenFilterWithDistinct() {
        List<Integer> retval = Arrays.asList(1, 2, 2, 3, 3, 2, 4, 5, 6, 5, 6).stream()
                .filter(i -> i %2 == 0)
                .distinct()
                .collect(Collectors.toList());

        LOG.info("distinct filter: {}", retval);
    }

    //note that the order is only due to the list, if it was a set no guarntees
    private void truncateWithLimit() {
        int calorieCount = 300, skipCount = 3;
        List<Dish> retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieCount)
                .limit(skipCount)
                .collect(Collectors.toList());

        LOG.info("truncWithLimit: {}", retval);
        LOG.info("size: {}", retval.size());
    }

    private void useSkip() {
        int calorieThreshold = 200, skipCount = 2, limitCount = 2;
        List<Dish> retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .skip(skipCount)
                .collect(Collectors.toList());
        LOG.info("skip {} : {}", skipCount, retval);
        LOG.info("count: {}", retval.size());

        retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .skip(skipCount)
                .limit(limitCount)
                .collect(Collectors.toList());

        LOG.info("skipBeforeLimit: {}", retval);
        LOG.info("count: {}", retval.size());

        retval = Dish.createMenu().stream()
                .filter(d -> d.getCalories() > calorieThreshold)
                .limit(limitCount)
                .skip(skipCount)
                .collect(Collectors.toList());

        LOG.info("limitBeforeSkip: {}", retval);
        LOG.info("count: {}", retval.size());
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
