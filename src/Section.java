import javax.swing.ImageIcon;
import java.awt.*;

public class Section extends Box{

    private ImageIcon icon;
    public Section(int x, int y, int w, ImageIcon imgCon)
    {
        super(x,y,w);
        this.icon = imgCon;
    }

    @Override
    public void drawSelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image img = icon.getImage();
        int repeat = width / height;
        int currX = 0;
        while(repeat != 0)
        {
            g2d.drawImage(img, x + currX, y, height, height, null);
            currX+=height;
            repeat--;
        }
    }
}
