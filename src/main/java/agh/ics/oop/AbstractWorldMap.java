package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap{
    protected MapVisualizer drawer = new MapVisualizer(this);
    protected ArrayList<Animal> animals = new ArrayList<>();
    abstract public Vector2d calcUpRight();
    abstract public Vector2d calcLowerLeft();
    abstract public boolean canMoveTo(Vector2d position);
    abstract public Object objectAt(Vector2d position);
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

    public String toString(){
        return drawer.draw(this.calcLowerLeft(), this.calcUpRight());
    }
}
