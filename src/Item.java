import javax.swing.*;
import java.awt.*;

public class Item {
    ImageIcon img;

    public Item(String item){
        // img = <set up folder where all item sprites go>;
    }

    public void drawSelf(Graphics g, int x, int y)
    {//for testing purposes
        g.drawOval(x,y, 10, 10);
    }
}
