import agh.ics.oop.Animal;
import agh.ics.oop.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    // Testy do starej wersji klasy animal
//    @Test
//    public void orientationTest(){
//        Animal ani = new Animal();
//        ani.move(MoveDirection.LEFT);
//        assertEquals("(2,2) WEST", ani.toString());
//    }
//
//    @Test
//    public void moveTest() {
//        Animal ani = new Animal();
//        ani.move(MoveDirection.LEFT);
//        ani.move(MoveDirection.LEFT);
//        assertEquals("(2,2) SOUTH", ani.toString());
//
//        ani.move(MoveDirection.FORWARD);
//        ani.move(MoveDirection.LEFT);
//        assertEquals("(2,1) EAST", ani.toString());
//    }
//
//    @Test
//    public void moveBorderTest() {
//        Animal ani = new Animal();
//        ani.move(MoveDirection.FORWARD);
//        ani.move(MoveDirection.FORWARD);
//        assertEquals("(2,4) NORTH", ani.toString());
//
//        ani.move(MoveDirection.FORWARD);
//        ani.move(MoveDirection.LEFT);
//        assertEquals("(2,4) WEST", ani.toString());
//    }
}
