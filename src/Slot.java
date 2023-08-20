import java.awt.*;

public class Slot {
    Item item;
    int x;
    int y;
    int diam;

    public Slot(Item item, int x, int y, int diam)
    {
        this.item = item;
        this.x = x;
        this.y = y;
        this.diam = diam;
    }

    public Slot(int x, int y, int diam)
    {

        this.x = x;
        this.y = y;
        this.diam = diam;
        this.item = null;
    }

    public void drawSelf(Graphics g)
    {//for testing purposes
        g.setColor(Color.blue);
        g.drawOval(x,y, diam, diam);
        if(item != null)
            item.drawSelf(g,x+diam/2-item.diam/2, y + diam/2-item.diam/2);
    }

}
