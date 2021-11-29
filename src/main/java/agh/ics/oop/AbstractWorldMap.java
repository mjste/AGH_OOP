package agh.ics.oop;

import java.util.Map;

public class AbstractWorldMap implements  IWorldMap{
    protected Map<Vector2d, Animal> animalMap;
    protected Vector2d v0;
    protected Vector2d v1;
    protected MapVisualizer mapVisualizer;

    public String toString() {
        return mapVisualizer.draw(v0, v1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(position);
    }
}