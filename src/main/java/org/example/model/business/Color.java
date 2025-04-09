package org.example.model.business;

import java.util.HashMap;
import java.util.Map;

/**
 * Color for the pawn
 */
public enum Color {
    RED(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    VIOLET(4);

    private static final Map<Integer, Color> map = new HashMap<>();
    private final int value;

    static {
        for (Color color : Color.values()) {
            map.put(color.value, color);
        }
    }

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Color getColor(int value) {
        return map.get(value);
    }

}
