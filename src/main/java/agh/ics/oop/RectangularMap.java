package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private List<Animal> animalList;
    private Vector2d v0;
    private Vector2d v1;

    public RectangularMap(int width, int height){
        this.v0 = new Vector2d(0,0);
        this.v1 = new Vector2d(width-1, height-1);
        animalList = new ArrayList<>();
    }

    @Override
    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(v0, v1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(v0) && position.precedes(v1) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null ;
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
