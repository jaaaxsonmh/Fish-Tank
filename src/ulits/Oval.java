package ulits;

import com.jogamp.opengl.GL2;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */


public class Oval {
    private float xRadius, yRadius;
    private float transparency;


    public Oval(float transparency, float xRadius, float yRadius) {
        this.transparency = transparency;
        this.xRadius = xRadius;
        this.yRadius = yRadius;

    }

    public void draw(GL2 gl, float offsetX, float offsetY, Colour outerColour, Colour innerColour, float transparency) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        Colour.setDynamicColourRGBA(innerColour, transparency, gl);
        gl.glVertex2f(offsetX, offsetY);

        for (int j = 0; j <= 361; j++) {
            double angle = 2 * Math.PI * j / 361;
            double x = Math.cos(angle) * xRadius;
            double y = Math.sin(angle) * yRadius;
            Colour.setDynamicColourRGBA(outerColour, transparency, gl);
            gl.glVertex2d(x + offsetX, y + offsetY);
        }
        gl.glEnd();
    }
}
