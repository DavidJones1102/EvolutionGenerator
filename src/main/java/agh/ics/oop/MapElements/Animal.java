package agh.ics.oop.MapElements;

import agh.ics.oop.MapElementsValues.Genotype;
import agh.ics.oop.MapElementsValues.Settings;
import agh.ics.oop.MapElementsValues.MapDirection;
import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.Maps.Jungle;
import agh.ics.oop.Simulation.SimulationEngine;

import java.util.ArrayList;

public class Animal extends AbstractMapElement {
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation;
    private Jungle map;
    private int energy;
    private int grassEaten=0;
    private int childrenCount=0;
    private int age=0;
    private Genotype genotype;
    private Settings settings;
    private boolean highlight = false;
    public Animal(Jungle mapGiven, Vector2d initialPosition, Settings settingsGiven){
        orientation = MapDirection.values()[(int) (Math.random()*7+0.1)];
        position = initialPosition;
        map=mapGiven;
        energy=settingsGiven.startingEnergy;
        genotype = new Genotype(settingsGiven.genomeSize);
        settings = settingsGiven;
    }

    public Animal(Animal animal1, Animal animal2){
        settings = animal1.getSettings();
        orientation = MapDirection.values()[(int) (Math.random()*7+0.1)];
        position = animal1.getPosition();
        map = animal1.getMap();
        energy = 2*settings.energyTakenDuringCopulation;

        animal1.addEnergy( -settings.energyTakenDuringCopulation);
        animal2.addEnergy( -settings.energyTakenDuringCopulation );
        genotype = new Genotype(animal1, animal2);
    }
    public String toString(){
        return orientation.toString();
    }
    public void reverseOrientation(){
      orientation = orientation.reverseOrientation();
    };
    public void move (){
        int gene = genotype.getGene(settings.behaviorVariant);

        orientation = orientation.changeOrientation(gene);

        Vector2d v = orientation.toUnitVector();
        Vector2d newPosition = position.add( v );

        Vector2d oldPosition = position;
        if(settings.mapVariant){
            position = map.positionInBoundsHell(this, newPosition);
        }
        else {
            position = map.positionInBoundsEarth(this, newPosition);
        }

        if(!oldPosition.equals(position)){
            this.positionChanged(oldPosition,position);
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
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
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
    public Settings getSettings() {
        return settings;
    }
    public Jungle getMap(){
        return map;
    }
    public int getAge(){
        return age;
    }
    public int getChildrenCount() {
        return childrenCount;
    }
    public int getGrassEaten() {
        return grassEaten;
    }
    public Genotype getGenotype(){
        return genotype;
    }
    @Override
    public String getLabel() {
        if(highlight){
            return "!!!!!";
        }
        else if(energy < settings.startingEnergy/2){
            return "Low";
        }
        else if(energy > settings.startingEnergy*(3/2)){
            return "High";
        }
        else {
            return "Neutral";
        }

    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    @Override
    public String getImage() {
        return "src/main/resources/animal.png";
    }
}

