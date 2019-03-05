package objects;

import Models.Sand.SandBase;
import Models.Sand.SandStones;
import com.jogamp.opengl.GL2;
import Models.Water;

import static com.jogamp.opengl.GL2GL3.GL_POLYGON_SMOOTH;

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
        water.drawWave(gl);
        gl.glDisable(GL2.GL_POLYGON_SMOOTH);

//        SandStones pebbles = new SandStones();
//
//        // 3000 pebbles
//        for(int i =0; i <= 3000; i++) {
//            pebbles.draw(gl);
//        }
    }
}
