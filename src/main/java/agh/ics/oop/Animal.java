package agh.ics.oop;

import java.io.IOError;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Animal {
    private MapDirection mapDir;
    private Vector2d position;
    private IWorldMap map;
    private List<IPositionChangeObserver> observerList;

    public Animal(IWorldMap map) {
        this.map = map;
        mapDir = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.observerList = new LinkedList<>();
        observerList.add((IPositionChangeObserver) map);
        mapDir = MapDirection.NORTH;
        position = initialPosition;
    }

    public String toString() {
        switch (mapDir) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            default:
                throw new IllegalStateException("Unexpected value: " + mapDir);
        }
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition;
        switch (direction) {
            case LEFT:
                mapDir = mapDir.previous(mapDir);
                break;
            case BACKWARD:
                newPosition = position.add(mapDir.toUnitVector(mapDir).opposite());
                if (map.canMoveTo(newPosition)) {
                    positionChanged(position, newPosition);
                    this.position = newPosition;
                }
                break;
            case RIGHT:
                mapDir = mapDir.next(mapDir);
                break;
            case FORWARD:
                newPosition = position.add(mapDir.toUnitVector(mapDir));
                if (map.canMoveTo(newPosition)) {
                    positionChanged(position, newPosition);
                    this.position = newPosition;
                }
                break;
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observerList.remove(observer);
    }

    private void positionChanged(Vector2d oldPos, Vector2d newPos) {
        for(IPositionChangeObserver obs : observerList) {
            obs.positionChanged(oldPos, newPos);
        }
    }
}
