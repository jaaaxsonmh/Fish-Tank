package Managers;

import java.util.HashMap;

enum ButtonEnum {
    BUBBLES(0), FISH(1);

    static HashMap<Integer, String> Title = new HashMap<>();
    final int ID;

    static void init() {
        Title.put(0, "Bubbles");
        Title.put(1, "Spawn Fish");
    }

    ButtonEnum(int id) {
        this.ID = id;
    }
}
