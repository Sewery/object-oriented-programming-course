package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void validInputConvertToMoveDirections() {
        //given
        String[] input = {"b", "f", "l","r"};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(4, moveDirections.size());
        var expected = List.of(MoveDirection.BACKWARD,MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT);
        assertEquals(expected, moveDirections);
    }
    @Test
    void invalidInputConvertToMoveDirections() {
        String[] input = {"f", "b", "l", "", "dre.fwwd","r"};
        assertThrows(IllegalArgumentException.class, ()->OptionsParser.convertToMoveDirections(input));
    }
    @Test
    void emptyInputConvertToMoveDirections() {
        //given
        String[] input = {};

        //when
        var moveDirections = OptionsParser.convertToMoveDirections(input);

        //then
        assertEquals(0, moveDirections.size());
        var expected = List.of();
        assertEquals(expected, moveDirections);
    }
}