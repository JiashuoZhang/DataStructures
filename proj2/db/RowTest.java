package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class RowTest {
    /* Create a new row with specified attributes. */
    Row row1 = new Row();

    @Test
    public void testToString() throws Exception {
        row1.add(new Value("int", "1"));
        row1.add(new Value("int", "2"));
        row1.add(new Value("string", "'hello'"));
        row1.add(new Value("string", "'world'"));
        row1.add(new Value("float", "3.200"));
        assertEquals("1,2,'hello','world',3.200", row1.toString());
    }
}