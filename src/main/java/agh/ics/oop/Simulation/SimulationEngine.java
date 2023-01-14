package agh.ics.oop.Simulation;

import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElementsValues.Settings;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.Maps.Jungle;


import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationEngine implements Runnable {
    private Jungle map;
    private ArrayList<Animal> animals = new ArrayList<>();
    private int nOfAnimals = 0;
    LinkedList<IPositionChangeObserver> observers = new LinkedList<>();  // modyfikator dostępu
    private Settings settings;

    public SimulationEngine(Jungle mapGiven, Settings settingsGiven) {
        map = mapGiven;

        settings = settingsGiven;
        placeAnimals();
        map.setEngine(this);
    }

    private void placeAnimals() {
        boolean flag;  // mało wymowna nazwa
        for (int i = 0; i < settings.startingAnimalsNumber; i++) {
            int randomX = (int) (Math.random() * settings.mapWidth);
            int randomY = (int) (Math.random() * settings.mapHeight);
            Vector2d animalPosition = new Vector2d(randomX, randomY);
            Animal animalToAdd = new Animal(map, animalPosition, settings);
            if (map.place(animalToAdd)) {
                animals.add(animalToAdd);
                nOfAnimals++;
            }
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        nOfAnimals++;
        for (IPositionChangeObserver observer : observers) {
            animal.addObserver(observer);
        }
    }

    public void subscribeAll(IPositionChangeObserver observer) {
        for (Animal animal : animals) {
            animal.addObserver(observer);
        }
        observers.add(observer);
    }

    public void run() {
        try {
            int currentAnimalNumber = 0;
            Animal currentAnimal;

            while (nOfAnimals > 0) {
                currentAnimal = animals.get(currentAnimalNumber);
                currentAnimal.move();
                if (currentAnimalNumber == nOfAnimals - 1) { //koniec dnia  // nieczytelne; lepiej najpierw iterować po wszystkich zwierzętach, a później robić koniec dnia
                    map.endOfADay();
                    Thread.sleep(2000);
                }
                if (currentAnimal.getEnergy() <= 0) {
                    animals.remove(currentAnimal);
                    nOfAnimals--;
                    currentAnimalNumber--;
                    if (nOfAnimals <= 0) {
                        break;
                    }
                }
                currentAnimalNumber = (currentAnimalNumber + 1) % nOfAnimals;
            }
        } catch (InterruptedException exception) {
            return;
        }

    }
}
