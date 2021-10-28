package agh.ics.oop;

public class Animal {
    private MapDirection mapDir;
    private Vector2d position;

    public Animal(){
        mapDir = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public String toString(){
        return position.toString()+" "+mapDir.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
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
                if (newPosition.follows(new Vector2d(0,0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    this.position = newPosition;
                }
                break;
            case BACKWARD:
                newPosition = position.add(mapDir.toUnitVector(mapDir).opposite());
                if (newPosition.follows(new Vector2d(0,0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    this.position = newPosition;
                }
                break;
        }
    }
}
