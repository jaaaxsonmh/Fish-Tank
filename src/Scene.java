import Models.Bubble;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import objects.Tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Draws a line based on x,y coordinates stored in an array
 * @author jwhalley
 *
 */
public class Scene implements GLEventListener, Runnable {

    private Tank tank = new Tank();
    private Queue<Bubble> bubbles;

    private static final int BUBBLE_AMOUNT = 40;
    private boolean enabled = true;

    private Scene() {
        super();
        bubbles = new ConcurrentLinkedQueue<>();
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL2.GL_BLEND);

        // draw the tank
        // sand and water.
        tank.draw(gl);

        // draw bubbles
        if(enabled) {
            for(Bubble bub : bubbles) {
                bub.draw(gl);
            }
        }

        float transparency = 1.0f;
        float radius = 0.05f;
        float offsetX = 0.0f;
        float offsetY = -0.0f;
        float age = 0.01f;

        Bubble bubble = new Bubble(radius, offsetX, offsetY, age, transparency);
        bubble.draw(gl);

        gl.glEnd();
        gl.glFlush();
    }



    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    @Override
    public void run() {
        while(enabled) {
            if(bubbles.size() < BUBBLE_AMOUNT) {
                float transparency = 1.0f;
                float radius = 0.05f;
                float offsetX = 0.0f;
                float offsetY = -0.0f;
                float age = 0.01f;

                bubbles.add(new Bubble(radius, offsetX, offsetY, age, transparency));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Jack's Fish(y) Tank");
        frame.setResizable(false);
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        Scene scene = new Scene();

        // add event listeners


        canvas.addGLEventListener(scene);
        frame.add(canvas);
        frame.setSize(640, 640);

        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(() -> {
                    animator.stop();
                    System.exit(0);
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();

    }


}