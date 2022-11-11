package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{
    private Vector2d upRight;
    private Vector2d lowerLeft;
    public RectangularMap(int width, int height) {
        upRight = new Vector2d(width-1, height-1);
        lowerLeft = new Vector2d(0, 0);
    }
    public boolean canMoveTo(Vector2d position) {
        return  position.precedes(upRight) && position.follows(lowerLeft) && !this.isOccupied(position) ;
    }
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    //public String toString(){
    //    return drawer.draw(lowerLeft, upRight);
    //}

    @Override
    public Vector2d calcLowerLeft() { return lowerLeft; }

    @Override
    public Vector2d calcUpRight() { return upRight; }
}
