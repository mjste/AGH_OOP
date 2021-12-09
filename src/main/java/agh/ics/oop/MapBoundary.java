package agh.ics.oop;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private final IWorldMap map;
    public SortedSet<Vector2d> xSorted = new TreeSet<>(new XComparator());
    public SortedSet<Vector2d> ySorted = new TreeSet<>(new YComparator());

    public MapBoundary(IWorldMap map) {
        this.map = map;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (!map.isOccupied(oldPosition)) {
            xSorted.remove(oldPosition);
            ySorted.remove(oldPosition);
        }
        xSorted.add(newPosition);
        ySorted.add(newPosition);
    }

    public Vector2d lowerLeft() {
        return new Vector2d(xSorted.first().x, ySorted.first().y);
    }

    public Vector2d upperRight() {
        return new Vector2d(xSorted.last().x, ySorted.last().y);
    }
}
