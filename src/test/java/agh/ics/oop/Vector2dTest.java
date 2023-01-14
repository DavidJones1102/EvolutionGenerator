package agh.ics.oop;

import agh.ics.oop.MapElementsValues.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//  @setup
public class Vector2dTest {
    Vector2d v1 = new Vector2d(2, 3);
    Vector2d v2 = new Vector2d(2, 3);
    Vector2d v3 = new Vector2d(3, 3);
    Vector2d v4 = new Vector2d(2, 4);
    Vector2d v5 = new Vector2d(5, 5);

    @Test
    public void upperRightTest() {
        assertTrue(v1.upperRight(v2).equals(new Vector2d(2, 3)));
        assertTrue(v4.upperRight(v2).equals(new Vector2d(2, 4)));
        assertTrue(v5.upperRight(v3).equals(new Vector2d(5, 5)));
        assertTrue(v3.upperRight(v5).equals(new Vector2d(5, 5)));
    }

    @Test
    public void lowerLeftTest() {
        assertTrue(v1.lowerLeft(v2).equals(new Vector2d(2, 3)));
        assertTrue(v4.lowerLeft(v2).equals(new Vector2d(2, 3)));
        assertTrue(v5.lowerLeft(v3).equals(new Vector2d(3, 3)));
        assertTrue(v3.lowerLeft(v5).equals(new Vector2d(3, 3)));
    }

    @Test
    public void addTest() {
        assertTrue(v1.add(v2).equals(new Vector2d(4, 6)));
        assertTrue(v4.add(v2).equals(new Vector2d(4, 7)));
        assertTrue(v5.add(v3).equals(new Vector2d(8, 8)));
        assertTrue(v3.add(v5).equals(new Vector2d(8, 8)));
    }

    @Test
    public void subtractTest() {
        assertTrue(v1.subtract(v2).equals(new Vector2d(0, 0)));
        assertTrue(v4.subtract(v2).equals(new Vector2d(0, 1)));
        assertTrue(v5.subtract(v3).equals(new Vector2d(2, 2)));
        assertTrue(v3.subtract(v5).equals(new Vector2d(-2, -2)));
    }

    @Test
    public void equalsTest() {
        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
        assertFalse(v1.equals(v4));
        assertFalse(v1.equals(v5));
    }

    @Test
    public void toStringTest() {
        assertEquals(v1.toString(), "(2,3)");
        assertEquals(v3.toString(), "(3,3)");
        assertEquals(v4.toString(), "(2,4)");
        assertEquals(v5.toString(), "(5,5)");
    }

    @Test
    public void precedesTest() {
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertTrue(v1.precedes(v4));
        assertTrue(v1.precedes(v5));
        assertFalse(v5.precedes(v2));
        assertFalse(v5.precedes(v2));
        assertFalse(v3.precedes(v2));
    }

    @Test
    public void followsTest() {
        assertFalse(v1.follows(v3));
        assertFalse(v1.follows(v4));
        assertFalse(v1.follows(v5));

        assertTrue(v1.follows(v2));
        assertTrue(v5.follows(v2));
        assertTrue(v5.follows(v2));
        assertTrue(v3.follows(v2));
    }

    @Test
    public void oppositeTest() {
        assertTrue(v1.opposite().equals(new Vector2d(-2, -3)));
        assertTrue(v2.opposite().equals(new Vector2d(-2, -3)));
        assertTrue(v3.opposite().equals(new Vector2d(-3, -3)));
        assertTrue(v4.opposite().equals(new Vector2d(-2, -4)));
        assertTrue(v5.opposite().equals(new Vector2d(-5, -5)));
    }
}
