package preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.*;

import static com.google.common.base.Preconditions.*;

public class PreconditionDemo {
    private static final Logger LOG = LoggerFactory.getLogger(PreconditionDemo.class);

    private boolean initialized;

    public boolean isInitialized() {
        return initialized;
    }

    public PreconditionDemo setInitialized(boolean initialized) {
        this.initialized = initialized;
        return this;
    }

    //simple precondition to prevent a divide by zero
    public double divide(int numerator, int divisor) {
        checkArgument(divisor != 0, "divisor must not be 0.  numerator=%s divisor=%s", numerator, divisor);//note printf style
        return (double)numerator/divisor;
    }

    public String jdkNullCheck(String input) {
        requireNonNull(input, "input must not be null");
        LOG.info("jdk input: {}", input);
        return input;
    }

    public String guavaNullCheck(String input) {
        checkNotNull(input, "input must not be null");
        LOG.info("Guava input: {}", input);
        return input;
    }

    public boolean testState() {
        checkState(initialized, "cannot do pretend action since we are not initialized");
        LOG.info("Initialized: {}", initialized);
        return initialized;
    }

    //maybe add array index checks
}
