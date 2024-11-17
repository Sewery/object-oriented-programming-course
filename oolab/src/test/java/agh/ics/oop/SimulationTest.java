package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        var positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        var movements = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,MoveDirection.FORWARD);
        WorldMap worldMap = new RectangularMap(4,4);
        simulation = new Simulation(positions,movements, worldMap);
    }

    @Test
    void initialPositions() {
        var animals = simulation.getAnimals();
        assertEquals(new Vector2d(2, 2), animals.get(0).getCurrPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getCurrPosition());
    }

    @Test
    void runSimulation() {
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        // Check final positions and orientations
        assertEquals(new Vector2d(2, 3), animals.get(0).getCurrPosition());
        assertEquals("N", animals.get(0).getOrientation().toString());


        assertEquals(new Vector2d(3, 3), animals.get(1).getCurrPosition());
        assertEquals("E", animals.get(1).getOrientation().toString());
    }

}
