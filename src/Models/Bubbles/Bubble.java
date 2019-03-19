package Models.Bubbles;

import com.jogamp.opengl.GL2;
import ulits.Circle;
import ulits.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Bubble extends Circle {

    private Colour colour;
    private final float AGE;
    public float offsetX;
    public float offsetY;

    public Bubble(float radius, float offsetX, float offsetY, float age, Colour colour, float transparency) {
        super(transparency, radius);
        this.colour = colour;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.AGE = age;
    }

    public void draw(GL2 gl){
        super.draw(gl, offsetX, offsetY, colour, colour, transparency);
    }

    public void animate(GL2 gl){
        gl.glBegin(GL2.GL_TRIANGLE_FAN);

        this.offsetY += AGE;

        if(transparency > 0) {
            this.transparency -= AGE/2;
        } else {
            transparency = 0;
        }

        // lower divisor the faster the radius decrease.
        if(radius > 0) {
            this.radius -= AGE/30;
        } else {
            radius = 0;
        }

        gl.glEnd();
    }
}
