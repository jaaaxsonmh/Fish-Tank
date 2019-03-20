package Managers;

import Models.Bubbles.Bubble;
import Models.Water;
import com.jogamp.opengl.GL2;
import ulits.Colour;
import ulits.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class BubbleManager {
    private final static Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);

    private List<Bubble> bubbles = new ArrayList<>();
    private List<Bubble> toRemove = new ArrayList<>();
    private static final int BUBBLE_AMOUNT = 40;
    private boolean enabled;
    private final long TIME_DELAY = 100L;
    private long prevTime = System.currentTimeMillis() - TIME_DELAY;

    private void populate() {
        long currentTime = System.currentTimeMillis();
        if((currentTime - prevTime) >= TIME_DELAY) {
            prevTime = currentTime;
            if (BUBBLE_AMOUNT > bubbles.size()) {
                float transparency = Rand.getFloatBetween(0.5f, 1.0f);
                float radius = Rand.getFloatBetween(0.03f, 0.05f);
                float offsetX = Rand.getFloatBetween(0.7f, 0.9f);
                float offsetY = Rand.getFloatBetween(-0.7f, -0.8f);
                float age = Rand.getFloatBetween(0.005f, 0.01f);


                bubbles.add(new Bubble(radius, offsetX, offsetY, age, WHITE, transparency));
            }
        }

    }

    public void stateManager(GL2 gl, double interp) {
        if (enabled) {
            populate();
            for (Bubble bub : bubbles) {
                bub.draw(gl);
                bub.animate(interp);
                reset();
            }
        } else {
            for (Bubble bub : bubbles) {
                bub.draw(gl);
                bub.animate(interp);

            }
            hardReset();
            bubbles.removeAll(toRemove);
            System.out.println(bubbles.size());
        }
    }


    private void reset() {
        for (Bubble bub : bubbles) {
            float resetY = Rand.getFloatBetween(-0.7f, -0.8f);
            float resetX = Rand.getFloatBetween(0.7f, 0.9f);
            float radius = Rand.getFloatBetween(0.03f, 0.05f);
            float transparency = Rand.getFloatBetween(0.5f, 1.0f);

            if (bub.offsetY >= Water.WAVE_WATER_HEIGHT) {
                bub.offsetY = resetY;
            }
            if (bub.transparency < 0.0f || bub.radius < 0.0f) {
                bub.offsetY = resetY;
                bub.offsetX = resetX;
                bub.transparency = transparency;
                bub.radius = radius;
            }
        }
    }

    private void hardReset() {
        for (Bubble bub : bubbles) {
            if (bub.offsetY >= Water.WAVE_WATER_HEIGHT) {
                toRemove.add(bub);
            }
            if (bub.transparency < 0.0f || bub.radius < 0.0f) {
                toRemove.add(bub);
            }
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
