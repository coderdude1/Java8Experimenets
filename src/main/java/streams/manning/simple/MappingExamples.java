package streams.manning.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.manning.core.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingExamples {
    private static final Logger LOG = LoggerFactory.getLogger(MappingExamples.class);

    private static final String WORD_PREFIX = "Word: {}";
    private static final String LETTERS_PREFIX = "letters: {}";

    public static void main(String[] args) {
        MappingExamples mappingExamples = new MappingExamples();
        mappingExamples.simpleStringLengthExample();
        mappingExamples.dishNameLength();
        mappingExamples.flattenStreamIncorrect();
        mappingExamples.flattenStreamIncorrectTwo();
        mappingExamples.flattenStreamFlatMap();
        mappingExamples.returnSquaresOfNumberStream();
        mappingExamples.returnCombinedOutputFromTwoStreams();
        mappingExamples.combineTwoStreamsDivisibleByThree();
    }

    private void simpleStringLengthExample() {
        List<Integer> wordLengths = getWords().stream()//returns Stream<String>
                .map(String::length) //returns Stream<Integer>
                .collect(Collectors.toList());

        LOG.info("Word Length: {}", wordLengths);
    }

    private List<String> getWords() {
        return Arrays.asList("Blargh", "Snorklee", "arrgggh", "ummmmmmmmm", "blorp");
    }

    private void dishNameLength() {
        List<Integer> dishNameLength = Dish.createMenu().stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        LOG.info("Dish name length: {}", dishNameLength);
    }

    /*
    Doesn't work to return a list of unique characters in the array of words.  This is since the stream output from
    the map is a Stream<String[]> (an array of strings) instead of Stream<String> where each string emitted by the stream
    is a character from the word.
     */
    private void flattenStreamIncorrect() {
        LOG.info("\n\n***************  Incorrect one");

        getWords().stream()
                .map(word -> {
                    LOG.info(WORD_PREFIX, word);
                    String[] letters = word.split("");//note this is an array and is why this won't work.  ie returns Stream<String[]> not Stream<String>
                    LOG.info(LETTERS_PREFIX, Arrays.toString(letters));
                    return letters;
                })
                .distinct() //processes each array, one element at a time so nothing gets de-dupped
                .forEach(t ->   //t is an array of strings.  using foreach so we can place breakpoint to see what content looks like
                        LOG.info("{}", Arrays.toString(t))//convient way to place a breakpoint
                );
    }

    /*
    This one doesn't work since the 2nd map operation returns a Stream<Stream<String>>.  we wouldh ave to process each
    stream seperatly
     */
    private void flattenStreamIncorrectTwo() {
        LOG.info("\n\n***************  Incorrect two");
        getWords().stream()//this is to show how to create a stream from an array
                .map(word -> {
                    LOG.info(WORD_PREFIX, word);
                    String[] letters = word.split("");//note this is an array and is why this won't work.  ie returns Stream<String[]> not Stream<String>
                    LOG.info(LETTERS_PREFIX, Arrays.toString(letters));
                    return letters;
                })
                .map(Arrays::stream)//makes each array into it's own stream
                .distinct()
                .forEach(t ->   //t is an array of strings.  using foreach so we can see what it looks like in debugger
                        LOG.info("{}", t)//convient way to place a breakpoint
                );
    }

    //this will actually combine all the streams into one stream
    private void flattenStreamFlatMap() {
        LOG.info("\n\n***************  Correct using flatMap");

        List<String> retval = getWords().stream()
                .map(word -> {
                    LOG.info(WORD_PREFIX, word);
                    String[] letters = word.split("");//creates an array with each letter
                    LOG.info(LETTERS_PREFIX, Arrays.toString(letters));
                    return letters;
                })
                .flatMap(Arrays::stream)//makes each array into it's own stream, then flatmap will consolidate into one single stream
                .distinct()
                .collect(Collectors.toList());

        printCommaSeperatedList(retval);
    }

    private void returnSquaresOfNumberStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        LOG.info("\n\n***************  Squares");

        List<Integer> squares = numbers.stream()
                .map(t -> t * t)
                .collect(Collectors.toList());//note no guarantee of order, add a .sorted after I figure out how they work

        printCommaSeperatedList(squares);
    }

    private void returnCombinedOutputFromTwoStreams() {
        LOG.info("\n\n***************  Pairs");

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                                .map(n2 -> new int[]{n1, n2})
                )
        .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        pairs.forEach(pair -> {
                    String currentPair = "(" + (pair[0]) + ", " + pair[1] + "),";
                    sb.append(currentPair);
                });

        LOG.info(sb.toString());
    }

    private void combineTwoStreamsDivisibleByThree() {
        LOG.info("\n\n***************  Pairs divisible by 3");

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                        .filter(n2 -> (n1 + n2) % 3 == 0)
                        .map(n2 -> new int[]{n1, n2})
                )
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        pairs.forEach(pair -> sb.append("(" + (pair[0]) + ", " + pair[1] + "), "));//TODO figure out how to not appned ',' on last element

        LOG.info(sb.toString());
    }


    @SuppressWarnings("squid:S106")//gonna use sout for now
    private void printCommaSeperatedList(List<?> retval) {
        retval.stream()
                .map(t -> t + ",")//TODO figure out how to not append ',' on last element
                .sorted()
                .forEach(System.out::print);//ignoring the sonar comment for this for now
    }
}
