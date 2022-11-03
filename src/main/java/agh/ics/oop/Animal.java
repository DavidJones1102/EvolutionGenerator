package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    private IWorldMap map;
    public Animal(){
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }
    public Animal( IWorldMap mapGiven){
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
        map=mapGiven;
    }
    public Animal(IWorldMap mapGiven, Vector2d initialPosition){
        orientation = MapDirection.NORTH;
        position = initialPosition;
        map=mapGiven;
    }

    public Vector2d getPosition() {
        return position;
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
    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }
   public void move (MoveDirection direction){
       Vector2d up_right = new Vector2d(4,4) ;
       Vector2d down_left = new Vector2d(0,0) ;
       Vector2d v = orientation.toUnitVector();

       if( direction == MoveDirection.RIGHT )
       { orientation=orientation.next();}
       else if ( direction == MoveDirection.LEFT )
       { orientation=orientation.previous();}
       else if ( direction == MoveDirection.FORWARD )
       {
           Vector2d new_position = position.add( v );
           if( map.canMoveTo(new_position)){
               position = new_position;
           }
       }
       else if ( direction == MoveDirection.BACKWARD )
       {
           Vector2d new_position = position.subtract( v );
           if( map.canMoveTo(new_position)){
               position = new_position;
           }
       }

    }
}

