import java.awt.*;

public class BackgroundSprite {
    int x, y;
    int height, width;

    public BackgroundSprite(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        height = h;
        width = w;
    }


    public void drawSelf(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public void updateVert(int y)
    {
        this.y +=  y;
    }
    public void updateHori(int x)
    {
        this.x += x;
    }
}

