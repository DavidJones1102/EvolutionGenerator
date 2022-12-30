package agh.ics.oop.MapElements;

import agh.ics.oop.Interfaces.IMapElement;
import agh.ics.oop.MapElementsValues.Vector2d;

abstract public class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    public Vector2d getPosition(){
        return position;
    }
    abstract public String getLabel();
    abstract public String getImage();
    public boolean isAt(Vector2d positionGiven){
        return position.equals(positionGiven);
    }
}
