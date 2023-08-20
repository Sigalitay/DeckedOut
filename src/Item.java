import javax.swing.*;
import java.awt.*;

public class Item {
    ImageIcon img;
    int diam = 10;

    public Item(String item){
        // img = <set up folder where all item sprites go>;
    }

    public void drawSelf(Graphics g, int x, int y)
    {//for testing purposes
        g.drawOval(x,y, diam, diam);
    }
}
