package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTests {
    @Test
    public void placeAndIsOccupiedTest(){
        RectangularMap map = new RectangularMap(40,30);
        Animal animal1 = new Animal(map, new Vector2d(10,10));
        Animal animal2 = new Animal(map, new Vector2d(3,3));
        Animal animal3 = new Animal(map, new Vector2d(30,15));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertTrue(map.isOccupied(new Vector2d(10,10)));
        assertTrue(map.isOccupied(new Vector2d(3,3)));
        assertTrue(map.isOccupied(new Vector2d(30,15)));
        assertFalse(map.isOccupied(new Vector2d(13,13)));
        assertFalse(map.isOccupied(new Vector2d(15,30)));
    }

    @Test
    public void objectAtTest(){
        RectangularMap map = new RectangularMap(40,30);
        Animal animal1 = new Animal(map, new Vector2d(10,10));
        Animal animal2 = new Animal(map, new Vector2d(3,3));
        Animal animal3 = new Animal(map, new Vector2d(30,15));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertEquals(animal1, map.objectAt(new Vector2d(10,10)));
        assertEquals(animal2, map.objectAt(new Vector2d(3,3)));
        assertEquals(animal3, map.objectAt(new Vector2d(30,15)));
        assertFalse(animal1 == map.objectAt(new Vector2d(30,15)));
    }

    @Test
    public void canMoveToTest(){
        RectangularMap map = new RectangularMap(41,31);
        Animal animal1 = new Animal(map, new Vector2d(10,10));
        Animal animal2 = new Animal(map, new Vector2d(3,3));
        Animal animal3 = new Animal(map, new Vector2d(30,15));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertFalse( map.canMoveTo(new Vector2d(30,15)) );
        assertFalse( map.canMoveTo(new Vector2d(10,10)) );
        assertFalse( map.canMoveTo(new Vector2d(3,3)) );
        assertFalse( map.canMoveTo(new Vector2d(40,40)) );
        assertFalse( map.canMoveTo(new Vector2d(-1,-1)) );

        assertTrue( map.canMoveTo(new Vector2d(15,30)));
        assertTrue( map.canMoveTo(new Vector2d(30,30)));
    }

    @Test
    public void integralTest(){
        RectangularMap map = new RectangularMap(6,6);
        Animal animal1 = new Animal(map, new Vector2d(5,5));
        Animal animal2 = new Animal(map, new Vector2d(0,0));
        Animal animal3 = new Animal(map, new Vector2d(3,2));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(5,5)));

        animal1.move(MoveDirection.BACKWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(5,4)));

        animal1.move(MoveDirection.BACKWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(5,3)));

        animal1.move(MoveDirection.LEFT);

        assertEquals(animal1, map.objectAt(new Vector2d(5,3)));

        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(4,3)));

        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(3,3)));

        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(3,3)));

        animal3.move(MoveDirection.BACKWARD);
        assertEquals(animal3, map.objectAt(new Vector2d(3,1)));

        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(3,2)));

        animal3.move(MoveDirection.BACKWARD);
        assertEquals(animal3, map.objectAt(new Vector2d(3,0)));

        animal3.move(MoveDirection.BACKWARD);
        assertEquals(animal3, map.objectAt(new Vector2d(3,0)));

        animal2.move(MoveDirection.RIGHT);
        animal2.move(MoveDirection.FORWARD);
        assertEquals(animal2, map.objectAt(new Vector2d(1,0)));

        animal2.move(MoveDirection.FORWARD);
        assertEquals(animal2, map.objectAt(new Vector2d(2,0)));

        animal2.move(MoveDirection.FORWARD);
        assertEquals(animal2, map.objectAt(new Vector2d(2,0)));
    }

}
