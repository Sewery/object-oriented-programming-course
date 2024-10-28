package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void previous() {
        var north = MapDirection.NORTH;
        var south = MapDirection.SOUTH;
        var east = MapDirection.EAST;
        var west = MapDirection.WEST;

        //then
        assertEquals(MapDirection.WEST, north.previous());
        assertEquals(MapDirection.EAST, south.previous());
        assertEquals(MapDirection.NORTH, east.previous());
        assertEquals(MapDirection.SOUTH, west.previous());
    }

    @Test
    void next() {
        var north = MapDirection.NORTH;
        var south = MapDirection.SOUTH;
        var east = MapDirection.EAST;
        var west = MapDirection.WEST;

        //then
        assertEquals(MapDirection.EAST, north.next());
        assertEquals(MapDirection.WEST, south.next());
        assertEquals(MapDirection.SOUTH, east.next());
        assertEquals(MapDirection.NORTH, west.next());
    }
}