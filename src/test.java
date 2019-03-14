package jogl_basics;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;


/**
 * Draws a quad and a triangle each pre-compiled in a display list
 * @author jwhalley
 */

public class test implements GLEventListener {

    private int base;
    private int quadDList;
    private int triDList;;


    private void createQuadDList(GL2 gl, int DLindex){
        quadDList = DLindex;
        //pre-compile the commands in the list
        gl.glNewList(quadDList, GL2.GL_COMPILE);
        gl.glColor3d(1, 1, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2d(0.3, 0.3);
        gl.glVertex2d(0.5, 0.3);
        gl.glVertex2d(0.5, 0.5);
        gl.glVertex2d(0.3, 0.5);
        gl.glEnd();
        gl.glEndList();
    }

    private void createTriDList(GL2 gl, int DLindex){
        triDList = DLindex;
        //pre-compile the commands in the list
        gl.glNewList(triDList, GL2.GL_COMPILE);
        gl.glColor3d(1, 0, 1);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2d(-0.5, -0.1);
        gl.glVertex2d(-0.25, -0.6);
        gl.glVertex2d(-0.75, -0.6);
        gl.glEnd();
        gl.glEndList();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        //clear the drawing canvas
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glCallList(quadDList); // draw the quad
        gl.glCallList(triDList); // draw the triangle

        gl.glFlush();

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Builds two lists, and returns handle for the first list
        base = gl.glGenLists(2);

        //create and pre-compile the display lists
        createQuadDList(gl,base);
        createTriDList(gl,base+1);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        Frame frame = new Frame("Two display lists demo");
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        test sdl = new test();
        canvas.addGLEventListener(sdl);
        frame.add(canvas);
        frame.setSize(640, 480);

        final Animator animator = new Animator(canvas);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();
    }

}