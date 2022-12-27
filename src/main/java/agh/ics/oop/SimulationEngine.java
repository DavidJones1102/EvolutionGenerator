package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{
    private AbstractWorldMap map;
    private Vector2d[] positiones;
    private ArrayList<Animal> animals = new ArrayList<>();
    private int nOfAnimals=0;

    public SimulationEngine(AbstractWorldMap mapGiven, Vector2d[] positionesGiven) {
        map = mapGiven;
        positiones = positionesGiven;
        placeAnimals();
    }

    private void placeAnimals( ){
        boolean flag;
        Animal animalToAdd;
        for (Vector2d animalPosition: positiones){
            animalToAdd = new Animal(map,animalPosition);
            flag = map.place(animalToAdd);
            if(flag){
                animals.add(animalToAdd);
                nOfAnimals++;
            }
        }
    }


    public void subscribeAll(IPositionChangeObserver observer){
        for(Animal animal: animals){
            animal.addObserver(observer);
        }
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

            while (true){
                currentAnimal = animals.get(currentAnimalNumber);
                currentAnimal.move();
                if(currentAnimalNumber==nOfAnimals-1){ //koniec dnia
                    map.endOfADay();
                    Thread.sleep(300);
                }
                currentAnimalNumber = (currentAnimalNumber+1) % nOfAnimals;
            }
        }
        catch (InterruptedException exception){
            return;
        }

    }
}
