package objects;

import com.jogamp.opengl.GL2;
import ulits.Colour;
import ulits.Coords;


public class Fins {
    private Coords pA, pB, pC;
    private Colour colour;
    private float transparency;


    Fins(Coords a, Coords b, Coords c, Colour colour, float transparency) {
        this.pA = a;
        this.pB = b;
        this.pC = c;
        this.colour = colour;
        this.transparency = transparency;
    }

    // move the points from B-C and A stays still.
    public void movePointsBC(float distance) {

            pB.y -= distance;
            pC.y -= distance;
    }

    void draw(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        Colour.setColourRGBA(colour, gl);
        gl.glVertex2f(pA.x, pA.y);
        gl.glVertex2f(pB.x, pB.y);
        gl.glVertex2f(pC.x, pC.y);
        gl.glEnd();
    }
}
