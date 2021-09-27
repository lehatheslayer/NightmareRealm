package wga.app.gui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import wga.app.objects.Game;

/**
 * Класс пользовательского интерфейса
 */
public class Gui {
    /**
     * Размеры окна, кнопки рестарта соответсвенно, а также таблица цветов по типу клеток
     */
    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 500;
    private static final double BUTTON_WIDTH = 70;
    private static final double BUTTON_HEIGHT = 50;
    private static final Map<wga.app.objects.Cell, Color> COLORS = new HashMap<>() {{
        put(wga.app.objects.Cell.EMPTY_CELL, Color.WHITE);
        put(wga.app.objects.Cell.BLOCKED_CELL, Color.BLACK);
        put(wga.app.objects.Cell.ORANGE_CELL, Color.ORANGE);
        put(wga.app.objects.Cell.YELLOW_CELL, Color.YELLOW);
        put(wga.app.objects.Cell.RED_CELL, Color.RED);
    }};

    /**
     * Данное поле указывает на клетку, которую хотят передвинуть
     */
    private Cell selected;

    /**
     * Булевое поле, указывающее на то, идет ли игра или нет
     */
    private boolean running;

    /**
     * Метод отрисовывает игровое приложение: все клетки, кнопку рестарта
     *
     * @param game - объект игры
     * @return игровая панель, родительский объект, который хранит внутри себя все остальные объекты
     */
    public Parent createContent(Game game) {
        Pane root = new Pane();
        root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

        Button btn = new Button();
        btn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setText("Restart");
        btn.setTranslateX(SCENE_WIDTH - BUTTON_WIDTH - (SCENE_WIDTH - Game.SIZE * Cell.TILE_SIZE - BUTTON_WIDTH) / 2);
        btn.setTranslateY((SCENE_HEIGHT - BUTTON_HEIGHT) / 2);
        btn.setOnAction(actionEvent -> generateField(game, root));

        root.getChildren().add(btn);

        generateField(game, root);

        return root;
    }

    /**
     * Метод отрисовывает игровое поле
     *
     * @param game - объект игры
     * @param root - объект игровой панели
     */
    public void generateField(Game game, Pane root) {
        running = true;
        game.start();

        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                Cell cell = new Cell(i, j);
                cell.setTranslateX(i * Cell.TILE_SIZE);
                cell.setTranslateY(j * Cell.TILE_SIZE);
                cell.setColor(COLORS.get(game.getField()[j][i]));
                clickHandle(cell, game);

                root.getChildren().add(cell);
            }
        }
    }

    /**
     * Метод переопределяет для каждой клетки метод setOnMouseClicked
     *
     * @param cell - объект клетки
     * @param game - объект игры
     */
    private void clickHandle(Cell cell, Game game) {
        cell.setOnMouseClicked(mouseEvent -> {
            if (!running) {
                return;
            }

            if (selected == null) {
                if (cell.getColor() == Color.BLACK) {
                    return;
                }

                selected = cell;
                setHover();
                return;
            }

            if (cell.getColor() == Color.BLACK || cell.equals(selected)) {
                deleteHover();
                selected = null;
                return;
            }

            deleteHover();
            swapCells(cell, selected, game);
            selected = null;
        });
    }

    /**
     * Метод выделяет выбранную клетку
     */
    private void setHover() {
        selected.getRectangle().setStroke(Color.GREEN);
        selected.getRectangle().setStrokeWidth(5);
    }

    /**
     * Метод удаляет выделение у клетки
     */
    private void deleteHover() {
        selected.getRectangle().setStroke(Color.BLACK);
        selected.getRectangle().setStrokeWidth(1);
    }

    /**
     * Метод меняет местами клетки (двигает игровую клетку на свободную клетку)
     *
     * @param cell1 - клетка 1
     * @param cell2 - клетка 2
     * @param game - игра
     */
    private void swapCells(Cell cell1, Cell cell2, Game game) {
        if (game.isValidSwap(cell1.x, cell1.y, cell2.x, cell2.y)) {
            game.swapCells(cell1.x, cell1.y, cell2.x, cell2.y);
            Cell.swap(cell1, cell2);

            if (game.isWin()) {
                win();
            }
        }
    }

    /**
     * Метод вызывается при победе, отрисовывает надпись о победе
     */
    private void win() {
        running = false;
        showAlertWithHeaderText();
    }

    /**
     * Метод уведомляет игрока о победе
     */
    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WIN!");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You won the game!");

        alert.showAndWait();
    }
}

