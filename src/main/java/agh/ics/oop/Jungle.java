package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Jungle extends AbstractWorldMap {
    private Vector2d lowerLeft = new Vector2d(0,0);
    private Vector2d upperRight;
    private int equatorStart;
    private int equatorEnd;
    private int width;
    private int height;
    public Jungle( int widthGiven, int heightGiven, int grassAmount, int grassProfitGiven){
       grassProfit = grassProfitGiven;
       width = widthGiven;
       height = heightGiven;
       upperRight = new Vector2d(width-1,height-1);

       int equatorHeight = (int) (height*0.2);
       equatorStart =  (int) (height/2-equatorHeight/2);
       equatorEnd = (int) (height/2 +equatorHeight/2);

        ArrayList<Vector2d> equator = new ArrayList<Vector2d>();
        ArrayList<Vector2d> nonEquator = new ArrayList<Vector2d>();
        for(int i=0; i<equatorStart;i++){
            for(int j=0; j < width; j++){
                nonEquator.add(new Vector2d(i,j));
            }
        }
        for(int i=equatorEnd+1; i<height;i++){
            for(int j=0; j < width; j++){
                nonEquator.add(new Vector2d(i,j));
            }
        }
        for(int i=equatorStart; i<=equatorEnd;i++){
            for(int j=0; j < width; j++){
                equator.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(equator,new Random());
        Collections.shuffle(nonEquator,new Random());
        int grassInEquator = (int) (grassAmount*0.8);
        for ( int i=0; i < grassInEquator;i++){
            Grass grassToAdd = new Grass(equator.get(i));
            this.placeGrass(grassToAdd);
        }
        for ( int i=0; i < grassAmount-grassInEquator;i++){
            Grass grassToAdd = new Grass(nonEquator.get(i));
            this.placeGrass(grassToAdd);
        }
    }
    public boolean canMoveTo(Vector2d position){
        return  position.precedes(upperRight) && position.follows(lowerLeft) ;
    }

    @Override
    public Vector2d calcUpRight() {
        return upperRight;
    }
    @Override
    public Vector2d calcLowerLeft() {
        return lowerLeft;
    }
}