import Managers.BubbleManager;
import Models.Bubbles.Bubble;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import objects.Tank;
import ulits.Colour;
import ulits.Rand;

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
    private BubbleManager bub = new BubbleManager();
    private Queue<Bubble> bubbles;

    private static final int BUBBLE_AMOUNT = 5;
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

        bub.populate();
        bub.draw(gl);

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

        final FPSAnimator animator = new FPSAnimator(canvas, 30);
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