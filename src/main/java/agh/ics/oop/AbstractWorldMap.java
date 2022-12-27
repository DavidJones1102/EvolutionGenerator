package agh.ics.oop;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected MapVisualizer drawer = new MapVisualizer(this);
    protected Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    protected Map<Vector2d, Grass> grass = new HashMap<>();
    abstract public Vector2d calcUpRight();
    abstract public Vector2d calcLowerLeft();
    abstract public boolean canMoveTo(Vector2d position);
    public Object objectAt(Vector2d position){
        if (animals.get(position)!=null){
            return animals.get(position).getFirst();
        }
        return grass.get(position);

    }
    public boolean place( Animal animal ){
        this.addAnimal(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }
    public void placeGrass(Grass element ){
        grass.put(element.getPosition(), element);
    }
    private void addAnimal(Vector2d position, Animal animal) throws IllegalArgumentException{
        boolean placeAbility = this.canMoveTo(position);
        LinkedList<Animal> elementsOnPosition = animals.get(position);

        if (elementsOnPosition!=null) {
            elementsOnPosition.add(animal);
        } else if ( placeAbility ) {
            elementsOnPosition = new LinkedList<Animal>();
            elementsOnPosition.add(animal);
            animals.put(position, elementsOnPosition);
        } else{
            throw new IllegalArgumentException("Zwierzę nie może być umiejscowione na tej pozycji");
        }
    }
    private void removeAnimal(Vector2d position, Animal animal) throws IllegalArgumentException {
        LinkedList<Animal> elementsOnPosition = animals.get(position);

        if (elementsOnPosition==null) {
            throw new IllegalArgumentException("There is no animal to delete on "+position);
        } else if ( elementsOnPosition.size() == 1 ) {
            animals.remove(position);

        } else{
            elementsOnPosition.remove(animal);
        }
    }
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }
    public String toString(){
        return drawer.draw(this.calcLowerLeft(), this.calcUpRight());
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        this.removeAnimal(oldPosition,animal);
        this.addAnimal(newPosition, animal);
    }
}
