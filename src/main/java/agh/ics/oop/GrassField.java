package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField implements IWorldMap{
    List<Grass> grassList;
    List<Animal> animalList;
    Vector2d v0;
    Vector2d v1;
    MapVisualizer mapVisualizer;

    public GrassField(int n) {
        grassList = new ArrayList<>();
        animalList = new ArrayList<>();
        mapVisualizer = new MapVisualizer(this);
        int top = (int)sqrt(10*n);
        v0 = new Vector2d(0,0);
        v1 = new Vector2d(top, top);
        Random rand = new Random();
        while (n > 0) {
            int x = rand.nextInt(top);
            int y = rand.nextInt(top);
            Vector2d tempVector = new Vector2d(x, y);
            if (!isOccupied(tempVector)) {
                grassList.add(new Grass(tempVector));
                n -= 1;
            }
        }
    }

    public String toString() {
        return mapVisualizer.draw(v0, v1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!(objectAt(position) instanceof Animal)) {
            Vector2d ll = position.lowerLeft(v0);
            Vector2d ur = position.upperRight(v1);
            if (ll.precedes(v0)) v0 = ll;
            if (ur.follows(v1)) v1 = ur;
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (canMoveTo(pos)) {
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
        for (Animal animal: animalList)
            if (animal.getPosition().equals(position))
                return animal;
        for (Grass grass : grassList)
            if (grass.getPosition().equals(position))
                return grass;
        return null;
    }
}
