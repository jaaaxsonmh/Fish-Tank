package ulits;

import com.jogamp.opengl.GL2;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */


public class Circle {
    public float radius;
    public float transparency;


    public Circle(float transparency, float radius) {
        this.transparency = transparency;
        this.radius = radius;

    }

    public void draw(GL2 gl, float offsetX, float offsetY, Colour outerColour, Colour innerColour, float transparency) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        // draw inner point with inner colour
        Colour.setDynamicColourRGBA(innerColour, transparency, gl);
        gl.glVertex2f(offsetX, offsetY);


        for (int j = 0; j <= 361; j++) {
            double angle = 2 * Math.PI * j / 361;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            Colour.setDynamicColourRGBA(outerColour, transparency, gl);
            gl.glVertex2d(x + offsetX, y + offsetY);
        }
        gl.glEnd();
    }
}
