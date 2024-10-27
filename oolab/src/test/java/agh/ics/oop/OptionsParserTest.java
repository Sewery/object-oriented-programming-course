package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {
    @Test
    void convertToMoveDirections() {
        //given
        String[] input = {"f", "b", "l", "r", "dre.fwwd"};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(3, moveDirections.length);
        var expected = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        assertEquals(expected, moveDirections);
    }
}