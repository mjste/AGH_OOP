package agh.ics.oop;
import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height){
        this.v0 = new Vector2d(0,0);
        this.v1 = new Vector2d(width-1, height-1);
        animalList = new ArrayList<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(v0) && position.precedes(v1) && super.canMoveTo(position);
    }
}
