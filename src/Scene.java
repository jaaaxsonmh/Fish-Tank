import Managers.BubbleManager;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import objects.Pump;
import objects.Tank;
import Models.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Draws a line based on x,y coordinates stored in an array
 *
 * @author jwhalley
 */
public class Scene implements GLEventListener, MouseListener{
    private static final int BUTTONS_SIZE = 2;
    private boolean enabled =  true;

    private Tank tank = new Tank();
    private Button button = new Button();
    private BubbleManager bub = new BubbleManager();
    private Pump pump = new Pump();
    private int base;
    private static int winSize;

    private Button[] buttons;



    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL2.GL_BLEND);

        // draw the tank
        // sand and water.
        tank.draw(gl);

        button.draw(gl);
        button.addTitle(gl);

        //draw pump from list
        gl.glCallList(pump.baseList);
        gl.glCallList(pump.holderList);
        gl.glCallList(pump.plugList);

        bub.draw(gl);

        gl.glFlush();
    }


    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glEnable(GL2.GL_BLEND);

        base = gl.glGenLists(3);

        //create and pre-compile the display lists
        pump.createBaseList(gl, base);
        pump.createHolderList(gl, base + 1);
        pump.createPlugList(gl, base + 2);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }




    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        float mouseX = e.getX();
        float mouseY = winSize - e.getY();

        float openglX = 2.0f * (mouseX / winSize) - 1.0f;
        float openglY = 2.0f * (mouseY / winSize) - 1.0f;

        if (openglY >= 0.80f) {
            if(enabled) {
                enabled = false;
            } else {
                enabled = true;
            }
            bub.setEnabled(enabled);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        winSize = (int) screenSize.getWidth() / 2;

        Frame frame = new Frame("Jack's Fish(y) Tank");
        frame.setResizable(false);
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        Scene scene = new Scene();

        // add event listeners
        canvas.addGLEventListener(scene);
        canvas.addMouseListener(scene);

        frame.add(canvas);
        frame.setSize(winSize, winSize);

        final FPSAnimator animator = new FPSAnimator(canvas, 30);
        animator.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
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