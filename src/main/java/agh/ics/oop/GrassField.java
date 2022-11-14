package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GrassField extends AbstractWorldMap {

    private ArrayList<Grass> grass = new ArrayList<>();
    public GrassField( int grassAmount){
        ArrayList<Vector2d> mylist = new ArrayList<Vector2d>();
        for(int i=0; i<=(int) Math.sqrt(grassAmount*10);i++){
            for(int j=0; j <=(int) Math.sqrt(grassAmount*10); j++){
                mylist.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(mylist,new Random());
        for ( int i=0; i<grassAmount;i++){
            grass.add(new Grass(mylist.get(i)));
        }
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

    @Override
    public Vector2d calcUpRight() {
        Vector2d upperRight = animals.get(0).getPosition();
        for (Animal animal : animals) {
            upperRight=animal.getPosition().upperRight(upperRight);
        }
        for (Grass grass1 : grass) {
            upperRight=grass1.getPosition().upperRight(upperRight);
        }
        return upperRight;
    }

    @Override
    public Vector2d calcLowerLeft() {
        Vector2d lowerLeft = animals.get(0).getPosition();
        for (Animal animal : animals) {
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
        }
        for (Grass grass1 : grass) {
            lowerLeft=grass1.getPosition().lowerLeft(lowerLeft);
        }
        return lowerLeft;
    }
}