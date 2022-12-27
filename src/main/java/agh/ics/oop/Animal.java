package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractMapElement{
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation;
    private IWorldMap map;
    private int energy;
    private int grassEaten=0;
    private int childrenCount=0;
    private int age=0;
    private Genotype genotype = new Genotype(45);
    public Animal(IWorldMap mapGiven, Vector2d initialPosition){
        orientation = MapDirection.values()[(int) (Math.random()*7+0.1)];
        position = initialPosition;
        map=mapGiven;
    }
    public Animal( IWorldMap mapGiven){
        this(mapGiven, new Vector2d(2,2));
    }

    public String toString(){
        return orientation.toString();
    }

    public void move (){
        int gene = genotype.getGene();
        for( int i = 0; i<gene; i++){
            orientation=orientation.next();
        }

        Vector2d v = orientation.toUnitVector();
        Vector2d newPosition = position.add( v );
        if( map.canMoveTo(newPosition)){
            Vector2d oldPosition = position;
            position = newPosition;
            this.positionChanged(oldPosition,newPosition, this);
        }
        age++;
    }
   public void move (MoveDirection direction){
       Vector2d v = orientation.toUnitVector();

       if( direction == MoveDirection.RIGHT )
       { orientation=orientation.next();}
       else if ( direction == MoveDirection.LEFT )
       { orientation=orientation.previous();}
       else if ( direction == MoveDirection.FORWARD )
       {
           Vector2d new_position = position.add( v );
           if( map.canMoveTo(new_position)){
               this.positionChanged(this.getPosition(),new_position, this);
               position = new_position;
           }
       }
       else if ( direction == MoveDirection.BACKWARD )
       {
           Vector2d new_position = position.subtract( v );
           if( map.canMoveTo(new_position)){
               this.positionChanged(this.getPosition(),new_position,this);
               position = new_position;
           }
       }
    }
    public void addObserver(IPositionChangeObserver observer){
         observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }
    public void addEnergy(int energyToAdd){
        energy+=energyToAdd;
    }
    public int getEnergy() {
        return energy;
    }
    public int getAge(){
        return age;
    }
    public int getChildrenCount() {
        return childrenCount;
    }

    @Override
    public String getLabel() {
        return "Animal";
    }

    @Override
    public String getImage() {
        return "src/main/resources/up.png";
    }
}

