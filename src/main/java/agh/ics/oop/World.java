package agh.ics.oop;

public class World {
    public static void main(String args[]) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection dir = MapDirection.NORTH;
        System.out.println(dir);
        System.out.println(dir.next(dir));
        System.out.println(dir.previous(dir));
    }
}
