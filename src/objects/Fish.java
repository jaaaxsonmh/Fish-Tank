package objects;

import com.jogamp.opengl.GL2;
import ulits.*;

public class Fish {
    private static final Colour ORANGE = new Colour(0.937f, 0.424f, 0.0f);
    private static final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);
    private static final Colour BLACK = new Colour(0.0f, 0.0f, 0.0f);

    public float x;
    public float y;
    private float vx = Rand.getFloatBetween(-0.01f, 0.01f);
    private float vy = Rand.getFloatBetween(-0.01f, 0.01f);
    private float offset02 = 0.02f;
    private float offset025 = 0.025f;

    public float xRadius, yRadius, finMovement;
    private Fins rightFin, leftFin;
    private Oval body;
    private Circle rightEye, leftEye, rightPupil, leftEyePupil, leftEyeShadow, rightEyeShadow, innerMouth;
    private int finMovementCount = 0;


    public Fish(float x, float y) {
        this.x = x;
        this.y = y;
        this.xRadius = 0.06f;
        this.yRadius = 0.02f;
        finMovement = 0.002f;
        drawEyes();
        drawFins();
        drawBody();
        drawMouth();
    }

    public void drawFins() {

        rightFin = new Fins(xRadius, y + yRadius, y - yRadius, WHITE, ORANGE);
        leftFin = new Fins(xRadius, y + yRadius, y - yRadius, WHITE, ORANGE);

    }

    public void drawEyes() {
        rightEyeShadow = new Circle(1.0f, 0.026f);
        rightEye = new Circle(1.0f, 0.024f);
        rightPupil = new Circle(1.0f, 0.006f);

        leftEyeShadow = new Circle(1.0f, 0.022f);
        leftEye = new Circle(1.0f, 0.02f);
        leftEyePupil = new Circle(1.0f, 0.004f);
    }

    public void drawBody() {
        body = new Oval(1.0f, 0.04f, xRadius);
    }

    public void drawMouth() {
        innerMouth = new Circle(1.0f, 0.013f);
    }

    public void draw(GL2 gl) {
        rightFin.drawRight(gl, x, y);
        leftFin.drawLeft(gl, x, y);
        rightFin.moveYBC(finMovement, vy);
        leftFin.moveYBC(finMovement, vy);

        finMovementCount++;
        if (finMovementCount > 5) {
            finMovement *= -1;
            finMovementCount = 0;
        }

        body.draw(gl, x, y, ORANGE, WHITE, 1.0f);

        innerMouth.draw(gl, x- offset02, y - offset025, WHITE, WHITE, 1.0f);

        rightEyeShadow.draw(gl, x + offset02, y + offset02, BLACK, BLACK, 1.0f);
        rightEye.draw(gl, x + offset02, y + offset02, WHITE, WHITE, 1.0f);
        rightPupil.draw(gl, x + offset02, y + offset02, BLACK, BLACK, 1.0f);

        leftEyeShadow.draw(gl, x - offset025, y + offset02, BLACK, BLACK, 1.0f);
        leftEye.draw(gl, x - offset025, y + offset02, WHITE, WHITE, 1.0f);
        leftEyePupil.draw(gl, x - offset025, y + offset02, BLACK, BLACK, 1.0f);
    }

    public void animate() {
        x += vx;
        y += vy;

        if (x > 0.95f) {
            x = 0.95f;
            vx = -vx;
        } else if (x < -0.95f) {
            x = -0.95f;
            vx = -vx;
        }

        if (y > 0.75f) {
            y = 0.75f;
            vy = -vy;
        } else if (y < -0.95f) {
            y = -0.95f;
            vy = -vy;
        }
    }
}
