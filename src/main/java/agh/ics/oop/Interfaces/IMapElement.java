package agh.ics.oop.Interfaces;

import agh.ics.oop.MapElementsValues.Vector2d;

public interface IMapElement {
    boolean isAt(Vector2d position);
    Vector2d getPosition();
    String getLabel();
    String getImage();
}
