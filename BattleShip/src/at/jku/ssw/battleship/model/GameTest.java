package at.jku.ssw.battleship.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game(Game.createRandomPlayingField(4, 2));
    //Constructor testing
    @Test public void testingConstructor() {
        try {
            new Game(new Field(3));
        } catch (IllegalArgumentException e) {
            fail("Exception was expected for minus input");
        }
    }
    //Method testing
    @Test
    void getField() {
        Field field =game.getField();
        assertEquals(field,game.getField());
    }

    @Test
    void isRunning() {
        assertEquals(true,game.isRunning());
    }

    @Test
    void getShipCount() {
        assertEquals(2,game.getShipCount());
    }

    @Test
    void fireAt() {
        //hier its default to test because sometimes is true and sometimes not ;
        //the reason is the random pos creator
        //but 80% false
        assertEquals(false,game.fireAt(3,2));
    }

    @Test
    void createRandomPlayingField() {
        Game game2 = new Game(Game.createRandomPlayingField(4, 2));
assertEquals(2,game2.getShipCount());
    }
}