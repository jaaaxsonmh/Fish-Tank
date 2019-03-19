package Managers;

import java.util.HashMap;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public enum ButtonEnum {
    BUBBLES(0), FISH(1), REMOVE(2);

    public static HashMap<Integer, String> Title = new HashMap<>();
    public final int ID;

    public static void init() {
        Title.put(0, "Bubbles");
        Title.put(1, "Fish");
        Title.put(2, "Remove");
    }

    ButtonEnum(int id) {
        this.ID = id;
    }
}
