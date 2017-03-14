package streams.manning.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * This is the 'Putting it in practice' chapter 5 review
 */
public class TraderQuiz {
    private static final Logger LOG = LoggerFactory.getLogger(TraderQuiz.class);
    private static final String CAMBRIDGE = "Cambridge";
    private static final String MILAN = "Milan";

    private Trader raoul = new Trader("Raoul", CAMBRIDGE);
    private Trader mario = new Trader("Mario", MILAN);
    private Trader alan = new Trader("Alan", CAMBRIDGE);
    private Trader brian = new Trader("Brian", CAMBRIDGE);

    private List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        TraderQuiz traderQuiz = new TraderQuiz();
        traderQuiz.findAllTrans2011Sorted();
        traderQuiz.uniqueCities();
        traderQuiz.sortedTradersFromCambridge();
        traderQuiz.allTradersNamesSortedAlpha();
        traderQuiz.anytradersInMiln();
        traderQuiz.printAllTransactionsFromCambridge();
        traderQuiz.highestTransactionValue();
        traderQuiz.minimumTransactionValue();
    }

    //1.  Find all transactions in the year 2011 and sort them by value (small to high).
    private void findAllTrans2011Sorted() {
        LOG.info("\n\n******************  transactions from 2011 sorted desc  ***************");

        List<Transaction> result = transactions.stream()
               .filter(transaction -> transaction.getYear() == 2011)
//               .sorted((o1, o2) -> o1.getValue() - o2.getValue())// my way
                .sorted(comparing(Transaction::getValue))//book solution
               .collect(Collectors.toList());

        result.forEach(transaction -> LOG.info("value: {}", transaction.getValue()));
    }

    //2.  What are all the unique cities where the traders work?
    private void uniqueCities() {
        LOG.info("\n\n******************  Unique Cities  ***************");

        List<String> result = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        result.forEach(s -> LOG.info("city: {}", s));//note no stream needed to use forEach
    }

    //3.   Find all traders from Cambridge and sort them by name.
    private void sortedTradersFromCambridge() {
        LOG.info("\n\n******************  Traders from cambridge and sorted by name ***************");

        List<Trader> traders = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(CAMBRIDGE))
//                .map(transaction -> transaction.getTrader())
                .map(Transaction::getTrader)//sonarqube
//                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))//my solution
                .sorted(comparing(Trader::getName))//from the book
                .collect(Collectors.toList());


        traders.forEach(trader -> LOG.info("trader: {}", trader.getName()));
    }

    //4.   Return a string of all traders’ names sorted alphabetically.
    private void allTradersNamesSortedAlpha() {
        LOG.info("\n\n******************  String of Traders sorted by name ***************");

        String traders = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(CAMBRIDGE))
                .map(transaction -> transaction.getTrader().getName())
//                .sorted((o1, o2) -> o1.compareTo(o2))//original intellij analysis suggested use method ref
                .sorted(String::compareTo)
                .collect(Collectors.joining(", "));//book was expecting use of reduce but I figured this one out, more performant

        LOG.info("TraderString: {}", traders);
    }

    //5.   Are any traders based in Milan?
    private void anytradersInMiln() {
        LOG.info("\n\n****************** traders in milan? ***************");
        boolean areTradersInMilan = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(MILAN))
                .count() > 1;
        LOG.info("tradersInMilan = {}", areTradersInMilan);

        //Book solution, probably better than mine
        areTradersInMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals(MILAN));
        LOG.info("book solution tradersInMilan = {}", areTradersInMilan);
    }

    //6.   Print all transactions’ values from the traders living in Cambridge.

    private void printAllTransactionsFromCambridge() {
        LOG.info("\n\n****************** transactioinsFromCambridge? ***************");

        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(CAMBRIDGE))
                .forEach(transaction -> LOG.info("Transaction Value: {}", transaction));
    }

    //7.  What’s the highest value of all the transactions?
    private void highestTransactionValue() {
        LOG.info("\n\n****************** max trans value and trans with max value ***************");

        transactions.stream()
//                .map(transaction -> transaction.getValue())//output value from transaction
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(integer -> LOG.info("max trans vluae {}", integer));

        //try to keep the transaction with the highest value

        Transaction maxTrans = transactions.stream()
                .max((o1, o2) -> o1.getValue() - o2.getValue())
                .orElseGet(() -> null);

        LOG.info("MaxTrans: {}", maxTrans);
    }

    //8.  Find the transaction with the smallest value.
    private void minimumTransactionValue() {
        LOG.info("\n\n****************** max trans value and trans with max value ***************");

        transactions.stream()
//                .map(transaction -> transaction.getValue())//output value from transaction
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(integer -> LOG.info("min trans vluae {}", integer));

        //try to keep the transaction with the highest value

        Transaction minTrans = transactions.stream()
                .min((o1, o2) -> o1.getValue() - o2.getValue())//note htis can be done many wayw
                .orElseGet(null);

        LOG.info("Mainrans: {}", minTrans);

        //book example
        transactions.stream().min(comparing(Transaction::getValue))
                .ifPresent(transaction -> LOG.info("book example minTrans []", transaction));
    }
}
