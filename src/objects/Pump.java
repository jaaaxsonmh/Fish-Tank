package objects;

import com.jogamp.opengl.GL2;
import ulits.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Pump {

    private final static Colour WHITE_600 = new Colour(1.0f, 0.992f, 0.906f);
    private final static Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);
    private final static Colour BLACK_800 = new Colour(0.016f, 0.141f, 0.141f);

    public int baseList, holderList, plugList;


    public void createBaseList(GL2 gl, int DLindex) {
        baseList = DLindex;
        //pre-compile the commands in the list
        gl.glNewList(baseList, GL2.GL_COMPILE); // deleted this line
        gl.glBegin(GL2.GL_QUADS);

        Colour.setColourRGBA(WHITE, gl);

        // top left corner
        gl.glVertex2f(0.65f, -0.75f);
        //top right corner
        gl.glVertex2f(0.95f, -0.75f);

        Colour.setColourRGBA(WHITE_600, gl);

        //bottom right corner
        gl.glVertex2f(0.95f, -0.85f);
        //bottom left corner
        gl.glVertex2f(0.65f, -0.85f);

        gl.glEnd();
        gl.glEndList();
    }

    public void createHolderList(GL2 gl, int DLindex) {
        holderList = DLindex;
        //pre-compile the commands in the list
        gl.glNewList(holderList, GL2.GL_COMPILE);
        gl.glBegin(GL2.GL_QUADS);

        //First holder tie
        Colour.setColourRGBA(WHITE, gl);
        //top left corner
        gl.glVertex2f(0.70f, -0.74f);
        //top right corner
        gl.glVertex2f(0.73f, -0.74f);

        Colour.setColourRGBA(BLACK_800, gl);
        //bottom right corner
        gl.glVertex2f(0.73f, -0.85f);
        //bottom left corner
        gl.glVertex2f(0.70f, -0.85f);

        //Second holder tie
        Colour.setColourRGBA(WHITE, gl);
        //top left
        gl.glVertex2f(0.87f, -0.74f);
        //top right
        gl.glVertex2f(0.90f, -0.74f);

        Colour.setColourRGBA(BLACK_800, gl);
        //bottom right
        gl.glVertex2f(0.90f, -0.85f);
        //bottom left
        gl.glVertex2f(0.87f, -0.85f);

        gl.glEnd();
        gl.glEndList();
    }

    public void createPlugList(GL2 gl, int DLindex) {
        plugList = DLindex;
        //pre-compile the commands in the list
        gl.glNewList(plugList, GL2.GL_COMPILE);
        gl.glBegin(GL2.GL_QUADS);
        Colour.setColourRGBA(BLACK_800, gl);
        gl.glVertex2f(0.95f, -0.79f);
        //top right
        Colour.setColourRGBA(WHITE, gl);
        gl.glVertex2f(1.0f, -0.79f);

        //bottom right
        Colour.setColourRGBA(BLACK_800, gl);
        gl.glVertex2f(1.0f, -0.81f);
        //bottom left
        Colour.setColourRGBA(WHITE, gl);
        gl.glVertex2f(0.95f, -0.81f);
        gl.glEnd();
        gl.glEndList();
    }
}
