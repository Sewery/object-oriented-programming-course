package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
class GrassFieldTest {
    private GrassField grassField;
    private List<Animal> animals;
    @BeforeEach
    void setUp() {
        grassField = new GrassField(10);
        var animal1 = new Animal();
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(5, 2));
        var animal4 = new Animal(new Vector2d(9, 9));
        grassField.place(animal1);
        grassField.place(animal2);
        grassField.place(animal3);
        grassField.place(animal4);

        animals = List.of(animal1, animal2, animal3, animal4);
    }
    @Test
    public void grassPlacement(){
        var grassPositions = RandomPositionsGenerator.generateUniqueList(10,(int)Math.sqrt(10*10));
        Set<Vector2d> grassElementsPositions = grassField.getElements().stream()
                .filter(worldElement -> worldElement.toString().equals("*")) //Searching for grass elements by string
                .map(worldElement -> ((Grass)worldElement).getPosition())//Extracting their positions
                .collect(Collectors.toSet());
        assertEquals(grassPositions, grassElementsPositions);
    }
    @Test
    public void basicPlacement(){
        assertEquals(new Vector2d(0, 0), animals.get(1).getPosition());
        assertEquals(new Vector2d(5, 2), animals.get(2).getPosition());
    }
    @Test
    public void canMoveTo(){
        assertFalse(grassField.canMoveTo(new Vector2d(0,0)));
        assertTrue(grassField.canMoveTo(new Vector2d(6, 2)));

        assertTrue(grassField.canMoveTo(new Vector2d(11, 3)));
    }

    @Test
    public void placement(){
        var animal1 = new Animal(new Vector2d(1, 2));
        var animal2 = new Animal(new Vector2d(2, 2));
        assertTrue(grassField.place(animal1));
        assertFalse(grassField.place(animal2));
    }

    @Test
    public void movement(){
        grassField.move(animals.getFirst(), MoveDirection.FORWARD);
        grassField.move(animals.get(2), MoveDirection.FORWARD);
        assertEquals(animals.getFirst(), grassField.objectAt(new Vector2d(2, 3)));
        assertEquals(animals.get(2), grassField.objectAt(new Vector2d(5, 3)));
    }

    @Test
    public void isOccupied(){
        assertTrue(grassField.isOccupied(new Vector2d(0, 0)));
        assertFalse(grassField.isOccupied(new Vector2d(5, 6)));
    }

    @Test
    public void objectAt(){
        assertEquals(animals.get(1), grassField.objectAt(new Vector2d(0, 0)));
        assertEquals(animals.get(2), grassField.objectAt(new Vector2d(5, 2)));
    }

    @Test
    public void getElements(){
        grassField.place(new Animal());
        grassField.place(new Animal(new Vector2d(2, 3)));
        assertEquals(15, grassField.getElements().size());
    }
}