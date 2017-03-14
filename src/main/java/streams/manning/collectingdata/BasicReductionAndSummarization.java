package streams.manning.collectingdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * Section 6.2 in manning book
 */
public class BasicReductionAndSummarization {
    private static final Logger LOG = LoggerFactory.getLogger(BasicReductionAndSummarization.class);

    public static void main(String[] args) {
        BasicReductionAndSummarization basicReductionAndSummarization = new BasicReductionAndSummarization();
        basicReductionAndSummarization.minMaxWithStreams();
        basicReductionAndSummarization.simpleSummation();
    }

    private void minMaxWithStreams() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.createMenu().stream()
                .collect(maxBy(dishCaloriesComparator));//note there is a stream().min and stream().max

        Optional<Dish> minCalorieDish = Dish.createMenu().stream()
                .collect(minBy(dishCaloriesComparator));

        LOG.info("Max calorie dish is: {}", mostCalorieDish);
        LOG.info("Min calorie dish is: {}", minCalorieDish);
    }

    private void simpleSummation() {
        //note there is a summingFloat and summingDouble
        int totalCalories = Dish.createMenu().stream().collect(summingInt(Dish::getCalories));
        LOG.info("Total Calories: {} ", totalCalories);

        double avcGalories = Dish.createMenu().stream().collect(averagingDouble(Dish::getCalories));
        LOG.info("Avg calories: {}", avcGalories);

        IntSummaryStatistics menuStats = Dish.createMenu().stream().collect(summarizingInt(Dish::getCalories));
        LOG.info("Stats: {}", menuStats);
    }
}
