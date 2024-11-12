package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TextMapTest {
    private List<String> texts;
    private TextMap textMap;
    @BeforeEach
    void setUp() {
        texts  = List.of("Ala","Ma","duzo","kotow","i","niedzwiedza");
        textMap = new TextMap();
    }

    @Test
    void place() {
        assertTrue( textMap.place(texts.getFirst()));
        assertTrue( textMap.place(texts.get(2)));
        assertFalse( textMap.place(texts.getFirst()));
    }

    @Test
    void move() {
        texts.forEach(textMap::place);

        //Forward
        textMap.move(texts.getFirst(),MoveDirection.FORWARD);
        assertEquals(texts.getFirst(),textMap.objectAt(0));

        textMap.move(texts.get(1),MoveDirection.FORWARD);
        assertEquals(texts.get(1),textMap.objectAt(0));

        //Backward
        textMap.move(texts.getFirst(),MoveDirection.BACKWARD);
        assertEquals(texts.getFirst(),textMap.objectAt(0));

        textMap.move(texts.get(2),MoveDirection.BACKWARD);
        assertEquals(texts.get(2),textMap.objectAt(1));

        //Left
        textMap.move(texts.getLast(),MoveDirection.LEFT);
        assertEquals(texts.getLast(),textMap.objectAt(texts.size()-1));

        textMap.move(texts.get(texts.size()-2),MoveDirection.LEFT);
        assertEquals(texts.get(texts.size()-2),textMap.objectAt(texts.size()-1));

        //Right
        textMap.move(texts.getLast(),MoveDirection.RIGHT);
        assertEquals(texts.getLast(),textMap.objectAt(texts.size()-1));

        textMap.move(texts.get(texts.size()-3),MoveDirection.RIGHT);
        assertEquals(texts.get(texts.size()-3),textMap.objectAt(texts.size()-2));
    }

    @Test
    void canMoveTo() {
        texts.forEach(textMap::place);
        assertTrue(textMap.canMoveTo(1));
        assertFalse(textMap.canMoveTo(-1));
        assertFalse(textMap.canMoveTo(texts.size()));
    }
}