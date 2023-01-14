package agh.ics.oop.Maps;

import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElementsValues.Settings;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.Simulation.SimulationEngine;

import java.util.*;


public class Jungle implements IWorldMap, IPositionChangeObserver {
    private Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    private Map<Vector2d, Grass> grass = new HashMap<>();
    private LinkedList<Animal> deadAnimals = new LinkedList<>();
    private Settings settings;
    private int deadAnimalsCount = 0;
    private float deadAnimalsAvgAge = 0;
    private ArrayList<Vector2d> equator = new ArrayList<Vector2d>();
    private ArrayList<Vector2d> nonEquator = new ArrayList<Vector2d>();
    private Vector2d lowerLeft = new Vector2d(0, 0);
    protected Vector2d upperRight;
    protected SimulationEngine engine;

    private int width;
    private int height;

    public Jungle(Settings settingsGiven, SimulationEngine engineGiven) {
        settings = settingsGiven;
        width = settings.mapWidth;
        height = settings.mapHeight;
        upperRight = new Vector2d(width - 1, height - 1);
        engine = engineGiven;


        int equatorHeight = (int) (height * 0.2);
        int equatorStartHeight = (int) ((float) height / 2 - equatorHeight);
        int equatorEndHeight = (int) ((float) height / 2 + equatorHeight);

        int equatorWidth = (int) (width * 0.2);
        int equatorStartWidth = (int) (((float) width / (float) 2) - equatorWidth);
        int equatorEndWidth = (int) (((float) width / (float) 2) + equatorWidth);

        //Przyporządkowuje każde pole do równika lub do nie równika
        Vector2d lowerLeftEquator = new Vector2d(equatorStartWidth, equatorStartHeight);
        Vector2d upperRightEquator = new Vector2d(equatorEndWidth, equatorEndHeight);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Vector2d toAdd = new Vector2d(j, i);
                if (toAdd.follows(lowerLeftEquator) && toAdd.precedes(upperRightEquator)) {
                    equator.add(toAdd);
                } else {
                    nonEquator.add(toAdd);
                }
            }
        }
        spawnGrass(settings.startingGrassAmount);
    }

    public Object objectAt(Vector2d position) {
        if (animals.get(position) != null) {
            return animals.get(position).getFirst();
        }
        return grass.get(position);

    }

    public boolean place(Animal animal) {
        this.addAnimal(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;  // czy jest sens coś zwracać, jeśli to zawsze true?
    }

    public void placeGrass(Grass element) {
        grass.put(element.getPosition(), element);
    }

    private void addAnimal(Vector2d position, Animal animal) throws IllegalArgumentException {
        boolean placeAbility = this.canMoveTo(position);
        LinkedList<Animal> elementsOnPosition = animals.get(position);

        if (elementsOnPosition != null) {
            elementsOnPosition.add(animal);
        } else if (placeAbility) {
            elementsOnPosition = new LinkedList<Animal>();
            elementsOnPosition.add(animal);
            animals.put(position, elementsOnPosition);
        } else {
            throw new IllegalArgumentException("Zwierzę nie może być umiejscowione na tej pozycji");
        }
    }

    private void removeAnimal(Vector2d position, Animal animal) throws IllegalArgumentException {
        LinkedList<Animal> elementsOnPosition = animals.get(position);

        if (elementsOnPosition == null) {
            throw new IllegalArgumentException("There is no animal to delete on " + position);
        } else if (elementsOnPosition.size() == 1) {
            animals.remove(position);

        } else {
            elementsOnPosition.remove(animal);
        }
    }

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        this.removeAnimal(oldPosition, animal);
        this.addAnimal(newPosition, animal);
    }

    public void endOfADay() {
        removeDeadAnimals();
        eating();
        copulationPhase();
        this.spawnGrass(settings.dailyGrassAmount);
    }

    private void eating() {
        LinkedList<Grass> toRemoveAfterEating = new LinkedList<>();

        for (Grass food : grass.values()) {
            Vector2d position = food.getPosition();
            LinkedList<Animal> animalsOnPosition = animals.get(position);

            if (animalsOnPosition != null) {
                toRemoveAfterEating.add(food);
                Animal strongestAnimal = strongestAnimalInList(animalsOnPosition);
                strongestAnimal.addEnergy(settings.energyFromGrass);
                strongestAnimal.addGrassEaten();
            }
        }

        for (Grass food : toRemoveAfterEating) {
            grass.remove(food.getPosition());
        }
    }

    private void removeDeadAnimals() {
        for (Animal animal : deadAnimals) {
            animal.removeAllObservers();
            removeAnimal(animal.getPosition(), animal);
            deadAnimalsAvgAge = ((deadAnimalsCount * deadAnimalsAvgAge) + animal.getAge()) / (deadAnimalsCount + 1);
            deadAnimalsCount++;
        }
        deadAnimals.clear();
    }

    private void copulationPhase() {
        LinkedList<Animal> animalsToAdd = new LinkedList<>();

        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList.size() >= 2) {
                LinkedList<Animal> cloneAnimalList = (LinkedList<Animal>) animalList.clone();
                Animal animal1 = strongestAnimalInList(cloneAnimalList);
                cloneAnimalList.remove(animal1);
                Animal animal2 = strongestAnimalInList(cloneAnimalList);
                if (animal2.getEnergy() > settings.energyNeededToCopulation && animal1.getEnergy() > settings.energyNeededToCopulation) {  // jeśli drugie w kolejności spełnia warunek, to pierwsze też musi
                    animalsToAdd.add(new Animal(animal1, animal2));
                    animal1.addChild();
                    animal2.addChild();
                }
            }
            ;
        }
        for (Animal animal : animalsToAdd) {
            place(animal);
            engine.addAnimal(animal);
        }
    }

    private boolean compareAnimals(Animal temporaryTheStrongest, Animal animal) {  // czemu nie kulturalny Comparator?
        int energyDiff = temporaryTheStrongest.getEnergy() - animal.getEnergy();
        int ageDiff = temporaryTheStrongest.getAge() - animal.getAge();
        int childDiff = temporaryTheStrongest.getChildrenCount() - animal.getChildrenCount();
        if (energyDiff < 0) {
            return true;
        } else if (energyDiff > 0) {
            return false;
        } else if (ageDiff < 0) {
            return true;
        } else if (ageDiff > 0) {
            return false;
        } else if (childDiff < 0) {
            return true;
        } else {
            return false;
        }
    }

    private Animal strongestAnimalInList(LinkedList<Animal> animalsOnPosition) {
        Animal temporaryTheStrongest = animalsOnPosition.get(0);
        for (Animal animal : animalsOnPosition) {
            boolean changeAnimal = compareAnimals(temporaryTheStrongest, animal);
            if (changeAnimal) {
                temporaryTheStrongest = animal;
            }
        }
        return temporaryTheStrongest;
    }

    public void setEngine(SimulationEngine engineGiven) {
        engine = engineGiven;
    }

    public void addDeadAnimal(Animal deadAnimal) {
        deadAnimals.add(deadAnimal);
    }

    public float[] getAnimalsStats() {
        float[] stats = new float[2];
        int size = 0;
        int avgEnergy = 0;
        for (LinkedList<Animal> animals1 : animals.values()) {
            for (Animal animal : animals1) {
                size++;
                avgEnergy += animal.getEnergy();
            }
        }
        stats[0] = size;
        stats[1] = (float) avgEnergy / size;
        return stats;
    }

    public float getDeadAnimalsAvgAge() {
        return deadAnimalsAvgAge;
    }

    public int getGrassCount() {
        return grass.size();
    }

    public Vector2d calcUpRight() {
        return upperRight;
    }

    public Vector2d calcLowerLeft() {
        return lowerLeft;
    }

    public void spawnGrass(int grassAmount) {
        Collections.shuffle(equator, new Random());
        Collections.shuffle(nonEquator, new Random());

        int equatorCurrentIndex = 0;
        int nonEquatorCurrentIndex = 0;
        for (int i = 0; i < grassAmount; i++) {
            if (Math.random() <= 0.2) {
                nonEquatorCurrentIndex = spawnGrassIn(nonEquatorCurrentIndex, nonEquator);
            } else {
                equatorCurrentIndex = spawnGrassIn(equatorCurrentIndex, equator);
            }

        }
    }

    //Generuje trawe na jednym z pozycji w liscie jeżeli to możliwe
    private int spawnGrassIn(int index, ArrayList<Vector2d> list) {
        while (index < list.size()) {
            if (grass.get(list.get(index)) == null) {
                this.placeGrass(new Grass(list.get(index)));
                return index;
            } else {
                index++;
            }
        }
        return index;
    }

    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft);
    }

    public Vector2d positionInBoundsEarth(Animal animal, Vector2d newPosition) {
        if (newPosition.y >= height || newPosition.y < 0) {
            animal.reverseOrientation();
            return animal.getPosition();
        } else if (newPosition.x >= width) {
            return new Vector2d(0, newPosition.y);
        } else if (newPosition.x < 0) {
            return new Vector2d(width - 1, newPosition.y);
        } else {
            return newPosition;
        }
    }

    public Vector2d positionInBoundsHell(Animal animal, Vector2d newPosition) {
        if (newPosition.y >= height || newPosition.y < 0 || newPosition.x < 0 || newPosition.x >= width) {
            animal.addEnergy(settings.energyTakenDuringCopulation);
            int randomX = (int) (Math.random() * settings.mapWidth);
            int randomY = (int) (Math.random() * settings.mapHeight);
            Vector2d animalPosition = new Vector2d(randomX, randomY);
            return animalPosition;
        } else {
            return newPosition;
        }
    }
}