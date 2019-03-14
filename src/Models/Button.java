package Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import ulits.Colour;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button {
    private final Colour GREEN = new Colour(0.392f, 0.867f, 0.090f);
    private final Colour BLACK = new Colour(0.373f, 0.382f, 0.347f);
    private final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);

    private final static GLUT glut = new GLUT();
    private boolean enabled;

    private boolean isEnabled() {
        return enabled;
    }


    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_POLYGON);
        if(isEnabled()) {
        } else {
            Colour.setColourRGBA(BLACK, gl);
            Colour.setColourRGBA(GREEN, gl);

        }
        //top left
        gl.glVertex2f(-0.95f, 0.95f);
        //top right
        gl.glVertex2f(-0.65f, 0.95f);
        // bottom right
        gl.glVertex2f(-0.65f, 0.85f);
        // bottom left
        gl.glVertex2f(-0.95f, 0.85f);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        if(isEnabled()) {
        } else {
            Colour.setColourRGBA(BLACK, gl);
            Colour.setColourRGBA(GREEN, gl);

        }
        //top left
        gl.glVertex2f(-0.55f, 0.95f);
        //top right
        gl.glVertex2f(-0.25f, 0.95f);
        // bottom right
        gl.glVertex2f(-0.25f, 0.85f);
        // bottom left
        gl.glVertex2f(-0.55f, 0.85f);
        gl.glEnd();
    }

    public void addTitle(GL2 gl) {
        Colour.setColourRGBA(WHITE, gl);
        gl.glRasterPos2d(-0.93, 0.87f);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Bubbles");
    }


}
