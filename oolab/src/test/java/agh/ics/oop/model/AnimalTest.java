package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;
    private Animal animal4;
    private MoveValidator moveValidator;
    @BeforeEach
    void setUp() {
        animal1 = new Animal();
        animal2 = new Animal(new Vector2d(0, 0));
        animal3 = new Animal(new Vector2d(3, 2));
        animal4 = new Animal(new Vector2d(4, 4));
        moveValidator = new RectangularMap(4,4);
    }

    @Test
    void initialOrientation() {
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
    }
    @Test
    void afterRightMovementOrientation() {
        //Checks right movement
        animal1.move(MoveDirection.RIGHT,moveValidator);
        assertEquals(MapDirection.EAST, animal1.getOrientation());

        animal1.move(MoveDirection.RIGHT,moveValidator);
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());

        animal1.move(MoveDirection.RIGHT,moveValidator);
        assertEquals(MapDirection.WEST, animal1.getOrientation());

        animal1.move(MoveDirection.RIGHT,moveValidator);
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
    }
    @Test
    void afterLeftMovementOrientation() {
        //Checks left movement
        animal1.move(MoveDirection.LEFT,moveValidator);
        assertEquals(MapDirection.WEST, animal1.getOrientation());

        animal1.move(MoveDirection.LEFT,moveValidator);
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());

        animal1.move(MoveDirection.LEFT,moveValidator);
        assertEquals(MapDirection.EAST, animal1.getOrientation());

        animal1.move(MoveDirection.LEFT,moveValidator);
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
    }
    @Test
    void afterForwardMovementOrientation() {

        animal3.move(MoveDirection.FORWARD,moveValidator);
        assertEquals(MapDirection.NORTH, animal3.getOrientation());

        animal4.move(MoveDirection.FORWARD,moveValidator);//Animal on right upper map corner case
        assertEquals(MapDirection.NORTH, animal4.getOrientation());
    }
    @Test
    void afterBackwardMovementOrientation() {
        //Checks orientation after backward movement
        animal3.move(MoveDirection.BACKWARD,moveValidator);
        assertEquals(MapDirection.NORTH, animal3.getOrientation());

        animal2.move(MoveDirection.BACKWARD,moveValidator);//Animal on left lower map corner case
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
    }
    @Test
    void forwardMovement() {
        //from north forward
        animal3.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(3, 3)));

        animal4.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal4.isAt(new Vector2d(4, 4)));

        //from west forward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(2, 3)));

        //from south forward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(2, 2)));

        //from east forward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(3, 2)));

        animal4.move(MoveDirection.RIGHT,moveValidator);
        animal4.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal4.isAt(new Vector2d(4, 4)));

    }
    @Test
    void backwardMovement() {
        //From north backward
        animal3.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(3, 1)));

        animal2.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal2.isAt(new Vector2d(0, 0)));

        //from west backward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(4, 1)));

        //from south backward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(4, 2)));

        //from east backward
        animal3.move(MoveDirection.LEFT,moveValidator);
        animal3.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal3.isAt(new Vector2d(3, 2)));

        animal2.move(MoveDirection.RIGHT,moveValidator);
        animal2.move(MoveDirection.BACKWARD,moveValidator);
        assertTrue(animal2.isAt(new Vector2d(0, 0)));

    }
    @Test
    void alwaysOnMap() {
        //From left lower corner
        animal2.move(MoveDirection.LEFT,moveValidator);
        animal2.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal2.isAt(new  Vector2d(0, 0)));

        animal2.move(MoveDirection.LEFT,moveValidator);
        animal2.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal2.isAt(new Vector2d(0, 0)));

        //From right upper corner
        animal4.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal4.isAt(new Vector2d(4, 4)));

        animal4.move(MoveDirection.RIGHT,moveValidator);
        animal4.move(MoveDirection.FORWARD,moveValidator);
        assertTrue(animal4.isAt(new  Vector2d(4, 4)));

        //Initializing Animal on wrong position
        //assertThrowsExactly(IllegalArgumentException.class, () -> new Animal(new Vector2d(-1,5)));


    }
}
