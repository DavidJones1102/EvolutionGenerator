package agh.ics.oop;

public class Grass {
    public Vector2d position; //Public???
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
}
