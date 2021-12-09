package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    private MapBoundary mapBoundary = new MapBoundary(this);
    private final Map<Vector2d, Grass> grassMap;

    public GrassField(int n) {

        grassMap = new HashMap<>();
        animalMap = new LinkedHashMap<>();
        mapVisualizer = new MapVisualizer(this);
        int top = (int)sqrt(10*n);
//        v0 = new Vector2d(0,0);
//        v1 = new Vector2d(top, top);
        Random rand = new Random();
        while (n > 0) {
            int x = rand.nextInt(top);
            int y = rand.nextInt(top);
            Vector2d tempVector = new Vector2d(x, y);
            if (!isOccupied(tempVector)) {
                grassMap.put(tempVector, new Grass(tempVector));
                positionChanged(tempVector, tempVector);
                n -= 1;
            }
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object != null) return object;
        return grassMap.get(position);
    }

    protected Vector2d[] getBoundaries() {
        return new Vector2d[]{mapBoundary.lowerLeft(), mapBoundary.upperRight()};
    }

    @Override
    public boolean place(Animal animal) {
        super.place(animal);
        positionChanged(animal.getPosition(), animal.getPosition());
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }
}
