package Managers;

import java.util.HashMap;

public enum ButtonEnum {
    BUBBLES(0), FISH(1);

    public static HashMap<Integer, String> Title = new HashMap<>();
    public final int ID;

    public static void init() {
        Title.put(0, "Bubbles");
        Title.put(1, "Spawn Fish");
    }

    ButtonEnum(int id) {
        this.ID = id;
    }
}
