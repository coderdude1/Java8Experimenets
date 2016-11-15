package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class MoreAdvancedFiltering {
    private static final Logger LOG = LoggerFactory.getLogger(MoreAdvancedFiltering.class);

    public static void main(String[] args) {
        MoreAdvancedFiltering moreAdvancedFiltering = new MoreAdvancedFiltering();
        moreAdvancedFiltering.anyMatchDemo();
        moreAdvancedFiltering.allMatchDemo();
        moreAdvancedFiltering.noneMatchDemo();
        moreAdvancedFiltering.findAnyDemo();
        moreAdvancedFiltering.findOneDemo();
    }

    private void anyMatchDemo() {
        LOG.info("\n\n*****************   anyMatch demo *******************");

        if(Dish.createMenu().stream().anyMatch(Dish::isVegetarian)) {
            LOG.info("There are vegetarian dishes in the menu");
        } else {
            LOG.info("there are no vegetarian dishes in the menu");
        }
    }

    private void allMatchDemo() {
        LOG.info("\n\n*****************   allMatch demo *******************");
        allMatchLogic(700);
        allMatchLogic(1000);
    }

    private void allMatchLogic(int calorieLimit) {
        if(Dish.createMenu().stream().allMatch(dish -> {
            return dish.getCalories() < calorieLimit;
        })) {
            LOG.info("all dishes less than {} cals", calorieLimit);
        } else {
            LOG.info("some dishes great than {} cals", calorieLimit);
        }
    }

    private void noneMatchDemo() {//this is the opposite of allMatch
        LOG.info("\n\n*****************   noneMatch demo *******************");
        noneMatchLogic(1000);
        noneMatchLogic(500);
    }

    private void noneMatchLogic(int calories) {
        if(Dish.createMenu().stream()
                .noneMatch(dish ->
                    dish.getCalories() >= calories)) {
            LOG.info("no dishes have more than {} calories", calories);
        } else {
            LOG.info("some dishes are greater than {} calories", calories);
        }
    }

    private void findAnyDemo() {
        LOG.info("\n\n*****************   findAny demo *******************");

        Dish.createMenu().parallelStream()//note with parallel stream findAny returns a differnt value than sequential stream
                .filter(Dish::isVegetarian)
                .findAny()  //grab the first one and return an Optional<Dish>
                .ifPresent(dish ->LOG.info("Found a vegetarian dish: {}", dish.getName())); //notice it is IF not IS\

        Dish.createMenu().stream()//note with parallel stream findAny returns a differnt value than sequential stream
                .filter(Dish::isVegetarian)
                .findAny()  //grab the first one and return an Optional<Dish>
                .ifPresent(dish ->LOG.info("Found a vegetarian dish: {}", dish.getName())); //notice it is IF not IS\
    }

    private void findOneDemo() {
        LOG.info("\n\n*****************   findOne demo *******************");
        Dish.createMenu().parallelStream()//note with parallel stream findFirst returns a same value than sequential stream
                .filter(Dish::isVegetarian)
                .findFirst()  //grab the first one and return an Optional<Dish>
                .ifPresent(dish ->LOG.info("Found a vegetarian dish: {}", dish.getName())); //notice it is IF not IS\

        Dish.createMenu().stream()//note with parallel stream findFirst returns a same value than sequential stream
                .filter(Dish::isVegetarian)
                .findFirst()  //grab the first one and return an Optional<Dish>
                .ifPresent(dish ->LOG.info("Found a vegetarian dish: {}", dish.getName())); //notice it is IF not IS\
    }

}
