package lambdas.function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randy on 11/3/2016.
 */
public class FunctionDemo {
    public static void main(String[] args) {
        FunctionDemo functionDemo = new FunctionDemo();
        functionDemo.stringToInt();
    }

    @SuppressWarnings("squid:S106")//don't care about system.out, want to show method reference
    private void stringToInt() {
        List<String> inputs = new ArrayList<>();
        inputs.add("blargh1");
        inputs.add("blargh22");
        inputs.add("loooooooooooooop");
        inputs.add("ooiojoiooij");
        Function<String, Integer> fn = (String s) -> Integer.valueOf(s.length());
        List<Integer> length = map(inputs, fn);
        length.forEach(System.out::println);
    }

    //this is the method that accepts a Function lambda
    private <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> results = new ArrayList<>();
        list.forEach(item -> results.add(function.apply(item)));
        return results;
    }
}
