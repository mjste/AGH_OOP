import agh.ics.oop.*;
import agh.ics.oop.enums.MoveDirection;
import org.junit.jupiter.api.Test;

public class SimulationEngineTest {

    @Test
    public void testRun(){
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
}
