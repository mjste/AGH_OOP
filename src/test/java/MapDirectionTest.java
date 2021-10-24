import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapDirectionTest {

    @Test
    public void testNext(){
        MapDirection first = MapDirection.NORTH;
        MapDirection second = MapDirection.EAST;
        assertEquals(first.next(first), second);

        first = second;
        second = MapDirection.SOUTH;
        assertEquals(first.next(first), second);

        first = second;
        second = MapDirection.WEST;
        assertEquals(first.next(first), second);

        first = second;
        second = MapDirection.NORTH;
        assertEquals(first.next(first), second);
    }

    @Test
    public void testPrevious(){
        MapDirection first = MapDirection.NORTH;
        MapDirection second = MapDirection.WEST;
        assertEquals(first.previous(first), second);

        first = second;
        second = MapDirection.SOUTH;
        assertEquals(first.previous(first), second);

        first = second;
        second = MapDirection.EAST;
        assertEquals(first.previous(first), second);

        first = second;
        second = MapDirection.NORTH;
        assertEquals(first.previous(first), second);

    }
}