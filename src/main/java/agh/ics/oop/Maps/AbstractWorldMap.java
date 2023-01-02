package agh.ics.oop.Maps;


import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.Simulation.SimulationEngine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    protected int energyNeddedToCopulation;
    protected Map<Vector2d, Grass> grass = new HashMap<>();
    protected LinkedList<Animal> deadAnimals = new LinkedList<>();
    protected int grassProfit;
    protected int grassDaily;
    protected int deadAnimalsCount=0;
    protected float deadAnimalsAvgAge=0;
    protected SimulationEngine engine;
    abstract public Vector2d calcUpRight();
    abstract public Vector2d calcLowerLeft();
    abstract public boolean canMoveTo(Vector2d position);
    abstract public Vector2d positionInBounds(Animal animal, Vector2d newPosition);
    abstract public void spawnGrass(int grassAmount);
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
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        this.removeAnimal(oldPosition,animal);
        this.addAnimal(newPosition, animal);
    }
    private boolean compareAnimals(Animal temporaryTheStrongest,Animal animal){
        int energyDiff = temporaryTheStrongest.getEnergy() - animal.getEnergy();
        int ageDiff = temporaryTheStrongest.getAge() - animal.getAge();
        int childDiff = temporaryTheStrongest.getChildrenCount() - animal.getChildrenCount();
        if (energyDiff < 0){ return true; }
        else if (energyDiff > 0){ return false; }
        else if (ageDiff < 0){ return true; }
        else if (ageDiff > 0){ return false; }
        else if (childDiff < 0){ return true; }
        else{ return false; }
    }
    private Animal strongestAnimalInList(LinkedList<Animal> animalsOnPosition){
        Animal temporaryTheStrongest = animalsOnPosition.get(0);
        for(Animal animal: animalsOnPosition){
            boolean changeAnimal = compareAnimals(temporaryTheStrongest, animal);
            if(changeAnimal){
                temporaryTheStrongest = animal;
            }
        }
        return temporaryTheStrongest;
    }
    private void eating(){
        LinkedList<Grass> toRemoveAfterEating = new LinkedList<>();

        for (Grass food: grass.values()){
            Vector2d position = food.getPosition();
            LinkedList<Animal> animalsOnPosition = animals.get(position);

            if(animalsOnPosition != null){
                toRemoveAfterEating.add(food);
                Animal strongestAnimal = strongestAnimalInList(animalsOnPosition);
                strongestAnimal.addEnergy(grassProfit);
                strongestAnimal.addGrassEaten();
            }
        }

        for(Grass food: toRemoveAfterEating){
            grass.remove(food.getPosition());
        }
    }
    public void setEngine(SimulationEngine engineGiven){
        engine=engineGiven;
    }
    public void addDeadAnimal(Animal deadAnimal){
        deadAnimals.add(deadAnimal);
    }
   private void removeDeadAnimals(){
       for( Animal animal: deadAnimals){
           animal.removeAllObservers();
           removeAnimal(animal.getPosition(), animal);
           deadAnimalsAvgAge = ((deadAnimalsCount*deadAnimalsAvgAge)+animal.getAge())/(deadAnimalsCount+1);
           deadAnimalsCount++;
       }
       deadAnimals.clear();
   }

    private void copulationPhase(){
        LinkedList<Animal> animalsToAdd = new LinkedList<>();

        for( LinkedList<Animal> animalList: animals.values()){
            if(animalList.size()>=2){
                LinkedList<Animal> cloneAnimalList = (LinkedList<Animal>) animalList.clone();
                Animal animal1 = strongestAnimalInList(cloneAnimalList);
                cloneAnimalList.remove(animal1);
                Animal animal2 = strongestAnimalInList(cloneAnimalList);
                if(animal2.getEnergy()>energyNeddedToCopulation && animal1.getEnergy()>energyNeddedToCopulation){
                    animalsToAdd.add( new Animal(animal1,animal2));
                    animal1.addChild();
                    animal2.addChild();
                }
            };
        }
        for (Animal animal: animalsToAdd){
            place(animal);
            engine.addAnimal(animal);
        }
    }
    public void endOfADay(){
        removeDeadAnimals();
        eating();
        copulationPhase();
        this.spawnGrass(grassDaily);
    }
    public float[] getAnimalsStats(){
        float[] stats = new float[2];
        int size = 0;
        int avgEnergy = 0;
        for(LinkedList<Animal> animals1: animals.values()){
            for(Animal animal: animals1){
                size++;
                avgEnergy+=animal.getEnergy();
            }

        }
        stats[0]=size;
        stats[1]=(float)avgEnergy/size;
        return stats;
    }
    public float getDeadAnimalsAvgAge(){
        return deadAnimalsAvgAge;
    }
    public int getGrassCount(){
        return grass.size();
    }

}
