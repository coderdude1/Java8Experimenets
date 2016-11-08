package streams.firstexperiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class needs an overhaul, it was a very quick and dirty experiment to better understand an exception I was seeing
 * elsewhere
 */
public class SimpleStream {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleStream.class);

    public static void main(String[] args) {
        SimpleStream simpleStream = new SimpleStream();
        List<SimplePojo> pojos = simpleStream.getSimplePojos(10);
        simpleStream.happyCase(pojos, "name 3");
        simpleStream.showHandledException(pojos);
    }

    public SimplePojo happyCase(List<SimplePojo> pojos, String filter) {
        SimplePojo first = pojos.stream()
                .filter(sp -> sp.getName().contains(filter))
                .findFirst()
                .orElse(null);
        LOG.info("First: {}", first);
        return first;
    }

    private void showHandledException(List<SimplePojo> pojos) {
        SimplePojo pojo = exceptionHandled(pojos, "blarhg");//should be null
        LOG.info("should be null: " + pojo);

        pojo = exceptionHandled(pojos, "name 3");
        LOG.info("should be 3: " + pojo);
    }

    private SimplePojo exceptionHandled(List<SimplePojo> pojos, String filter) {
        return pojos.stream()
                .filter(sp -> sp.getName().contains(filter))//not in List
                .findFirst()
                .orElse(null);

    }

    private List<SimplePojo> getSimplePojos(int numOfPojos) {
        List<SimplePojo> pojos = new ArrayList<>();

        for (int i = 0; i < numOfPojos; i++) {
            pojos.add(new SimplePojo().setIndex("index: " + i)
                 .setName("name " + i));
        }

        return pojos;
    }
}
