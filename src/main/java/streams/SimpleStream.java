package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleStream {

    public static void main(String[] args) {
        SimpleStream simpleStream = new SimpleStream();
        List<SimplePojo> pojos = simpleStream.getSimplePojos(10);
        simpleStream.happyCase(pojos, "name 3");
        simpleStream.eatException(pojos);
        simpleStream.showHandledException(pojos);
    }


    private void happyCase(List<SimplePojo> pojos, String filter) {
        SimplePojo first = pojos.stream()
                .filter(sp -> sp.getName().contains(filter))
                .findFirst()
                .get();
        System.out.println(first);
    }

    private void eatException(List<SimplePojo> pojos) {
        try {
            SimplePojo first = pojos.stream()
                    .filter(sp -> sp.getName().contains("blargh"))//not in List
                    .findFirst()
                    .get();
            System.out.println(first);
        } catch (NoSuchElementException e) {
            System.out.println("Expected exception caught");
        }
    }

    private void showHandledException(List<SimplePojo> pojos) {
        SimplePojo pojo = exceptionHandled(pojos, "blarhg");//should be null
        System.out.println("should be null: " + pojo);

        pojo = exceptionHandled(pojos, "name 3");
        System.out.println("should be 3: " + pojo);
    }

    private SimplePojo exceptionHandled(List<SimplePojo> pojos, String filter) {
        SimplePojo first = pojos.stream()
                .filter(sp -> sp.getName().contains(filter))//not in List
                .findFirst()
                .orElse(null);

        return first;
    }

    private List<SimplePojo> getSimplePojos(int numOfPojos) {
        List<SimplePojo> pojos = new ArrayList<SimplePojo>();

        for (int i = 0; i < numOfPojos; i++) {
            pojos.add(new SimplePojo().setIndex("index: " + i)
                 .setName("name " + i));
        }

        return pojos;
    }
}
