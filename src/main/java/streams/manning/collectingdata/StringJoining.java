package streams.manning.collectingdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import static java.util.stream.Collectors.joining;

public class StringJoining {
    private static final Logger LOG = LoggerFactory.getLogger(StringJoining.class);

    public static void main(String[] args) {
        StringJoining stringJoining = new StringJoining();
        stringJoining.simpleStringJoining();
    }

    private void simpleStringJoining() {
//        map s required if no toString(0 on dish, whcic does and not sure why it barfs
        String shortMenu = Dish.createMenu().stream().map(Dish::getName).collect(joining(", "));
        LOG.info("shortMenu with map: {}", shortMenu);

        //For some reason this generates a compile error even tho Dish has a toString
//        shortMenu = Dish.createMenu().stream().collect(joining(", "));

    }
}
