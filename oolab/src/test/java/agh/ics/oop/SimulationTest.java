package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimulationTest {
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        var positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        var movements = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD);
        simulation = new Simulation(positions, movements);
    }

    @Test
    void initialPositions() {
        var animals = simulation.getAnimals();
        assertEquals(new Vector2d(2, 2), animals.get(0).getPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getPosition());
    }

    @Test
    void runSimulation() {
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        // Check final positions and orientations
        assertEquals(new Vector2d(2, 3), animals.get(0).getPosition());
        assertEquals("Wschod", animals.get(0).getOrientation().toString());

        assertEquals(new Vector2d(3, 4), animals.get(1).getPosition());
        assertEquals("Polnoc", animals.get(1).getOrientation().toString());
    }

    @Test
    void outOfBoundsMovement() {
        List<Vector2d> invalidPositions = List.of(new Vector2d(-1, -1));
        List<MoveDirection> movements = List.of(MoveDirection.FORWARD);

        assertThrows(IllegalArgumentException.class, () -> {
            new Simulation(invalidPositions, movements);
        });
    }
}
