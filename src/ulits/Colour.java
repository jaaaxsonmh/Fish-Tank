package ulits;

import com.jogamp.opengl.GL2;

public class Colour {
    private float red = 1.0f;
    private float green = 1.0f;
    private float blue = 1.0f;
    private float alpha = 1.0f;

    public Colour() {

    }

    // construct rgba
    public Colour(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    // construct rgb
    public Colour(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // setters for colours

    public void setRed(float red) {
        this.red = red;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    //Random colour in Rand util.

    // constant material colours
    public static Colour MATERIAL_RED() {
        // hex code: e74c3c
        return new Colour(0.90588f, 0.29804f, 0.23529f );
    }

    public static Colour MATERIAL_GREEN() {
        // hex code: 2ECC71
        return new Colour( 0.18039f, 0.80000f, 0.44314f );
    }

    public static Colour MATERIAL_BLUE() {
        // hex code: 3498DB
        return new Colour( 0.20392f, 0.59608f, 0.85882f );
    }

    public static Colour MATERIAL_WHITE() {
        // hex code: ECF0F1
        return new Colour( 0.92549f, 0.94118f, 0.94510f, 1.0f);
    }

    public static void setColourRGBA(Colour colour, GL2 gl) {
        gl.glColor4f(colour.red, colour.green, colour.blue, colour.alpha);
    }

    public static void setDynamicColourRGBA(Colour colour, float transparency, GL2 gl) {
        gl.glColor4f(colour.red, colour.green, colour.blue, transparency);
    }

}
