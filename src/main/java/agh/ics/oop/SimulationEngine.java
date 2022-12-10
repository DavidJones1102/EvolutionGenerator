package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positiones;
    private ArrayList<Animal> animals = new ArrayList<>();
    private int nOfAnimals=0;
    public SimulationEngine(MoveDirection[] movesGiven, IWorldMap mapGiven, Vector2d[] positionesGiven) {
        moves = movesGiven;
        map = mapGiven;
        positiones = positionesGiven;
        placeAnimals();
    }
    public SimulationEngine(IWorldMap mapGiven, Vector2d[] positionesGiven) {
        map = mapGiven;
        positiones = positionesGiven;
        placeAnimals();
    }
    public void setDirection(MoveDirection[] movesGiven){
        moves = movesGiven;
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
    public void run() {
        try{
            int currentAnimalNumber = 0;
            Animal currentAnimal;

            for (MoveDirection currentMove: moves){
                currentAnimal = animals.get(currentAnimalNumber);
                currentAnimal.move(currentMove);
                currentAnimalNumber = (currentAnimalNumber+1) % nOfAnimals;
                Thread.sleep(300);
            }
        }
        catch (InterruptedException exception){
            return;
        }

    }
}
