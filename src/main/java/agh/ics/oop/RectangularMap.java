package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
   private MapVisualizer drawer = new MapVisualizer(this);
    private ArrayList<Animal> animals = new ArrayList<>();
    private Vector2d up_right;
    private Vector2d down_left;
    public RectangularMap(int width, int height) {
        up_right = new Vector2d(width-1, height-1);
        down_left = new Vector2d(0, 0);
    }
    public boolean canMoveTo(Vector2d position) {
        return  position.precedes(up_right) && position.follows(down_left) && !this.isOccupied(position) ;
    }
    public boolean place(Animal animal) {
        boolean placeAbility = this.canMoveTo(animal.getPosition());
        if (placeAbility) {
            animals.add(animal);
        }
        return placeAbility;
    }
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString(){
        return drawer.draw(down_left, up_right);
    }
}

