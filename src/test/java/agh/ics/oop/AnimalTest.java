package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    public void move(){
        Animal animals = new Animal();
        assertEquals(animals.toString(), "NORTH (2,2)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "NORTH (2,3)");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "EAST (2,2)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "EAST (3,2)");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "SOUTH (2,2)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "SOUTH (2,1)");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "WEST (2,2)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "WEST (1,2)");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "NORTH (2,2)");

        assertEquals(animals.toString(), "NORTH (2,2)");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "WEST (2,2)");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "SOUTH (2,2)");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "EAST (2,2)");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "NORTH (2,2)");

        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "NORTH (2,3)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "NORTH (2,4)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "NORTH (2,4)");

        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "NORTH (2,3)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "NORTH (2,2)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "NORTH (2,1)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "NORTH (2,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "NORTH (2,0)");

        animals.move(MoveDirection.RIGHT);
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "EAST (3,0)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "EAST (4,0)");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "EAST (4,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "EAST (3,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "EAST (2,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "EAST (1,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "EAST (0,0)");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "EAST (0,0)");
    }

}
