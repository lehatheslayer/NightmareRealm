package wga.app.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс игры, содержит всю логику игры
 */
public class Game {
    /**
     * Размер квадратного игрового поля
     */
    public static final int SIZE = 5;

    /**
     * Двумерный массив, хранящий все клетки. Представляет собой игровое поле
     */
    private Cell[][] field;

    /**
     * Создает объект игры, инициализирует игровое поле
     */
    public Game() {
        field = new Cell[SIZE][SIZE];
    }

    /**
     * Запускает/перезапускает игру
     */
    public void start() {
        generateField();
    }

    /**
     * Заполняет игровое поле клетками
     */
    private void generateField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j += 2) {
                field[i][j] = i % 2 == 0 ? Cell.BLOCKED_CELL : Cell.EMPTY_CELL;
            }
        }

        List<Cell> colors = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            colors.addAll(Cell.PLAYABLE_CELLS);
        }

        Collections.shuffle(colors);

        for (int i = 0; i < SIZE; i += 2) {
            for (int j = 0; j < SIZE; j++) {
                field[j][i] = colors.get(j + (i / 2) * SIZE);
            }
        }
    }

    /**
     * Метод меняет местами клетки (двигает игровую клетку на свободную клетку)
     *
     * @param x1 - координата x первой клетки
     * @param y1 - координата y первой клетки
     * @param x2 - координата x второй клетки
     * @param y2 - координата y второй клетки
     */
    public void swapCells(int x1, int y1, int x2, int y2) {
        swap(x1, y1, x2, y2);
    }

    /**
     * Метод проверяет валидность движения клеток
     *
     * @param x1 - координата x первой клетки
     * @param y1 - координата y первой клетки
     * @param x2 - координата x второй клетки
     * @param y2 - координата y второй клетки
     * @return - валидность (true/false)
     */
    public boolean isValidSwap(int x1, int y1, int x2, int y2) {
        if (!(x1 >= 0 && x1 < SIZE && y1 >= 0 && y1 < SIZE
                && x2 >= 0 && x2 < SIZE && y2 >= 0 && y2 < SIZE)) {
            return false;
        }

        if (field[y1][x1] == Cell.BLOCKED_CELL || field[y2][x2] == Cell.BLOCKED_CELL) {
            return false;
        }

        if (field[y1][x1] != Cell.EMPTY_CELL && field[y2][x2] != Cell.EMPTY_CELL) {
            return false;
        }

        return (x1 == x2 && Math.abs(y1 - y2) == 1)
                || (y1 == y2 && Math.abs(x1 - x2) == 1);
    }

    /**
     * Метод меняет местами клетки (двигает игровую клетку на свободную клетку)
     *
     * @param x1 - координата x первой клетки
     * @param y1 - координата y первой клетки
     * @param x2 - координата x второй клетки
     * @param y2 - координата y второй клетки
     */
    private void swap(int x1, int y1, int x2, int y2) {
        Cell tmp = field[y1][x1];
        field[y1][x1] = field[y2][x2];
        field[y2][x2] = tmp;
    }

    /**
     * Метод проверяет выиграна ли игра
     *
     * @return - true/false
     */
    public boolean isWin() {
        for (int i = 0; i < SIZE; i += 2) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (field[j][i] != field[j + 1][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Возвращает игровое поле
     *
     * @return - игровое поле
     */
    public Cell[][] getField() {
        return field;
    }

    /**
     * Устанавливает новое значение поля
     * Используется для тестов
     *
     * @param field - новое поле
     */
    public void setField(Cell[][] field) {
        this.field = field;
    }

    /**
     * Метод генерирует собранное поле
     * Используется для тестов
     *
     * @return - собранное поле
     */
    public static Cell[][] generateWinningField() {
        Cell[][] winningField = new Cell[Game.SIZE][Game.SIZE];

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

