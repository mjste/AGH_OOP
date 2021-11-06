package agh.ics.oop;

public class Animal {
    private MapDirection mapDir;
    private Vector2d position;
    private IWorldMap map;

//    public Animal(){
//        mapDir = MapDirection.NORTH;
//        position = new Vector2d(2, 2);
//    }

    public Animal(IWorldMap map){
        mapDir = MapDirection.NORTH;
        position = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        mapDir = MapDirection.NORTH;
        position = initialPosition;
        this.map = map;
    }

    public String toString(){
        switch (mapDir){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public Vector2d getPosition(){
        return position;
    }

    public void move(MoveDirection direction){
        Vector2d newPosition;
        switch (direction){
            case LEFT:
                mapDir = mapDir.previous(mapDir);
                break;
            case RIGHT:
                mapDir = mapDir.next(mapDir);
                break;
            case FORWARD:
                newPosition = position.add(mapDir.toUnitVector(mapDir));
                if (map.canMoveTo(newPosition)){
                    this.position = newPosition;
                }
                break;
            case BACKWARD:
                newPosition = position.add(mapDir.toUnitVector(mapDir).opposite());
                if (map.canMoveTo(newPosition)){
                    this.position = newPosition;
                }
                break;
        }
    }
}
