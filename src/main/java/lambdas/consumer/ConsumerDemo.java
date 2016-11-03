package lambdas.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsumerDemo {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerDemo.class);

    public static void main(String[] args) {
        ConsumerDemo consumerDemo = new ConsumerDemo();
        consumerDemo.processString();
    }

    private void processString() {
        Consumer<String> printUpperStringConsumer = (String s) -> LOG.info(s.toUpperCase());//note instance var is inside the lambda
        List<String> stuff = createListOfStrings(100);
        forEach(stuff, printUpperStringConsumer);

//        Consumer consumer = ConsumerDemo::staticForEach;//having troubles getting this to work revisit later
    }

    private List<String> createListOfStrings(int numOfStrings) {
        List<String> results = new ArrayList<>();

        for(int i = 0; i < numOfStrings; i++) {
            results.add("blargh : " + i);
        }
        return results;
    }

    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T i : list) {
            consumer.accept(i);
        }
    }

    private static void staticForEach(List<String> list, Consumer<String> consumer) {
        for (String stringItem : list) {
            consumer.accept(stringItem);
        }
    }
}
