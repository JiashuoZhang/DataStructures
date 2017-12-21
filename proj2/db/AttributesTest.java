package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class AttributesTest {
    String type = "a  int ,  b  int,  c  string, d string, e float";
    Attributes attr = new Attributes(type.split(Parser.COMMA));

    @Test
    public void testSize() throws Exception {
        assertEquals(5, attr.size());
    }

    @Test
    public void testGet() throws Exception {
        assertEquals("int", attr.types[0]);
        assertEquals("int", attr.types[1]);
        assertEquals("string", attr.types[2]);
        assertEquals("string", attr.types[3]);
        assertEquals("float", attr.types[4]);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("a int,b int,c string,d string,e float", attr.toString());
    }
}