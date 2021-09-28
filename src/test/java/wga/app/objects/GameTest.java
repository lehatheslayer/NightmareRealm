package wga.app.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isWin(boolean isWinningSituation) {
        if(isWinningSituation) {
            game.setField(GameTest.generateWinningField());
        }

        assertEquals(isWinningSituation, game.isWin());
    }

    /**
     * Метод генерирует собранное поле
     *
     * @return - собранное поле
     */
    public static Cell[][] generateWinningField() {
        final Cell[][] winningField = new Cell[Game.SIZE][Game.SIZE];

        for (int i = 0; i < Game.SIZE; i += 2) {
            for (int j = 0; j < Game.SIZE; j++) {
                winningField[j][i] = Cell.PLAYABLE_CELLS.get(i / 2);
            }
        }

        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 1; j < Game.SIZE; j += 2) {
                winningField[i][j] = i % 2 == 0 ? Cell.BLOCKED_CELL : Cell.EMPTY_CELL;
            }
        }

        return winningField;
    }
}