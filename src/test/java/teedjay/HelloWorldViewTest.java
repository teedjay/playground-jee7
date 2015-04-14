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

        v.setName(null);
        assertNull(v.getName());

        v.setName("");
        assertTrue(v.getName().isEmpty());

        v.setName("teeDjay");
        assertEquals("teeDjay", v.getName());

    }

    @Test
    public void testSubmit() {

        HelloWorldView v = new HelloWorldView();

        // null
        v.setName(null);
        v.submit();
        assertEquals("", v.getName());

        // empty and empty trimmed
        v.setName("");
        v.submit();
        assertEquals("", v.getName());

        v.setName("    ");
        v.submit();
        assertEquals("", v.getName());

        // single char and trimmed
        v.setName("t");
        v.submit();
        assertEquals("T", v.getName());

        v.setName("  t  ");
        v.submit();
        assertEquals("T", v.getName());

        // word and trimmed
        v.setName("teeDjay");
        v.submit();
        assertEquals("Teedjay", v.getName());

        v.setName("  teeDjay   ");
        v.submit();
        assertEquals("Teedjay", v.getName());

    }

}