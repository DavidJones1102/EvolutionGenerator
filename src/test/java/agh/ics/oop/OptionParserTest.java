package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class OptionParserTest {
    @Test
    public void parseTest(){
        String[] tab = {"f","forward","g","","r","right","l","left","fds","b","backward"};
        MoveDirection[] tab_enum = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD};
        assertTrue(Arrays.equals(OptionsParser.parse(tab),tab_enum));
    }
}
