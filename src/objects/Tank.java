package objects;

import Models.Sand.SandBase;
import com.jogamp.opengl.GL2;
import Models.Water;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Tank {

    private Water water;
    private SandBase sand;

    public Tank() {
        super();
        water = new Water();
        sand = new SandBase();
    }


    public void draw(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        sand.draw(gl);

        //Still water
        water.draw(gl);

        //Wave water
        gl.glEnable(GL2.GL_POLYGON_SMOOTH);
        water.animate(gl);
        gl.glDisable(GL2.GL_POLYGON_SMOOTH);
    }
}
