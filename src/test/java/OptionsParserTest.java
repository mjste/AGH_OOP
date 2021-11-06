import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
    @Test
    public void parseTest() {
        String[] dirStr = {"r", "f", "right"};
        MoveDirection[] expectedArr = {MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT};

        MoveDirection[] actualArr = new OptionsParser().parse(dirStr);
        for (int i = 0; i < dirStr.length; i++) {
            assertEquals(expectedArr[i], actualArr[i]);
        }
    }
}
