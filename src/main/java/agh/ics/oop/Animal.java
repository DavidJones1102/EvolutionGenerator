package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractMapElement{
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation;
    private IWorldMap map;
    public Animal(IWorldMap mapGiven, Vector2d initialPosition){
        orientation = MapDirection.NORTH;
        position = initialPosition;
        map=mapGiven;
    }
    public Animal( IWorldMap mapGiven){
        this(mapGiven, new Vector2d(2,2));
    }

    public String toString(){
        if (orientation==MapDirection.NORTH){
            return "N";
        }
        else if (orientation==MapDirection.EAST){
            return "E";
        }
        else if (orientation==MapDirection.WEST){
            return "W";
        }
        else if (orientation==MapDirection.SOUTH){
            return "S";
        }
        return "";
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
               this.positionChanged(this.getPosition(),new_position);
               position = new_position;
           }
       }
       else if ( direction == MoveDirection.BACKWARD )
       {
           Vector2d new_position = position.subtract( v );
           if( map.canMoveTo(new_position)){
               this.positionChanged(this.getPosition(),new_position);
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
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String getLabel() {
        return "Animal";
    }

    @Override
    public String getImage() {
        if (orientation==MapDirection.NORTH){
            return "src/main/resources/up.png";
        }
        else if (orientation==MapDirection.EAST){
            return "src/main/resources/right.png";
        }
        else if (orientation==MapDirection.WEST){
            return "src/main/resources/down.png";
        }
        else if (orientation==MapDirection.SOUTH){
            return "src/main/resources/left.png";
        }
        return "";
    }
}

