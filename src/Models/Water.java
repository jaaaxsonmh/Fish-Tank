package Models;


import com.jogamp.opengl.GL2;
import ulits.Colour;

public class Water {

    private final static Colour BLUE_800 = new Colour( 0.25882f, 0.64706f, 0.96078f , 0.6f);
    private final static Colour LIGHT_BLUE_800 = new Colour(0.16078f, 0.71373f, 0.96471f, 0.6f );

    private final static Colour BLUE_900 = new Colour(0.05098f, 0.27843f, 0.63137f, 0.6f);
    private final static Colour LIGHT_BLUE_900 = new Colour(0.00392f, 0.34118f, 0.60784f, 0.6f);

    private final static float WAVE_WATER_HEIGHT = 0.05f;
    private final static float STILL_WATER_HEIGHT = 0.90f - WAVE_WATER_HEIGHT;

    public void draw(GL2 gl) {

        gl.glEnable(GL2.GL_BLEND);
        gl.glBegin(GL2.GL_POLYGON);

        // top left
        Colour.setColourRGBA(BLUE_800, gl);
        gl.glVertex2f(-1.0f, STILL_WATER_HEIGHT);


        //top right
        Colour.setColourRGBA(LIGHT_BLUE_800, gl);
        gl.glVertex2f(1.0f, STILL_WATER_HEIGHT);

        //bottom right
        Colour.setColourRGBA(BLUE_900, gl);
        gl.glVertex2f(1.0f, -1.0f);

        //bottom left
        Colour.setColourRGBA(LIGHT_BLUE_900, gl);
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glDisable(GL2.GL_BLEND);
        gl.glEnd();

    }
}
