package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class RowTest {
    String type = "a  int ,  b  int,  c  string, d string, e float";
    Attributes attr = new Attributes(type.split(Parser.COMMA));
    /* Create a new row with specified attributes. */
    Row row1 = new Row(attr);
    Row row2 = new Row(attr);

    String values1 = "2, 5, 'lebron james', 'josh zhang', 3.2";
    String values2 = "3, 6, 'james harden', 'krystal', 2.6799";

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
        row1.add(values1);
        row2.add(values2);
        assertEquals("2,5,'lebron james','josh zhang',3.200", row1.toString());
        assertEquals("3,6,'james harden','krystal',2.680", row2.toString());
    }
}