package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    private Vector2d v1;
    private Vector2d v2;
    private Vector2d v3;

    @BeforeEach
    void setUp() {
        v1 = new Vector2d(1, 2);
        v2 = new Vector2d(-2, 0);
        v3 = new Vector2d(3, 4);
    }

    @Test
    void testToString() {
        assertEquals("(1,2)", v1.toString());
        assertEquals("(-2,0)", v2.toString());
        assertEquals("(3,4)", v3.toString());
    }

    @Test
    void precedes() {
        assertTrue(v1.precedes(v3));
        assertFalse(v1.precedes(v2));
        assertTrue(v2.precedes(v3));
        assertTrue(v2.precedes(v2));
    }

    @Test
    void follows() {
        assertFalse(v1.follows(v3));
        assertTrue(v1.follows(v2));
        assertFalse(v2.follows(v3));
        assertTrue(v2.follows(v2));
    }

    @Test
    void add() {
        var v1v2 = v1.add(v2);
        var v2v2 = v2.add(v2);
        var v2v3 = v2.add(v3);

        assertEquals(new Vector2d(-1, 2), v1v2);
        assertEquals(new Vector2d(-4, 0), v2v2);
        assertEquals(new Vector2d(1, 4), v2v3);
    }

    @Test
    void subtract() {
        var v1v2 = v1.subtract(v2);
        var v2v2 = v2.subtract(v2);
        var v2v3 = v2.subtract(v3);

        assertEquals(new Vector2d(3, 2), v1v2);
        assertEquals(new Vector2d(0, 0), v2v2);
        assertEquals(new Vector2d(-5, -4), v2v3);
    }

    @Test
    void upperRight() {
        var v1v2 = v1.upperRight(v2);
        var v2v2 = v2.upperRight(v2);
        var v2v3 = v2.upperRight(v3);

        assertEquals(new Vector2d(1, 2), v1v2);
        assertEquals(new Vector2d(-2, 0), v2v2);
        assertEquals(new Vector2d(3, 4), v2v3);
    }

    @Test
    void lowerLeft() {
        var v1v2 = v1.lowerLeft(v2);
        var v2v2 = v2.lowerLeft(v2);
        var v2v3 = v2.lowerLeft(v3);

        assertEquals(new Vector2d(-2, 0), v1v2);
        assertEquals(new Vector2d(-2, 0), v2v2);
        assertEquals(new Vector2d(-2, 0), v2v3);
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -2), v1.opposite());
        assertEquals(new Vector2d(2, 0), v2.opposite());
        assertEquals(new Vector2d(-3, -4), v3.opposite());
    }

    @Test
    void reflexiveEquals() {
        assertEquals(v3,v3);
    }

    @Test
    void symmetricEquals() {
        assertEquals(new Vector2d(1, 2),v1);
        assertEquals(v1,new Vector2d(1, 2));

    }
    @Test
    void transitiveEquals() {
        Vector2d vy = new Vector2d(1, 2);
        Vector2d vz = new Vector2d(1, 2);

        assertEquals(v1,vy);
        assertEquals(vy,vz);
        assertEquals(v1,vz);

    }
    @Test
    void notNullEquals() {
        assertNotEquals(v2, v1);
        assertNotEquals(null, v1);

    }
}
