import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import objects.Tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Draws a line based on x,y coordinates stored in an array
 * @author jwhalley
 *
 */
public class Scene implements GLEventListener {

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        Tank tank = new Tank();
        tank.draw(gl);

        gl.glFlush();



    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glBlendFunc(770, 771);
      gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    public static void main(String[] args) {
        Frame frame = new Frame("Jack's Fish(y) Tank");
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        Scene scene = new Scene();
        canvas.addGLEventListener(scene);
        frame.add(canvas);
        frame.setSize(640, 640);

        final Animator animator = new Animator(canvas);

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