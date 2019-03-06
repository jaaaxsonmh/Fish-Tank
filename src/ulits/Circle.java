package ulits;

import com.jogamp.opengl.GL2;

import java.util.Arrays;
import java.util.List;


public class Circle {
    private float radius;
    public float transparency;
    private Colour inner, outer;

    public Circle(float transparency, float radius, Colour inner, Colour outer) {
        this.transparency = transparency;
        this.radius = radius;
        this.inner = inner;
        this.outer = outer;

    }

    public Circle(float transparency, float radius, Colour inner) {
        // recursively call without an outer colour (solid fill circle)
        // used with fish eyes
        this(transparency, radius, inner, inner);
    }

   public void draw(GL2 gl, float offsetX, float offsetY) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        // draw inner point with inner colour
        Colour.setColourRGBA(inner, gl);
        gl.glVertex2f(offsetX, offsetY);

        // draw the outer points with outer colour
        Colour.setColourRGBA(outer, gl);
        for (int j = 0; j <= 361; j++) {
            double angle = 2 * Math.PI * j / 361;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            gl.glVertex2d(x + offsetX, y + offsetY);
        }
        gl.glEnd();
    }
}
