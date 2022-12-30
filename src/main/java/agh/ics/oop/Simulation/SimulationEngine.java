package agh.ics.oop.Simulation;

import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.Maps.AbstractWorldMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationEngine implements IEngine, Runnable{
    private AbstractWorldMap map;
    private Vector2d[] positiones;
    private ArrayList<Animal> animals = new ArrayList<>();
    private int nOfAnimals=0;
    LinkedList<IPositionChangeObserver> observers = new LinkedList<>();
    private int initialEnergy=10;

    public SimulationEngine(AbstractWorldMap mapGiven, Vector2d[] positionesGiven) {
        map = mapGiven;
        positiones = positionesGiven;
        placeAnimals();
    }

    private void placeAnimals( ){
        boolean flag;
        Animal animalToAdd;
        for (Vector2d animalPosition: positiones){
            animalToAdd = new Animal(map,animalPosition, initialEnergy);
            flag = map.place(animalToAdd);
            if(flag){
                animals.add(animalToAdd);
                nOfAnimals++;
            }
        }
    }
    public void addAnimal(Animal animal){
        animals.add(animal);
        nOfAnimals++;
        for(IPositionChangeObserver observer: observers){
            animal.addObserver(observer);
        }
    }

    public void subscribeAll(IPositionChangeObserver observer){
        for(Animal animal: animals){
            animal.addObserver(observer);
        }
        observers.add(observer);
    }
    public void unSubscribeAll(IPositionChangeObserver observer){
        for(Animal animal: animals){
            animal.removeObserver(observer);
        }
    }
    public void run() {
        try{
            int currentAnimalNumber = 0;
            Animal currentAnimal;

            while (nOfAnimals>0){
                currentAnimal = animals.get(currentAnimalNumber);
                currentAnimal.move();
                if(currentAnimalNumber==nOfAnimals-1){ //koniec dnia
                    map.endOfADay();
                    Thread.sleep(2000);
                }
                if(currentAnimal.getEnergy()<=0){
                    animals.remove(currentAnimal);
                    nOfAnimals--;
                    currentAnimalNumber--;
                    if(nOfAnimals <= 0){
                        break;
                    }
                }

                currentAnimalNumber = (currentAnimalNumber+1) % nOfAnimals;
            }
        }
        catch (InterruptedException exception){
            return;
        }

    }
}
