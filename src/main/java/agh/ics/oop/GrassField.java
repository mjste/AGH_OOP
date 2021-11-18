package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class GrassField implements IWorldMap{
    private List<Grass> grassList;
    private List<Animal> animalList;
    private Vector2d v0;
    private Vector2d v1;


    public GrassField(int n){
        v0 = new Vector2d(0, 0);
        v1 = new Vector2d(10, 10);
        Vector2d v2 = new Vector2d((int)round(sqrt(n*10)), (int)round(sqrt(n*10)));
        animalList = new ArrayList<>();
        grassList = new ArrayList<>();

        while (n > 0) {
            // losuj współrzędne
            int x = (int) (Math.random()*round(sqrt(n*10)));
            int y = (int) (Math.random()*round(sqrt(n*10)));
            Vector2d vec = new Vector2d(x, y);
            if (!isOccupied(vec)){
                grassList.add(new Grass(vec));
                n-= 1;
            }
        }
    }

    @Override
    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(v0, v1);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (objectAt(position) instanceof Animal){
            if (position.precedes(v0)){
                v0 = position;
            }
            if (position.follows(v1)) {
                v1 = position;
            }

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalList.add(animal);
            if (animal.getPosition().follows(v1)){
                v1 = animal.getPosition();
            }
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
        for (Grass grass : grassList)
        {
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }
}