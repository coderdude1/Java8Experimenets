package streams.manning.summarizing;

import streams.manning.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * Section 6.2 in manning book
 */
public class BasicReductionAndSummarization {
    public static void main(String[] args) {
        BasicReductionAndSummarization basicReductionAndSummarization = new BasicReductionAndSummarization();
        basicReductionAndSummarization.minMaxWithStreams();
        basicReductionAndSummarization.simpleSummation();
    }

    public void minMaxWithStreams() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.createMenu().stream()
                .collect(maxBy(dishCaloriesComparator));

        Optional<Dish> minCalorieDish = Dish.createMenu().stream()
                .collect(minBy(dishCaloriesComparator));

        System.out.println("Max calorie dish is :" + mostCalorieDish);
        System.out.println("Min calorie dish is :" + minCalorieDish);
    }

    public void simpleSummation() {
        //note there is a summingFloat and summingDouble
        int totalCalories = Dish.createMenu().stream().collect(summingInt(Dish::getCalories));
        System.out.println("Total Calories = " + totalCalories);

        double avcGalories = Dish.createMenu().stream().collect(averagingDouble(Dish::getCalories));
        System.out.println("Avg calories: " + avcGalories);

        IntSummaryStatistics menuStats = Dish.createMenu().stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("Stats: " + menuStats);
    }
}
