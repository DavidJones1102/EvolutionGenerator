package agh.ics.oop.Interfaces;

import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.MapElements.Animal;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
