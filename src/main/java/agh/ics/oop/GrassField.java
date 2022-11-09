package agh.ics.oop;

import java.util.ArrayList;

public class GrassField implements IWorldMap {
    public GrassField( int grassAmount){

    }
    private ArrayList<Animal> animals = new ArrayList<>();
    public boolean canMoveTo(Vector2d position){
        return !this.isOccupied(position);
    }
    public boolean place(Animal animal) {
        boolean placeAbility = this.canMoveTo(animal.getPosition());
        if (placeAbility) {
            animals.add(animal);
        }
        return placeAbility;
    }
    public boolean isOccupied(Vector2d position){
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }
        return false;
    }
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }
}
