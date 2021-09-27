package wga.app.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Объект игрового интерфейса, представляющий собой клетку
 */
public class Cell extends StackPane {
    /**
     * Размер клетки (клетки являются квадратными)
     */
    public static final double TILE_SIZE = 100;

    /**
     * Прямоугольник, представляющий клетку
     */
    private final Rectangle rectangle;

    /**
     * Координаты клетки
     */
    public int x;
    public int y;

    /**
     * Создает объект-клетку игрового интерфейса
     *
     * @param x - координата по оси X
     * @param y - координата по оси Y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().add(rectangle);
    }

    /**
     * Метод вызывается при изменении цвета клетки
     *
     * @param color - новый цвет
     */
    public void setColor(Color color) {
        rectangle.setFill(color);
    }

    /**
     * Метод возвращает текущий цвет клетки
     *
     * @return - цвет клетки
     */
    public Color getColor() {
        return (Color) rectangle.getFill();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Статический метод, который меняет местами цвета у двух клеток
     *
     * @param cell1 - клетка 1
     * @param cell2 - клетка 2
     */
    public static void swap(Cell cell1, Cell cell2) {
        Color color1 = cell1.getColor();
        cell1.setColor(cell2.getColor());
        cell2.setColor(color1);
    }
}
