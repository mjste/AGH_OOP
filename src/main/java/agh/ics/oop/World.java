package agh.ics.oop;

public class World {
    public static void main(String args[])
    {
        System.out.println("System wystartowal");
        String[] a = {"f", "f", "r", "l"};
        Direction[] list = new Direction[a.length];
        for (int i = 0; i < a.length; i++)
        {
            switch (a[i]) {
                case "f" -> list[i] = Direction.f;
                case "b" -> list[i] = Direction.b;
                case "r" -> list[i] = Direction.r;
                case "l" -> list[i] = Direction.l;
            }
        }
        run(list);
        System.out.println("System zakończył pracę");
    }

    static void run(Direction lines[])
    {
        System.out.println("Start");
        for (int i = 0; i < lines.length-1; i++) {
            switch (lines[i]) {
                case f -> System.out.println("Zwierzak idzie do przodu");
                case b -> System.out.println("Zwierzak idzie do tyłu");
                case r -> System.out.println("Zwierzak skręca w prawo");
                case l -> System.out.println("Zwierzak skręca w lewo");
            }
        }
        System.out.println("Stop");
    }
}
