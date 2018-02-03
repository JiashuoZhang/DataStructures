package db;

import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {
    String types = "a  int ,  b  int,  c  string, d string, e float";
    Table aTable = new Table("t1");

    @Test
    public void testCreateTable() throws Exception {
        aTable.setAttributes(types.split(Parser.COMMA));
        assertEquals(aTable.toString(), "a int,b int,c string,d string,e float");
    }

    @Test
    public void testToString() throws Exception {
        aTable.setAttributes(types.split(Parser.COMMA));
        aTable.insertRow("2, 5, 'hello', 'josh zhang', 3.2");
        aTable.insertRow("3, 6, 'harden', 'james', 4.2");
        System.out.print(aTable.toString());
    }
}