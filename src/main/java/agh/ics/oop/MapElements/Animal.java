package agh.ics.oop.MapElements;

import agh.ics.oop.MapElementsValues.Genotype;
import agh.ics.oop.Maps.AbstractWorldMap;
import agh.ics.oop.MapElementsValues.MapDirection;
import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElementsValues.Vector2d;

import java.util.ArrayList;

public class Animal extends AbstractMapElement {
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation;
    private AbstractWorldMap map;
    private int energy;
    private int grassEaten=0;
    private int childrenCount=0;
    private int age=0;
    private Genotype genotype;
    public Animal(AbstractWorldMap mapGiven, Vector2d initialPosition, int initialEnergy, int genomeSize){
        orientation = MapDirection.values()[(int) (Math.random()*7+0.1)];
        position = initialPosition;
        map=mapGiven;
        energy=initialEnergy;
        genotype = new Genotype(genomeSize);
    }
    public Animal( AbstractWorldMap mapGiven){
        this(mapGiven, new Vector2d(2,2),10,10);
    }
    public Animal(Animal animal1, Animal animal2){
        orientation = MapDirection.values()[(int) (Math.random()*7+0.1)];
        position = animal1.getPosition();
        map = animal1.getMap();
        int energy1 =  animal1.getEnergy();
        int energy2 =  animal2.getEnergy();
        int sumEnergy = energy1+energy2;
        energy = (int) (energy1+energy2)/2;
        animal1.addEnergy( (int) -(energy1/sumEnergy)*energy );
        animal1.addEnergy( (int) -( energy1-(energy1/sumEnergy)*energy ) );
        genotype = new Genotype(animal1, animal2);
    }
    public String toString(){
        return orientation.toString();
    }
    public void reverseOrientation(){
      orientation = orientation.reverseOrientation();
    };
    public void move (){
        int gene = genotype.getGene();

        orientation = orientation.changeOrientation(gene);

        Vector2d v = orientation.toUnitVector();
        Vector2d newPosition = position.add( v );

        Vector2d oldPosition = position;
        position = map.positionInBounds(this, newPosition);
        if(!oldPosition.equals(position)){
            this.positionChanged(oldPosition,position, this);
        }

        age++;
        energy--;
        if(energy <= 0){
            map.addDeadAnimal(this);
        }
    }
    public void addObserver(IPositionChangeObserver observer){
         observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    public void removeAllObservers(){
        observers.clear();
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }
    public void addChild(){childrenCount++;}
    public void addGrassEaten(){grassEaten++;}
    public void addEnergy(int energyToAdd){
        energy+=energyToAdd;
    }
    public int getEnergy() {
        return energy;
    }
    public AbstractWorldMap getMap(){
        return map;
    }
    public int getAge(){
        return age;
    }
    public int getChildrenCount() {
        return childrenCount;
    }
    public Genotype getGenotype(){
        return genotype;
    }
    @Override
    public String getLabel() {
        return "Animal";
    }

    @Override
    public String getImage() {
        return "src/main/resources/up.png";
    }
}

