package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private ArrayList<Animal> animals = new ArrayList<>();
    private Vector2d up_right;
    private Vector2d down_left;
    public RectangularMap(int width, int height) {
        up_right = new Vector2d(width, height);
        down_left = new Vector2d(0, 0);
    }

    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    public boolean canMoveTo(Vector2d position) {
        return !this.isOccupied(position) && position.precedes(up_right) && position.follows(down_left);
    }

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    public boolean place(Animal animal) {
        boolean placeAbility = this.canMoveTo(animal.getPosition());
        if (placeAbility) {
            animals.add(animal);
        }
        return placeAbility;
    }

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or null if the position is not occupied.
     */
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString(){
        MapVisualizer drawer = new MapVisualizer(this);
        return drawer.draw(down_left, up_right);
    }
}

