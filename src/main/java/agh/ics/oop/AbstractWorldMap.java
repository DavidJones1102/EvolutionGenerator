package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap/*, IPositionChangeObserver*/{
    protected MapVisualizer drawer = new MapVisualizer(this);
    //-protected ArrayList<Animal> animals = new ArrayList<>();
    protected ArrayList<AbstractMapElement> elements= new ArrayList<>();
    //protected Map<Vector2d, Animal> animals = new HashMap<>();
    abstract public Vector2d calcUpRight();
    abstract public Vector2d calcLowerLeft();
    abstract public boolean canMoveTo(Vector2d position);
    public Object objectAt(Vector2d position){
        for (AbstractMapElement element : elements) {
            if (element.isAt(position)) {
                return element;
            }
        }
        return null;
    }
    public boolean place(Animal animal) {
        boolean placeAbility = this.canMoveTo(animal.getPosition());
        if (placeAbility) {
            elements.add(animal);
        }
        return placeAbility;
    }

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }

    public String toString(){
        return drawer.draw(this.calcLowerLeft(), this.calcUpRight());
    }
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//        Animal animal = (Animal) this.objectAt(oldPosition);
//        animals.remove(oldPosition, animal );
//        animals.put(oldPosition, animal);
//    }
}
