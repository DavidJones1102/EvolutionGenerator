package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    public Animal(){
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public String toString(){
        return orientation +" "+ position.toString();
    }

   public void move (MoveDirection direction){
       if( direction == MoveDirection.RIGHT )
            { orientation=orientation.next();}
       else if ( direction == MoveDirection.LEFT )
            { orientation=orientation.previous();}
       else if ( direction == MoveDirection.FORWARD && orientation==MapDirection.NORTH && position.y<4)
            { position = position.add(new Vector2d(0,1) ); }
       else if ( direction == MoveDirection.FORWARD && orientation==MapDirection.EAST && position.x<4)
            { position = position.add(new Vector2d(1,0) ); }
       else if ( direction == MoveDirection.FORWARD && orientation==MapDirection.SOUTH && position.y>0)
            { position = position.add(new Vector2d(0,-1) ); }
       else if ( direction == MoveDirection.FORWARD && orientation==MapDirection.WEST && position.x>0)
            { position = position.add(new Vector2d(-1,0) ); }

       else if ( direction == MoveDirection.BACKWARD && orientation==MapDirection.NORTH && position.y>0)
            { position = position.add(new Vector2d(0,-1) ); }
       else if ( direction == MoveDirection.BACKWARD && orientation==MapDirection.EAST && position.x>0)
            { position = position.add(new Vector2d(-1,0) ); }
       else if ( direction == MoveDirection.BACKWARD && orientation==MapDirection.SOUTH && position.y<4)
            { position = position.add(new Vector2d(0,1) ); }
       else if ( direction == MoveDirection.BACKWARD && orientation==MapDirection.WEST && position.x<4)
            { position = position.add(new Vector2d(1,0) ); }
   }

}
