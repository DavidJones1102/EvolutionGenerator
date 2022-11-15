package agh.ics.oop;

public class Grass extends AbstractMapElement{
    public Grass( Vector2d given_position ){
        position = given_position;
    }

    @Override
    public String toString() {
        return "*";
    }

}
