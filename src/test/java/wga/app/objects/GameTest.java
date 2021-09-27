package wga.app.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты логики игры
 */
class GameTest {

    @Test
    void validCellsSwap() {
        Game game = new Game();
        game.start();

        Cell[][] expectedField = game.getField();

        game.swapCells(0, 1, 1, 1);

        Cell tmp = expectedField[1][0];
        expectedField[1][0] = expectedField[1][1];
        expectedField[1][1] = tmp;

        assertEquals(expectedField, game.getField());
    }

    @Test
    void invalidCellsSwap() {
        Game game = new Game();
        game.start();

        Cell[][] expectedField = game.getField();

        game.swapCells(0, 1, 3, 3);

        assertEquals(expectedField, game.getField());
    }

    @Test
    void isValidSwapNeighbours() {
        Game game = new Game();
        game.start();

        boolean result = game.isValidSwap(0, 1, 1, 1);

        assertTrue(result);
    }

    @Test
    void isValidSwapNonNeighbours() {
        Game game = new Game();
        game.start();

        boolean result = game.isValidSwap(0, 1, 4, 3);

        assertFalse(result);
    }

    @Test
    void isValidSwapIndexOutOfBonds() {
        Game game = new Game();
        game.start();

        boolean result = game.isValidSwap(5, 1, 2, 3);

        assertFalse(result);
    }

    @Test
    void isWin1() {
        Game game = new Game();
        game.setField(Game.generateWinningField());

        assertTrue(game.isWin());
    }

    @Test
    void isWin2() {
        Game game = new Game();
        game.start();

        assertFalse(game.isWin());
    }
}