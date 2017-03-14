package streams.manning.simple;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Trader {

    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public Trader setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Trader setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trader trader = (Trader) o;
        return Objects.equals(name, trader.name) &&
                Objects.equals(city, trader.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("city", city)
                .toString();
    }
}
