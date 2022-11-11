package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements IWorldMap {
    private MapVisualizer drawer = new MapVisualizer(this);
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Grass> grass = new ArrayList<>();
    private Vector2d lowerLeft = new Vector2d(0,0);
    private Vector2d upperRight = new Vector2d(0,0);
    public GrassField( int grassAmount){
        int x;
        int y;
        Vector2d v;
        for(int i=0; i<grassAmount;i++){
            y= ThreadLocalRandom.current().nextInt(0,  (int) Math.sqrt(grassAmount*10)+1);
            x= ThreadLocalRandom.current().nextInt(0,  (int) Math.sqrt(grassAmount*10)+1);
            v=new Vector2d(x,y);
            if( !grassAt(v)){
                grass.add(new Grass(v));
            }
            else {i--;}
        }
        //(, sqrt(n*10))
    }
    public boolean canMoveTo(Vector2d position){
        Object object = this.objectAt(position);
        return object==null || object instanceof Grass;
    }
    public boolean place(Animal animal) {
        boolean placeAbility = this.canMoveTo(animal.getPosition());
        if (placeAbility) {
            animals.add(animal);
        }
        return placeAbility;
    }
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        for (Grass grass1 : grass) {
            if (grass1.isAt(position)) {
                return grass1;
            }
        }
        return null;
    }
    public boolean grassAt(Vector2d position) {
        Object object = objectAt(position);
        return object!=null && object instanceof Grass;
    }
    public String toString(){
        for (Animal animal : animals) {
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
            upperRight=animal.getPosition().upperRight(upperRight);
        }
        return drawer.draw(lowerLeft, upperRight);
    }
}
