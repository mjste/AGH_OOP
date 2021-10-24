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

    public MapDirection next(MapDirection dir){
        switch (dir){
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            default:
                throw new IllegalArgumentException();
        }
    }

    public MapDirection previous(MapDirection dir){
        switch (dir) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case EAST:
                return NORTH;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Vector2d toUnitVector(MapDirection dir){
        switch (dir) {
            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case EAST:
                return new Vector2d(1, 0);
            default:
                throw new IllegalArgumentException();
        }
    }

    // stanęło na  poleceniu 7

}
