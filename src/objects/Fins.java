package objects;

import com.jogamp.opengl.GL2;
import ulits.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */


class Fins {
    private Colour innerColour, outerColour;
    private float pointBY, pointCY;
    private float xRadius;


    Fins(float xRadius, float pointBY, float pointCY, Colour innerColour, Colour outerColour) {
        this.innerColour = innerColour;
        this.outerColour = outerColour;

        this.xRadius = xRadius;
        this.pointBY = pointBY;
        this.pointCY = pointCY;
    }

    // move the points from B-C and A stays still.
    void moveYBC(float distance, float y) {
        pointBY -= distance - y;
        pointCY -= distance - y;
    }


    void drawRight(GL2 gl, float x, float y) {
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        Colour.setColourRGBA(innerColour, gl);
        gl.glVertex2f(x, y);
        Colour.setColourRGBA(outerColour, gl);

        gl.glVertex2f(x + xRadius, pointBY);
        gl.glVertex2f(x + xRadius, pointCY);
        gl.glEnd();
    }

    void drawLeft(GL2 gl, float x, float y) {
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        Colour.setColourRGBA(innerColour, gl);
        gl.glVertex2f(x, y);
        Colour.setColourRGBA(outerColour, gl);
        gl.glVertex2f(x - xRadius, pointBY);
        gl.glVertex2f(x - xRadius, pointCY);
        gl.glEnd();
    }

    void drawTop(GL2 gl, float x, float y) {
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        Colour.setColourRGBA(innerColour, gl);
        gl.glVertex2f(x, y);
        Colour.setColourRGBA(outerColour, gl);
        gl.glVertex2f(x + xRadius, pointBY);
        gl.glVertex2f(x - xRadius, pointCY);
        gl.glEnd();
    }
}
