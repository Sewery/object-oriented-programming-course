package agh.ics.oop.model;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    private RectangularMap map1;
    private List<Animal> animals;

    @BeforeEach
    void setUp() {
        //I assume that in that case method place in RectangularMap for below  works correctly
        //methods are tightly coupled so it's impossible to test them independently
        map1 = new RectangularMap(10, 10);
        var animal1 = new Animal();
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(5, 2));
        var animal4 = new Animal(new Vector2d(9, 9));
        try {
            map1.place(animal1);
            map1.place(animal2);
            map1.place(animal3);
            map1.place(animal4);
        } catch (IncorrectPositionException e) {
            fail(e.getLocalizedMessage());
        }


        animals = List.of(animal1, animal2, animal3, animal4);
    }

    @Test
    void objectAt() {
        assertEquals(animals.get(0), map1.objectAt(new Vector2d(2, 2)));
        assertEquals(animals.get(1), map1.objectAt(new Vector2d(0, 0)));
        assertEquals(animals.get(2), map1.objectAt(new Vector2d(5, 2)));
        assertEquals(animals.get(3), map1.objectAt(new Vector2d(9, 9)));
    }

    @Test
    void isOccupied() {
        assertTrue(map1.isOccupied(new Vector2d(2, 2)));
        assertTrue(map1.isOccupied(new Vector2d(0, 0)));
        assertTrue(map1.isOccupied(new Vector2d(5, 2)));
        assertTrue(map1.isOccupied(new Vector2d(9, 9)));

        assertFalse(map1.isOccupied(new Vector2d(10, 10)));
        assertFalse(map1.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void canMoveTo() {
        assertFalse(map1.canMoveTo(new Vector2d(-1, -1)));
        assertTrue(map1.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map1.canMoveTo(new Vector2d(5, 2)));
        assertFalse(map1.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map1.canMoveTo(new Vector2d(9, 9)));

        //Checking if on corners can be animal
        RectangularMap map2 = new RectangularMap(8, 8);
        assertTrue(map2.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map2.canMoveTo(new Vector2d(8, 8)));
    }

    @Test
    void place() {
        var animal1 = new Animal(new Vector2d(4, 6));
        try {
            map1.place(animal1);
        } catch (IncorrectPositionException e) {
            fail(e.getLocalizedMessage());
        }
        //Checking if
        var animal2 = new Animal(new Vector2d(5, 2));
        assertThrows(IncorrectPositionException.class, () -> map1.place(animal2));

        //Returns true when we try place already placed animal
        assertThrows(IncorrectPositionException.class, () ->  map1.place(animals.getFirst()));

    }

    @Test
    void move() {
        map1.move(animals.getFirst(), MoveDirection.FORWARD);
        assertEquals(animals.getFirst(), map1.objectAt(new Vector2d(2, 3)));

        map1.move(animals.getLast(), MoveDirection.FORWARD);
        assertEquals(animals.getLast(), map1.objectAt(new Vector2d(9, 9)));
    }

}