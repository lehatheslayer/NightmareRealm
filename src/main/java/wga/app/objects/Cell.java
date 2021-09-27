package wga.app.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Игровой объект, представляющий собой игровую клетку
 */
public enum Cell {
    /**
     * Все существующие виды клеток
     */
    EMPTY_CELL,
    BLOCKED_CELL,
    YELLOW_CELL,
    ORANGE_CELL,
    RED_CELL;

    /**
     * Список играбельных клеток (те, которые можно двигать)
     */
    public final static List<Cell> PLAYABLE_CELLS = new ArrayList<>() {{
        add(YELLOW_CELL);
        add(ORANGE_CELL);
        add(RED_CELL);
    }};
}
