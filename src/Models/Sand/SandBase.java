package Models.Sand;

import com.jogamp.opengl.GL2;
import ulits.Colour;


public class SandBase {

    final static float BASE_HEIGHT = -0.80f;

    private final static Colour YELLOW_100 = new Colour( 1.00000f, 0.97647f, 0.76863f, 0.0f);
    private final static Colour YELLOW_400 = new Colour(1.00000f, 0.93333f, 0.34510f);

    private final static Colour AMBER_500 = new Colour(1.00000f, 0.87843f, 0.50980f  );
    private final static Colour AMBER_200 = new Colour(1.00000f, 0.75686f, 0.02745f, 0.0f);

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_POLYGON);

        // top left
        Colour.setColourRGBA(YELLOW_100, gl);
        gl.glVertex2f(-1.0f, BASE_HEIGHT);

        //top right
        Colour.setColourRGBA(AMBER_200, gl);
        gl.glVertex2f(1.0f, BASE_HEIGHT);

        //bottom right
        Colour.setColourRGBA(AMBER_500, gl);
        gl.glVertex2f(1.0f, -1.0f);

        //bottom left
        Colour.setColourRGBA(YELLOW_400, gl);
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glDisable(GL2.GL_BLEND);

        gl.glEnd();
    }
}
