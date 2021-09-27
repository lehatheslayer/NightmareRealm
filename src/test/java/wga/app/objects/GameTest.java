package wga.app.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты логики игры
 */
class GameTest {
    private Game game;

    @BeforeEach
    public void init() {
        game = new Game();
        game.start();
    }

    @Test
    void validCellsSwap() {
        Cell[][] expectedField = game.getField();

        game.swapCells(0, 1, 1, 1);

        Cell tmp = expectedField[1][0];
        expectedField[1][0] = expectedField[1][1];
        expectedField[1][1] = tmp;

        assertEquals(expectedField, game.getField());
    }

    @Test
    void invalidCellsSwap() {
        Cell[][] expectedField = game.getField();

        game.swapCells(0, 1, 3, 3);

        assertEquals(expectedField, game.getField());
    }

    @Test
    void isValidSwapNeighbours() {
        boolean result = game.isValidSwap(0, 1, 1, 1);

        assertTrue(result);
    }

    @Test
    void isValidSwapNonNeighbours() {
        boolean result = game.isValidSwap(0, 1, 4, 3);

        assertFalse(result);
    }

    @Test
    void isValidSwapIndexOutOfBonds() {
        boolean result = game.isValidSwap(5, 1, 2, 3);

        assertFalse(result);
    }

    @Test
    void isWin1() {
        game.setField(Game.generateWinningField());

        assertTrue(game.isWin());
    }

    @Test
    void isWin2() {
        assertFalse(game.isWin());
    }
}