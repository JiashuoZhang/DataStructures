package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValueTest {
    @Test
    public void testToString() throws Exception {
        Value v1 = new Value(1);
        Value v2 = new Value(2);
        Value v3 = new Value((float)3.0);
        Value v4 = new Value((float)4.1111);
        Value v5 = new Value("'hello'");
        assertEquals("1", v1.toString());
        assertEquals("2", v2.toString());
        assertEquals("3.000", v3.toString());
        assertEquals("4.111", v4.toString());
        assertEquals("'hello'", v5.toString());
    }

}