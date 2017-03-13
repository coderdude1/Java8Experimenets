package streams.manning.grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * the groupingBy method accepts a Function (T->R) which is referred to as a classification function in this usage
 * groupingBy returns a map of lists (like a multimap) and can be used to do some really cool complex stuff
 */
public class SimpleGroupings {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleGroupings.class);

    public static void main(String[] args) {
        SimpleGroupings simpleGroupings = new SimpleGroupings();
        simpleGroupings.simpleGroupings();
        simpleGroupings.multiLevelGrouping();
    }

    private void simpleGroupings() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.createMenu().stream()
                .collect(groupingBy(Dish::getType));

        LOG.info("Dishes by type: {}", dishesByType);
    }

    private void multiLevelGrouping() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByCaloricLevel = Dish.createMenu().stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

        LOG.info("dishesByCaloricLevel: {}", dishesByCaloricLevel);
    }

}
