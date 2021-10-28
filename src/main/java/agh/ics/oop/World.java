package agh.ics.oop;

public class World {
    public static void main(String args[]) {
        Animal ani = new Animal();
        System.out.println(ani.toString());


        MoveDirection[] mDirs = OptionParser.parse(new String[]{"r", "f", "f", "f"});

        for (MoveDirection dir : mDirs) {
            ani.move(dir);
        }

        System.out.println(ani.toString());
    }
}
