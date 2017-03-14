package streams.manning.partitioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class Partitioning {
    private static final Logger LOG = LoggerFactory.getLogger(Partitioning.class);

    public static void main(String[] args) {
        Partitioning partitioning = new Partitioning();
        partitioning.partitioningExamples();
    }

    private void partitioningExamples() {
        //partitioning will return 2 lists, keyed by true/false
        Map<Boolean, List<Dish>> partitionedMenu = Dish.createMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian));

        LOG.info("partitionedMenu: {}", partitionedMenu);

        //Retreive just the vegetarian list
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        LOG.info("Vegetarian: {}", vegetarianDishes);

        //now just return the vegetiarn dishes directly from the stream via filter vs partition
        List<Dish> vegDishes = Dish.createMenu().stream()
                .filter(Dish::isVegetarian).collect(toList());
        LOG.info("Veg Dishes: {}", vegDishes);

        //this version of partitioningBy takes a 2nd arg that takes a groupingBy collector to group veg by type
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Dish.createMenu().stream()
                .collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)) //2nd collector
                );
        LOG.info("vegetarianDishesByType: {}", vegetarianDishesByType);
    }
}
