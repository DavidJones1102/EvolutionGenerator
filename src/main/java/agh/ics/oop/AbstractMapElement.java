package agh.ics.oop;

abstract public class AbstractMapElement {
    protected Vector2d position;
    public Vector2d getPosition(){
        return position;
    }
    public boolean isAt(Vector2d positionGiven){
        return position.equals(positionGiven);
    }
}