package streams.firstexperiment;


import com.google.common.base.MoreObjects;

import java.util.Objects;

public class SimplePojo {
    private String name;
    private String index;

    public String getName() {
        return name;
    }

    public SimplePojo setName(String name) {
        this.name = name;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public SimplePojo setIndex(String index) {
        this.index = index;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass())  {
            return false;
        }
        SimplePojo that = (SimplePojo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("index", index)
                .toString();
    }
}
