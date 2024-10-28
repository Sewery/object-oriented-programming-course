package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {
    @Test
    void validInputConvertToMoveDirections() {
        //given
        String[] input = {"b", "f", "l","r"};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(4, moveDirections.length);
        var expected = new MoveDirection[]{MoveDirection.BACKWARD,MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(expected, moveDirections);
    }
    @Test
    void invalidInputConvertToMoveDirections() {
        //given
        String[] input = {"f", "b", "l", "", "dre.fwwd","r"};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(4, moveDirections.length);
        var expected = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(expected, moveDirections);
    }
    @Test
    void emptyInputConvertToMoveDirections() {
        //given
        String[] input = {};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(0, moveDirections.length);
        var expected = new MoveDirection[]{};
        assertArrayEquals(expected, moveDirections);
    }
}