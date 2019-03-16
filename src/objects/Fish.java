package objects;

import com.jogamp.opengl.GL2;
import ulits.Colour;
import ulits.Coords;
import ulits.Rand;

public class Fish {
    private static final Colour ORANGE = new Colour(0.937f, 0.424f, 0.0f);
    private Coords rightA, rightB, rightC, leftA, leftB, leftC;
    public float x;
    public float y;
    public float xRadius, yRadius, finMovement;
    private Fins rightFin, leftFin;
    private int finMovementCount = 0;


    public Fish(float x, float y) {
        this.x = x;
        this.y = y;
        this.xRadius = 0.02f;
        this.yRadius = 0.02f;
        finMovement = 0.002f;
        drawFins();
    }

    public void drawFins() {
        rightA = new Coords(x, y);
        rightB = new Coords(x + xRadius + (xRadius / 3), y + yRadius);
        rightC = new Coords(x + xRadius + (xRadius / 3), y - yRadius);
        rightFin = new Fins(rightA, rightB, rightC, ORANGE, 1.0f);

        leftA = new Coords(x, y);
        leftB = new Coords(x - xRadius - (xRadius / 3), y + yRadius);
        leftC = new Coords(x - xRadius - (xRadius / 3), y - yRadius);
        leftFin = new Fins(leftA, leftB, leftC, ORANGE, 1.0f);
    }

    public void draw(GL2 gl) {
        gl.glPointSize(5);
        gl.glBegin(GL2.GL_POINTS);
        gl.glColor3f(0.5f, 0.5f, 0.0f);
        gl.glVertex2f(x, y);
        gl.glEnd();

        rightFin.draw(gl);
        leftFin.draw(gl);

        rightFin.movePointsBC(finMovement);
        leftFin.movePointsBC(finMovement);
        finMovementCount++;
        if (finMovementCount > 5) {
            finMovement *= -1;
            finMovementCount = 0;
        }

    }
}
