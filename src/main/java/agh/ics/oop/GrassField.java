package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GrassField extends AbstractWorldMap {
    public MapBoundary mapBoundary= new MapBoundary();
    public GrassField( int grassAmount){
        ArrayList<Vector2d> mylist = new ArrayList<Vector2d>();
        for(int i=0; i<=(int) Math.sqrt(grassAmount*10);i++){
            for(int j=0; j <=(int) Math.sqrt(grassAmount*10); j++){
                mylist.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(mylist,new Random());
        for ( int i=0; i<grassAmount;i++){
            Grass grassToAdd = new Grass(mylist.get(i));
            elements.put(mylist.get(i), grassToAdd);
            mapBoundary.addElement(grassToAdd);
        }
    }
    public boolean canMoveTo(Vector2d position){
        Object object = this.objectAt(position);
        return object==null || object instanceof Grass;
    }
    public boolean place(Animal animal) {
        mapBoundary.addElement(animal);
        animal.addObserver(mapBoundary);
        return super.place(animal);
    }
    @Override
    public Vector2d calcUpRight() {
        return mapBoundary.calcUpRight();
    }
    @Override
    public Vector2d calcLowerLeft() {
        return mapBoundary.calcLowerLeft();
    }
}