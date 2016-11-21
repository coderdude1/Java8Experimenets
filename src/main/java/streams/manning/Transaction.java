package streams.manning;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Transaction {
    private Trader trader;
    private int year;
    private int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public Transaction setTrader(Trader trader) {
        this.trader = trader;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Transaction setYear(int year) {
        this.year = year;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Transaction setValue(int value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return year == that.year &&
                value == that.value &&
                Objects.equals(trader, that.trader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trader, year, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("trader", trader)
                .add("year", year)
                .add("value", value)
                .toString();
    }
}
