package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    private Simulation<Animal,Vector2d> simulation1;
    private Simulation<String,Integer> simulation2;
    WorldMap<Animal,Vector2d> worldMap1;
    private WorldMap<String,Integer> worldMap2;
    @BeforeEach
    void setUp() {
        var movements = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,MoveDirection.FORWARD);

        var positionsVec = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        worldMap1 = new RectangularMap(4,4);
        List<Animal> animals = positionsVec.stream().map(Animal::new).toList();
        simulation1 = new Simulation<>(movements, worldMap1, animals);

        List<String> texts = List.of("Ala","Ma","duzo","kotow","i","niedzwiedza");
        worldMap2 = new TextMap();
        texts.forEach(worldMap2::place);
        simulation2 = new Simulation<>(movements, worldMap2, texts);

    }

    @Test
    void initialPositions() {
        var animals = simulation1.getEntities();
        assertEquals(new Vector2d(2, 2), animals.get(0).getCurrPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getCurrPosition());


    }

    @Test
    void runSimulation() {
        //For RectangularMap
        simulation1.run();
        List<Animal> animals = simulation1.getEntities();

        // Check final positions and orientations
        assertEquals(new Vector2d(2, 4), animals.get(0).getCurrPosition());
        assertEquals("N", animals.get(0).getOrientation().toString());


        assertEquals(new Vector2d(4, 3), animals.get(1).getCurrPosition());
        assertEquals("E", animals.get(1).getOrientation().toString());

        //For TextMap
        simulation2.run();
        var texts = simulation2.getEntities();
        // Check final positions
        assertEquals(texts.get(2), worldMap2.objectAt(0));
        assertEquals(texts.getFirst(), worldMap2.objectAt(1));
    }

}
