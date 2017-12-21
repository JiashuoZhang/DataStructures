package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableTest {
    String types = "a  int ,  b  int,  c  string, d string, e float";
    Table aTable = new Table(types.split(Parser.COMMA));

    @Test
    public void testAddRow() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
        aTable.addRow("2, 5, 'lebron james', 'josh zhang', 3.2");
        aTable.addRow("3, 6, 'harden', 'krystal', 4.2");
        System.out.print(aTable.toString());
    }

    @Test
    public void testPrintCols() throws Exception {
        aTable.addRow("2, 5, 'hello', 'hi', 3.2");
        aTable.addRow("3, 6, 'hello', 'hi', 4.2");
        aTable.addRow("4, 7, 'hello', 'hi', 5.2");
        aTable.addRow("5, 8, 'hello', 'hi', 6.2");
        for (int i = 0; i < 5; i++) {
            aTable.printCol(i);
        }
    }
}