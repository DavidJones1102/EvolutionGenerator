package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveDirectionTest {
    @Test
    public void nextTest(){
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        assertEquals(north.next(),MapDirection.EAST);
        assertEquals(east.next(),MapDirection.SOUTH);
        assertEquals(south.next(),MapDirection.WEST);
        assertEquals(west.next(),MapDirection.NORTH);
    }
}
