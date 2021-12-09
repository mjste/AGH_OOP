package agh.ics.oop;

import java.util.Map;
import java.util.Vector;

public abstract class AbstractWorldMap implements  IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, Animal> animalMap;
//    protected Vector2d v0;
//    protected Vector2d v1;
    protected MapVisualizer mapVisualizer;

    public String toString() {
        Vector2d[] boundaries = getBoundaries();

        return mapVisualizer.draw(boundaries[0], boundaries[1]);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            return true;
        } else {
            throw new IllegalArgumentException(animal.getPosition().toString() + " is not valid position to place");
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal ani = animalMap.get(oldPosition);
        animalMap.remove(oldPosition);
        animalMap.put(newPosition, ani);
    }

    //public abstract Vector2d[] getBoundaries();
}