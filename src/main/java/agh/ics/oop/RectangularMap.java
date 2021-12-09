package agh.ics.oop;
import java.util.LinkedHashMap;

public class RectangularMap extends AbstractWorldMap{
    private final Vector2d v0;
    private final Vector2d v1;

    public RectangularMap(int width, int height){
        this.v0 = new Vector2d(0,0);
        this.v1 = new Vector2d(width-1, height-1);
        animalMap = new LinkedHashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(v0) && position.precedes(v1) && super.canMoveTo(position);
    }

    public Vector2d[] getBoundaries() {
        return new Vector2d[]{v0,v1};
    }
}
