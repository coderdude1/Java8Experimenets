package streams.manning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

public class DishExperiments {
    private static final Logger LOG = LoggerFactory.getLogger(DishExperiments.class);

    public static void main(String[] args) {
        DishExperiments dishExperiments = new DishExperiments();
        dishExperiments.intermediateOperations();
    }

    /**
     * Note the output will interleave filter/map; filter/map;
     *
     * Note no if/else logic, no looping.  This is a declaritive composition
     */
    private void intermediateOperations() {
        //Note that this looks like a builder pattern
        List<String> names = Dish.createMenu().stream()
                .filter(d -> {  //requires a predicate T -> boolean Intermediate Operation
                    LOG.info("filtering: " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> { //transform or return value.  note Function T -> R  Intermediate Operation
                    LOG.info("map: " + d.getName());
                    return d.getName();
                })//loop/stream fusion since filter and map run together
                .limit(3) //short circuit ie stop after 3.  Intermediate Operation
                .collect(Collectors.toList()); //terminal Operation

        LOG.info("names: " + names);
    }

    private void terminalOperations() {
        Dish.createMenu().stream().forEach(System.out::println);//it produces a result from a pipeline, therefore terminal
    }

}
