package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString(MapDirection dir){
        switch (dir) {
            case EAST:
                return "Wschód";
            case WEST:
                return "Zachód";
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            default:
                throw new IllegalArgumentException();
        }
    }
    // stanęło na  poleceniu 7

}
