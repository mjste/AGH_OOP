import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
    @Test
    public void parseTest() {
        OptionsParser optionsParser = new OptionsParser();

        String[] dirStr = {"r", "f", "right"};
        MoveDirection[] expectedArr = {MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT};

        MoveDirection[] actualArr = optionsParser.parse(dirStr);
        for (int i = 0; i < dirStr.length; i++) {
            assertEquals(expectedArr[i], actualArr[i]);
        }

        try {
            optionsParser.parse(new String[]{"r", "r", "k"});
        } catch (IllegalArgumentException ex) {
            assertEquals("k is not legal move specification", ex.getMessage());
        }
    }
}
