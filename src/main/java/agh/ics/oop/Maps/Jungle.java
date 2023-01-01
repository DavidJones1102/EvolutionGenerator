package agh.ics.oop.Maps;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.gui.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Jungle extends AbstractWorldMap {
    private Vector2d lowerLeft = new Vector2d(0,0);
    private Vector2d upperLeft;
    private Vector2d upperRight;
    private Vector2d lowerRight;
    private int equatorStart;
    private int equatorEnd;

    private ArrayList<Vector2d> equator = new ArrayList<Vector2d>();
    private ArrayList<Vector2d> nonEquator = new ArrayList<Vector2d>();
    private int width;
    private int height;
    public Jungle(Settings settings){
       grassProfit = settings.energyFromGrass;
       grassDaily = settings.dailyGrassAmount;
       width = settings.mapWidth;
       height = settings.mapHeight;
       upperRight = new Vector2d(width-1,height-1);
       upperLeft = new Vector2d(0,height-1);
       lowerRight = new Vector2d(width-1,0);

       int equatorHeight = (int) (height*0.2);
       equatorStart =  (int) (height/2-equatorHeight/2);
       equatorEnd = (int) (height/2 +equatorHeight/2);


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
        spawnGrass(settings.startingGrassAmount);

    }
    public void spawnGrass(int grassAmount){
        Collections.shuffle(equator,new Random());
        Collections.shuffle(nonEquator,new Random());
        int grassInEquator = (int) (grassAmount*0.8);
        for ( int i=0, j=0; i < grassInEquator;i++){
            if(grass.get(equator.get(i+j))==null){
                Grass grassToAdd = new Grass(equator.get(i));
                this.placeGrass(grassToAdd);
            }
            else {
                j++;
                i--;
                if(i+j<=equator.size()){
                    break;
                }
            }

        }
        for ( int i=0,j=0; i < grassAmount-grassInEquator;i++){
            if(grass.get(nonEquator.get(i+j))==null){
                Grass grassToAdd = new Grass(nonEquator.get(i));
                this.placeGrass(grassToAdd);
            }
            else {
                j++;
                i--;
                if(i+j<=nonEquator.size()){
                    break;
                }
            }
        }
    }
    public boolean canMoveTo(Vector2d position){
        return  position.precedes(upperRight) && position.follows(lowerLeft) ;
    }
    public Vector2d positionInBounds(Animal animal, Vector2d newPosition){
        if(newPosition.y>=height || newPosition.y<0){
            animal.reverseOrientation();
            return animal.getPosition();
        }
        else if(newPosition.x>=width){
            return new Vector2d(0,newPosition.y);
        }
        else if(newPosition.x<0){
            return new Vector2d(width-1,newPosition.y);
        }
        else {
            return newPosition;
        }
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