package teedjay;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing the HelloWorldView logic
 *
 * @author thore
 */
public class HelloWorldViewTest {

    @Test
    public void testName() {
        HelloWorldView v = new HelloWorldView();
        v.setName("teeDjay");
        assertEquals(v.getName(), "teeDjay");
    }

}