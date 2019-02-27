package ulits;

import java.util.Random;

public class Rand {

    private Rand() {

    }

    public final static Random rand = new Random();

    public static int getIntBetween(int min, int max) {
        return rand.nextInt() * (max - min) + min;
    }

    public static float getFloatBetween(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }

    public static Colour getRandomRGB() {
        return new Colour(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    public static Colour getRandomRGBA() {
        return new Colour(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }




}
