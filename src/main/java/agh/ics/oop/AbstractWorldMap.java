package agh.ics.oop;

import java.util.List;

public class AbstractWorldMap implements  IWorldMap{
    protected List<Animal> animalList;
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
            animalList.add(animal);
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
        for (Animal animal : animalList)
        {
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }
}

//skończyłem pisać testy na grassfield i rectangularfield
// teraz zabieram sie do implementacji AbstractWorldMap