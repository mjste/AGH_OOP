package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private int width;
    private int height;
    private List<Animal> animals;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<Animal>();
    }

    @Override
    public String toString(){
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0), new Vector2d(height-1, width-1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.follows(new Vector2d(this.height-1, width-1)) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        // dodać sprawdzanie czy w ogóle można go tam umieścić
        if (true){
            this.animals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
