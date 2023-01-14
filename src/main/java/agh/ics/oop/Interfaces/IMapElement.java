package agh.ics.oop.Interfaces;  // to że coś jest interfejsem nie kwalifikuje tego do wspólnego pakietu

import agh.ics.oop.MapElementsValues.Vector2d;

public interface IMapElement {
    boolean isAt(Vector2d position);

    Vector2d getPosition();

    String getLabel();

    String getImage();
}
