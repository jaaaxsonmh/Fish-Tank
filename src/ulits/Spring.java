package ulits;

public class Spring {

    public float xPos, yPos;

    public int yFix;
    public float speed;
    private final float k = 0.03f;
    private final float fric = 0.05f;

    public Spring(float x, int y) {
        xPos = x;
        yPos = y;
        yFix = y;

        speed = 0;
    }

    public void refresh() {
        float y = yFix - yPos;

        y *= k;

        if(speed > 0){
            y -= fric;
        }else {
            y += fric;
        }

        speed += y;

        yPos += speed;
    }
}
