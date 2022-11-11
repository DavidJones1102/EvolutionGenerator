package agh.ics.oop;

public class Grass {
    private Vector2d position;
    public Grass( Vector2d given_position ){
        position = given_position;
    }
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }
    public boolean isAt(Vector2d positionGiven){
        return position.equals(positionGiven);
    }
}
