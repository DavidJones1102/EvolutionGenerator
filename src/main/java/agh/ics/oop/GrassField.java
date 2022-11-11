package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private MapVisualizer drawer = new MapVisualizer(this);
    private ArrayList<Grass> grass = new ArrayList<>();
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
        return object instanceof Grass;
    }

    @Override
    public Vector2d calcUpRight() {
        Vector2d upperRight = animals.get(0).getPosition();
        for (Animal animal : animals) {
            upperRight=animal.getPosition().upperRight(upperRight);
        }
        return upperRight;
    }

    @Override
    public Vector2d calcLowerLeft() {
        Vector2d lowerLeft = animals.get(0).getPosition();
        for (Animal animal : animals) {
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
        }
        return lowerLeft;
    }
}