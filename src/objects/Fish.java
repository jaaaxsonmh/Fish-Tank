package objects;

import com.jogamp.opengl.GL2;
import ulits.*;

public class Fish {
    private static final Colour ORANGE = new Colour(0.937f, 0.424f, 0.0f);
    private static final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);
    private static final Colour BLACK = new Colour(0.0f, 0.0f, 0.0f);


    private Coords rightA, rightB, rightC, leftA, leftB, leftC;
    public float x;
    public float y;
    private float vx = Rand.getFloatBetween(0.001f, 0.01f);
    private float vy = Rand.getFloatBetween(0.001f, 0.01f);
    private int width, height;

    public float xRadius, yRadius, finMovement;
    private Fins rightFin, leftFin;
    private Oval body;
    private Circle rightEye, leftEye, rightPupil, leftEyePupil, leftEyeShadow, rightEyeShadow;
    private int finMovementCount = 0;


    public Fish(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xRadius = 0.06f;
        this.yRadius = 0.02f;
        finMovement = 0.002f;
        drawEyes();
        drawFins();
        drawBody();
    }

    public void drawFins() {
        rightA = new Coords(x, y);
        rightB = new Coords(x + xRadius + (xRadius / 3), y + yRadius);
        rightC = new Coords(x + xRadius + (xRadius / 3), y - yRadius);

        leftA = new Coords(x, y);
        leftB = new Coords(x - xRadius - (xRadius / 3), y + yRadius);
        leftC = new Coords(x - xRadius - (xRadius / 3), y - yRadius);

        rightFin = new Fins(rightA, rightB, rightC, ORANGE, 1.0f);
        leftFin = new Fins(leftA, leftB, leftC, ORANGE, 1.0f);
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

    public void draw(GL2 gl) {

        rightFin.draw(gl);
        leftFin.draw(gl);

        rightFin.movePointsBC(finMovement);
        leftFin.movePointsBC(finMovement);

        finMovementCount++;
        if (finMovementCount > 5) {
            finMovement *= -1;
            finMovementCount = 0;
        }

        body.draw(gl, x, y, ORANGE, WHITE,  1.0f);


        rightEyeShadow.draw(gl, x + 0.02f, y, BLACK, 1.0f);
        rightEye.draw(gl, x +  0.02f, y, WHITE, 1.0f);
        rightPupil.draw(gl, x +  0.02f, y, ORANGE, 1.0f);

        leftEyeShadow.draw(gl, x - 0.025f, y, BLACK, 1.0f);
        leftEye.draw(gl, x - 0.025f, y, WHITE, 1.0f);
        leftEyePupil.draw(gl, x -  0.025f, y, ORANGE, 1.0f);
    }

    public void animate() {
        x += vx;
        y += vy;

        if(x > 0.95f){
            x = 0.95f;
            vx = -vx;
        } else if( x < -0.95f) {
            x = -0.95f;
            vx = -vx;
        }

        if(y > 0.75f) {
            y = 0.75f;
            vy = -vy;
        } else if (y < -0.95f) {
            y = -0.95f;
            vy = -vy;
        }
    }
}
