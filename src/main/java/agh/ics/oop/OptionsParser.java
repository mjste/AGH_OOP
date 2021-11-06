package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        int len = args.length;
        MoveDirection[] movDir = new MoveDirection[len];

        for (int i = 0; i < len; i++) {
            switch (args[i]) {
                case "f":
                case "forward":
                    movDir[i] = MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    movDir[i] = MoveDirection.BACKWARD;
                    break;
                case "l":
                case "left":
                    movDir[i] = MoveDirection.LEFT;
                    break;
                case "r":
                case "right":
                    movDir[i] = MoveDirection.RIGHT;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return movDir;
    }
}
