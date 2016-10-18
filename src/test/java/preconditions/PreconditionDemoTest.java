package preconditions;

import com.google.common.base.*;
import org.junit.Test;

import static org.junit.Assert.*;


public class PreconditionDemoTest {

    public static final String BLARGH = "blargh";

    @Test
    public void divide() {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        double result = preconditionDemo.divide(12, 3);
        assertNotNull(result);
        assertEquals(4, result, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divide_byZero() {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        preconditionDemo.divide(12, 0);
    }
    @Test
    public void jdkNullCheck() throws Exception {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        String result = preconditionDemo.jdkNullCheck(BLARGH);
        assertNotNull(result);
        assertEquals(BLARGH, result);
    }

    @Test(expected = NullPointerException.class)
    public void jdkNullCheck_NullValue() {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        preconditionDemo.jdkNullCheck(null);
    }

    @Test
    public void guavaNullCheck() throws Exception {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        String result = preconditionDemo.guavaNullCheck(BLARGH);
        assertNotNull(result);
        assertEquals(BLARGH, result);
    }

    @Test(expected = NullPointerException.class)
    public void guavaNullCheck_nullValue() throws Exception {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        preconditionDemo.guavaNullCheck(null);
    }

    @Test
    public void testState() {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        preconditionDemo.setInitialized(true);
        boolean result = preconditionDemo.testState();
        assertTrue(result);
    }

    @Test(expected = IllegalStateException.class)
    public void testState_notIntialzied() throws Exception {
        PreconditionDemo preconditionDemo = new PreconditionDemo();
        preconditionDemo.setInitialized(false);
        preconditionDemo.testState();
    }

}