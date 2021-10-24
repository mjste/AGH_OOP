import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void addTest(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v2 = new Vector2d(1, 2);
        assertTrue(v1.add(v2).equals(new Vector2d(2, 6)));
    }

    @Test
    public void subtractTest(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v2 = new Vector2d(1, 2);
        assertTrue(v1.subtract(v2).equals(new Vector2d(0, 2)));
    }

    @Test
    public void toStringTest(){
        Vector2d v1 = new Vector2d(1, 2);
        assertEquals(v1.toString(), "(1,2)");
    }

    @Test
    public void precedesTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(0, 1);
        Vector2d v3 = new Vector2d(1, 0);
        Vector2d v4 = new Vector2d(1, 1);

        assertTrue(v1.precedes(v1));
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertTrue(v1.precedes(v4));

        assertFalse(v2.precedes(v1));
        assertTrue(v2.precedes(v2));
        assertFalse(v2.precedes(v3));
        assertTrue(v2.precedes(v4));

        assertFalse(v3.precedes(v1));
        assertFalse(v3.precedes(v2));
        assertTrue(v3.precedes(v3));
        assertTrue(v3.precedes(v4));

        assertFalse(v4.precedes(v1));
        assertFalse(v4.precedes(v2));
        assertFalse(v4.precedes(v3));
        assertTrue(v4.precedes(v4));
    }

    @Test
    public void followsTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(0, 1);
        Vector2d v3 = new Vector2d(1, 0);
        Vector2d v4 = new Vector2d(1, 1);

        assertTrue(v1.follows(v1));
        assertFalse(v1.follows(v2));
        assertFalse(v1.follows(v3));
        assertFalse(v1.follows(v4));

        assertTrue(v2.follows(v1));
        assertTrue(v2.follows(v2));
        assertFalse(v2.follows(v3));
        assertFalse(v2.follows(v4));

        assertTrue(v3.follows(v1));
        assertFalse(v3.follows(v2));
        assertTrue(v3.follows(v3));
        assertFalse(v3.follows(v4));

        assertTrue(v4.follows(v1));
        assertTrue(v4.follows(v2));
        assertTrue(v4.follows(v3));
        assertTrue(v4.follows(v4));
    }

    @Test
    public void upperRightTest(){
        Vector2d v1 = new Vector2d(1, 3);
        Vector2d v2 = new Vector2d(3, 4);

        assertTrue(new Vector2d(3, 4).equals(v1.upperRight(v2)));
    }

    @Test
    public void lowerLeftTest(){
        Vector2d v1 = new Vector2d(1, 3);
        Vector2d v2 = new Vector2d(3, 4);

        assertTrue(new Vector2d(1, 3).equals(v1.lowerLeft(v2)));
    }

    @Test
    public void oppositeTest(){
        Vector2d v = new Vector2d(1, 4);
        assertTrue(v.opposite().equals(new Vector2d(-1, -4)));
    }


}
