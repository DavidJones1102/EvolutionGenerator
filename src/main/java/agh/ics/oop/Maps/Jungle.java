package agh.ics.oop.Maps;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElementsValues.Settings;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.Simulation.SimulationEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Jungle extends AbstractWorldMap {



    private int width;
    private int height;
    public Jungle(Settings settingsGiven, SimulationEngine engineGiven){
       settings = settingsGiven;
       width = settings.mapWidth;
       height = settings.mapHeight;
       upperRight = new Vector2d(width-1,height-1);
       engine = engineGiven;


       int equatorHeight = (int) (height*0.2);
       int equatorStartHeight =  (int) ((float) height/2-equatorHeight);
       int equatorEndHeight = (int) ((float) height/2 +equatorHeight);

       int equatorWidth = (int) (width*0.2);
       int equatorStartWidth =  (int) (( (float)width/(float)2 )-equatorWidth);
       int equatorEndWidth = (int) (( (float)width/(float)2 )+equatorWidth);

       //Przyporządkowuje każde pole do równika lub do nie równika
       Vector2d lowerLeftEquator = new Vector2d(equatorStartWidth,equatorStartHeight);
       Vector2d upperRightEquator = new Vector2d(equatorEndWidth, equatorEndHeight);
       for(int i = 0; i<height;i++){
           for(int j=0; j<width;j++){
               Vector2d toAdd = new Vector2d(j,i);
               if(toAdd.follows(lowerLeftEquator)&&toAdd.precedes(upperRightEquator)){
                   equator.add(toAdd);
               }
               else{
                   nonEquator.add(toAdd);
               }
           }
       }
       spawnGrass(settings.startingGrassAmount);
    }
    public void spawnGrass(int grassAmount){
        Collections.shuffle(equator,new Random());
        Collections.shuffle(nonEquator,new Random());

        int equatorCurrentIndex = 0;
        int nonEquatorCurrentIndex = 0;
        for ( int i=0; i < grassAmount;i++){
            if(Math.random()<=0.2){
                nonEquatorCurrentIndex=spawnGrassIn(nonEquatorCurrentIndex,nonEquator);
            }
            else{
                equatorCurrentIndex=spawnGrassIn(equatorCurrentIndex,equator);
            }

        }
    }
    //Generuje trawe na jednym z pozycji w liscie jeżeli to możliwe
    private int spawnGrassIn(int index, ArrayList<Vector2d> list){
        while (index<list.size()){
            if(grass.get(list.get(index)) == null){
                this.placeGrass(new Grass(list.get(index)));
                return index;
            }else{
                index++;
            }
        }
        return index;
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