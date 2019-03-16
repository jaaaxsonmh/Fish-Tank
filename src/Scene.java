import Managers.BubbleManager;
import Managers.ButtonEnum;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import objects.Fish;
import objects.Pump;
import objects.Tank;
import Models.Button;
import ulits.Colour;
import ulits.Rand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * Draws a line based on x,y coordinates stored in an array
 *
 * @author jwhalley
 */
public class Scene implements GLEventListener, MouseListener{
    private static final int BUTTONS_SIZE = 2;

    private boolean enabled =  true;

    private Tank tank = new Tank();
    private BubbleManager bub = new BubbleManager();
    private Pump pump = new Pump();

    private ArrayList<Fish> fishs = new ArrayList<Fish>();
    private Button[] buttons =  new Button[BUTTONS_SIZE];
    private int base;
    private static int winSize;

    private Scene() {
        ButtonEnum.init();
        for(int i = 0; i < BUTTONS_SIZE; i++) {
            buttons[i] = new Button(0.35f, 0.065f);
        }
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL2.GL_BLEND);

        // draw the tank environment
        tank.draw(gl);

        //draw pump from list
        gl.glCallList(pump.baseList);
        gl.glCallList(pump.holderList);
        gl.glCallList(pump.plugList);

        //Draw buttons, increase the location so that they dont display ontop of eachother.
        // set up enabled listener to change the colour of buttons.
        for(int i = 0; i < BUTTONS_SIZE; i++) {
            Colour.setColourRGBA(Button.BLACK, gl);
            
            if(buttons[i].isEnabled()) {
                // change colour of button to green
                Colour.setColourRGBA(Button.GREEN, gl);
            } else {
                Colour.setColourRGBA(Button.BLACK, gl);
            }

            //x: -0.95 base and then increase the buttons by index so they are spaced out
            //y: have y point of 0.915
            buttons[i].draw(gl, -0.95f+(i*(buttons[i].getWidth()+0.02f)), 0.915f);
            
            //Add the text to the buttons and change values so its 'kinda' center
            buttons[i].addTitle(gl, ButtonEnum.Title.get(i), -0.86f+(i*0.34f), 0.930f);
        }

        for(Fish fish : fishs) {
            fish.draw(gl);
        }

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
        gl.glShadeModel(GL2.GL_SMOOTH);
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

        for(int i = 0; i < BUTTONS_SIZE; i++) {
            if (openglX >= -0.95 + (i*(buttons[i].getWidth()+0.05f))
                    && openglX <= -0.95 + i*buttons[i].getWidth()+ buttons[i].getWidth()
                    && openglY >= 0.915f && openglY <= 0.915f +buttons[i].getHeight()
                    ) {
                buttons[i].onClick();
                if(i == ButtonEnum.FISH.ID)
                {
                    Fish fish = new Fish(Rand.getFloatBetween(-1.0f, 1.0f), Rand.getFloatBetween(-1.0f, 0.70f));
                    fishs.add(fish);
                }
                if(buttons[i].isEnabled()) {
                    if(i == ButtonEnum.BUBBLES.ID) {
                        bub.setEnabled(true);
                    }
                }
                if(!buttons[i].isEnabled()){
                    if(i == ButtonEnum.BUBBLES.ID) {
                        bub.setEnabled(false);
                    }
                }
            }
        }

        if(openglY <= 0.75f) {
            System.out.println("CLICKED:" + openglX + ", " + openglY);
            Fish fish = new Fish(openglX, openglY);
            fishs.add(fish);
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