package Managers;

import Models.Bubbles.Bubble;
import com.jogamp.opengl.GL2;
import ulits.Colour;
import ulits.Rand;

import java.util.ArrayList;
import java.util.List;

public class BubbleManager {

    private List<Bubble> bubbles = new ArrayList<Bubble>();
    private static final int BUBBLE_AMOUNT = 5;
    float transparency, radius, offsetX, offsetY, age;


    public void populate() {
        if (bubbles.size() < BUBBLE_AMOUNT) {
            transparency = Rand.getFloatBetween(0.5f, 1.0f);
            radius = Rand.getFloatBetween(0.03f, 0.05f);
            offsetX = Rand.getFloatBetween(0.8f, 0.9f);
            offsetY = Rand.getFloatBetween(-0.5f, -0.55f);
            age = Rand.getFloatBetween(0.005f, 0.01f);
            Colour colour = new Colour(0.25882f, 0.64706f, 0.96078f);

            bubbles.add(new Bubble(radius, offsetX, offsetY, age, colour, transparency));
        }
    }

    public void draw(GL2 gl) {
        for (Bubble bub : bubbles) {
            bub.draw(gl);
            bub.animate(gl);
        }
    }

    public static void recycler(List<Bubble> bubbles) {
        for (Bubble bub : bubbles) {
            bub.reset();
        }
    }

    public void reset() {

    }
}
