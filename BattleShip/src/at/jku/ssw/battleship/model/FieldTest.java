package at.jku.ssw.battleship.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
Field testField = new Field(5);
    @Test public void testingConstructor() {
        try {
            new Field(4);
        } catch (IllegalArgumentException e) {
            fail("Exception was expected for minus input");
        }
    }
    @Test
    void getState() {
        assertEquals(State.FREE,testField.getState(2,2));
    }

    @Test
    void getSize() {
        assertEquals(5,testField.size);
    }
}