package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GrassField extends AbstractWorldMap {

    public GrassField( int grassAmount){
        ArrayList<Vector2d> mylist = new ArrayList<Vector2d>();
        for(int i=0; i<=(int) Math.sqrt(grassAmount*10);i++){
            for(int j=0; j <=(int) Math.sqrt(grassAmount*10); j++){
                mylist.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(mylist,new Random());
        for ( int i=0; i<grassAmount;i++){
            elements.put(mylist.get(i),(IMapElement) new Grass(mylist.get(i)));
        }
    }
    public boolean canMoveTo(Vector2d position){
        Object object = this.objectAt(position);
        return object==null || object instanceof Grass;
    }

    @Override
    public Vector2d calcUpRight() {
        Vector2d upperRight = (Vector2d) elements.keySet().toArray()[0];
        for (Vector2d element : elements.keySet()) {
            upperRight=element.upperRight(upperRight);
        }
        return upperRight;
    }
    @Override
    public Vector2d calcLowerLeft() {
        Vector2d lowerLeft = (Vector2d) elements.keySet().toArray()[0];
        for (Vector2d element : elements.keySet()) {
            lowerLeft=element.lowerLeft(lowerLeft);
        }
        return lowerLeft;
    }
}