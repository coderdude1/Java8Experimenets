package lambdas.predicates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PredicateDemo {
    private static final Logger LOG = LoggerFactory.getLogger(PredicateDemo.class);

    public static void main(String[] args) {
        PredicateDemo predicateDemo = new PredicateDemo();
        predicateDemo.demo();
    }

    private void demo() {
        testNonEmptyString();
        testNumbers();
    }

    private void testNonEmptyString() {
        List<String> inputs = populateWithEmptyAndNonEmptyStrings(100);
        SomeKindOfPredicate<String> predicate = (String s) -> !s.isEmpty();
        List<String> results = filter(inputs, predicate);
        results.forEach(System.out::println);
        LOG.info("Input size: {}", inputs.size());
        LOG.info("filteredSize: {}", results.size());
    }

    private void testNumbers() {
        List<Integer> input = populateWithNumbers(100);
        SomeKindOfPredicate<Integer> predicate = (Integer i) -> i % 2 == 0;
        List<Integer> results = filter(input, predicate);
        results.forEach(System.out::println);
        LOG.info("Input size: {}", input.size());
        LOG.info("result size: {}", results.size());
    }

    private List<Integer> populateWithNumbers(int numOfItems) {
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < numOfItems; i++) {
            results.add(i);
        }

        return results;
    }

    private List<String> populateWithEmptyAndNonEmptyStrings(int numOfItems) {
        List<String> results = new ArrayList<>();
        for (int j = 0; j < numOfItems; j++) {
            if (j % 2 == 0) {
                results.add("");//empty
            } else if (j % 3 == 0) {
                results.add("    ");//spaces
            } else {
                results.add("blargh: " + j);
            }
        }

        return results;
    }

    /**
     * This is the method that will accept predicates and performa filter on those items, ie the boilerplate
     */
    private <T> List<T> filter(List<T> contents, SomeKindOfPredicate<T> someKindOfPredicate) {
        List<T> results = new ArrayList<>();
        for (T content : contents) {    //TODO replace with a stream
            if (someKindOfPredicate.someTestCondition(content)) {
                results.add(content);
            }
        }
        return results;
    }
}
