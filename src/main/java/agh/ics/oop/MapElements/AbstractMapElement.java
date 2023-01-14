package agh.ics.oop.MapElements;  // pakiety zwyczajowo camelCasem

import agh.ics.oop.Interfaces.IMapElement;
import agh.ics.oop.MapElementsValues.Vector2d;

abstract public class AbstractMapElement implements IMapElement {  // skoro jest klasa abstrakcyjna, to czy interfejs coś wnosi?
    protected Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    abstract public String getLabel();

    abstract public String getImage();

    public boolean isAt(Vector2d positionGiven) {
        return position.equals(positionGiven);
    }
}
