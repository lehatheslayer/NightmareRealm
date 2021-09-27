package wga.app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import wga.app.gui.Gui;
import wga.app.objects.Game;

// TODO: напиши тесты на методы, README.md

/**
 * Главный класс программы, запускает оконное приложение, связывает GUI с бизнес-логикой
 */
public class Main extends Application {
    /**
     * Объекты игры и пользовательского интерфейса
     */
    private final Game game = new Game();
    private final Gui gui = new Gui();

    /**
     * Метод отрисовывает игровое приложение: все клетки, кнопку рестарта
     *
     * @return - игровой холст, внутри которого хранятся все элементы
     */
    private Parent createContent() {
        return gui.createContent(game);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


