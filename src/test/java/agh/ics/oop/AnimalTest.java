package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    public void move(){
        RectangularMap map = new RectangularMap(5,5);
        Animal animals = new Animal(map);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "S");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "S");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "W");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "W");
        animals.move(MoveDirection.BACKWARD);
        animals.move(MoveDirection.RIGHT);
        assertEquals(animals.toString(), "N");

        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "W");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "S");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.LEFT);
        assertEquals(animals.toString(), "N");

        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "N");

        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "N");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "N");

        animals.move(MoveDirection.RIGHT);
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.FORWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "E");
        animals.move(MoveDirection.BACKWARD);
        assertEquals(animals.toString(), "E");
    }

}
