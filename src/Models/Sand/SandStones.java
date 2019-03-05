package Models.Sand;

import com.jogamp.opengl.GL2;
import ulits.Colour;
import ulits.Rand;

import java.util.Arrays;
import java.util.List;


public class SandStones {

    private static float RADIUS = 0.005f;

    final private static List<Colour> SAND_TEXTURE = Arrays.asList(
            new Colour(1.00000f, 0.87843f, 0.50980f),
            new Colour(1.00000f, 0.93333f, 0.34510f ),
            new Colour(1.00000f, 0.87843f, 0.50980f ),
            new Colour(1.00000f, 0.75686f, 0.02745f)
    );

    private static Colour getNextSandColour(){
        return SAND_TEXTURE.get(Rand.getIntBetween(0, SAND_TEXTURE.size()-1));
    }

    public void draw(GL2 gl) {

        float offset_x = Rand.getFloatBetween(1.0f, -1.0f);
        float offset_y = Rand.getFloatBetween(-1.0f, SandBase.BASE_HEIGHT - RADIUS);
        gl.glBegin(GL2.GL_LINE_LOOP);
        Colour.setColourRGBA(getNextSandColour(), gl);
        for (int j = 0; j <= 300; j++) {
            double angle = 2 * Math.PI * j / 300;
            double x = Math.cos(angle) * RADIUS;
            double y = Math.sin(angle) * RADIUS;
            gl.glVertex2d(x + offset_x, y + offset_y);
        }
        gl.glEnd();
    }
}
