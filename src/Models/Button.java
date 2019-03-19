package Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import ulits.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */


public class Button {
    public static final Colour GREEN = new Colour(0.392f, 0.867f, 0.090f);
    public static final Colour BLACK = new Colour(0.373f, 0.382f, 0.347f);
    public static final Colour RED = new Colour(1.0f, 0.0f, 0.0f);
    private final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);

    private final static GLUT glut = new GLUT();
    private float WIDTH, HEIGHT;
    private boolean enabled;

    public Button(float width, float height) {
        super();
        WIDTH = width;
        HEIGHT = height;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void onClick() {
        enabled = !enabled;
    }


    public void draw(GL2 gl, float x, float y) {

        gl.glBegin(GL2.GL_POLYGON);
        //top left
        gl.glVertex2f(x, y);
        //top right
        gl.glVertex2f(x + WIDTH, y);
        // bottom right
        gl.glVertex2f(x + WIDTH, y + HEIGHT);
        // bottom left
        gl.glVertex2f(x, y + HEIGHT);
        gl.glEnd();
    }

    public void addTitle(GL2 gl, String title, float x, float y) {
        Colour.setColourRGBA(WHITE, gl);
        gl.glRasterPos2d(x, y);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, title);
    }


    public float getWidth() {
        return WIDTH;
    }

    public float getHeight() {
        return HEIGHT;
    }
}
