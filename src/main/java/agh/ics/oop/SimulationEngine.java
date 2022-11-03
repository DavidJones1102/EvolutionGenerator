package agh.ics.oop;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positiones;
    public SimulationEngine(MoveDirection[] movesGiven, IWorldMap mapGiven, Vector2d[] positionesGiven) {
        moves = movesGiven;
        map = mapGiven;
        positiones = positionesGiven;
    }

    public void run() {
        int nOfAnimals = positiones.length;
        int currentAnimalNumber = 0;
        Vector2d currentPosition;
        Animal currentAnimal;
        for (Vector2d animalPosition: positiones){
            map.place(new Animal(map,animalPosition));
        }
        for (MoveDirection currentMove: moves){
            currentPosition = positiones[currentAnimalNumber];
            if( map.isOccupied(currentPosition)){
                currentAnimal = (Animal) map.objectAt(currentPosition);
                currentAnimal.move(currentMove);
                map.place(currentAnimal);
                positiones[currentAnimalNumber] = currentAnimal.getPosition();
            }
            currentAnimalNumber = (currentAnimalNumber+1) % nOfAnimals;

        }
    }
}
