import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        Integer x = new Integer(1);
        Integer y = new Integer(1);
        Integer z = new Integer(2);
        assertTrue(Flik.isSameNumber(x, y));
        assertTrue(!Flik.isSameNumber(y, z));
    }

}
