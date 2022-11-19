package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positiones;
    private ArrayList<Animal> animals = new ArrayList<>();
    public SimulationEngine(MoveDirection[] movesGiven, IWorldMap mapGiven, Vector2d[] positionesGiven) {
        moves = movesGiven;
        map = mapGiven;
        positiones = positionesGiven;
    }

    public void run() {
        int nOfAnimals=0;
        int currentAnimalNumber = 0;

        Animal currentAnimal;
        Animal animalToAdd;
        boolean flag;
        for (Vector2d animalPosition: positiones){
            animalToAdd = new Animal(map,animalPosition);
            flag = map.place(animalToAdd);
            if(flag){
                animals.add(animalToAdd);
                nOfAnimals++;
            }
        }

        for (MoveDirection currentMove: moves){
            currentAnimal = animals.get(currentAnimalNumber);
            currentAnimal.move(currentMove);
            currentAnimalNumber = (currentAnimalNumber+1) % nOfAnimals;
        }
    }
}
