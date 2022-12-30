package agh.ics.oop.MapElements;

import agh.ics.oop.MapElementsValues.Vector2d;

public class Grass extends AbstractMapElement {
    public Grass( Vector2d given_position ){
        position = given_position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getLabel() {
        return "Grass";
    }

    @Override
    public String getImage() {
        return "src/main/resources/grass.png";
    }
}
