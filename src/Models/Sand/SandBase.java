package Models.Sand;

import com.jogamp.opengl.GL2;
import ulits.Colour;


public class SandBase {

    final static float BASE_HEIGHT = -0.80f;


    private final static Colour BROWN_300 = new Colour( 0.63137f, 0.53333f, 0.49804f );
    private final static Colour BROWN_500 = new Colour(0.47451f, 0.33333f, 0.28235f );

    private final static Colour BROWN_700 = new Colour(0.36471f, 0.25098f, 0.21569f );
    private final static Colour BROWN_900 = new Colour(0.24314f, 0.15294f, 0.13725f );

    public void draw(GL2 gl) {
        gl.glEnable(GL2.GL_BLEND);
        gl.glBegin(GL2.GL_POLYGON);

        // top left
        Colour.setColourRGBA(BROWN_300, gl);
        gl.glVertex2f(-1.0f, BASE_HEIGHT);


        //top right
        Colour.setColourRGBA(BROWN_900, gl);
        gl.glVertex2f(1.0f, BASE_HEIGHT);

        //bottom right
        Colour.setColourRGBA(BROWN_700, gl);
        gl.glVertex2f(1.0f, -1.0f);


        //bottom left
        Colour.setColourRGBA(BROWN_900, gl);
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glDisable(GL2.GL_BLEND);


        gl.glEnd();
    }
}
