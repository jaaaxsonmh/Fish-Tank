package Models;

import com.jogamp.opengl.GL2;
import ulits.Circle;
import ulits.Colour;

public class Bubble extends Circle {

    private static final Colour  BLUE_800 = new Colour(0.25882f, 0.64706f, 0.96078f);
    private static final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);

    private final float AGE;
    private float offsetX, offsetY;

    public Bubble(float radius, float offsetX, float offsetY, float age, float transparency) {
        super(transparency, radius, WHITE, BLUE_800);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.AGE = age;
    }

    public void draw(GL2 gl){
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        super.draw(gl, offsetX, offsetY);
        offsetY += AGE;

        if(transparency > 0) {
            transparency -= AGE/2;
        }
    }

}
