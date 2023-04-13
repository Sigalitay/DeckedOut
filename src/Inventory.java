import java.awt.*;
import java.util.HashMap;

public class Inventory {

    HashMap<Item, Integer> amount = new HashMap<>();
    int margin = 50;

    int currScreen = 0;//STATS, MAP, ARMORY



    public void addItem(Item x) {
        //two ways of getting items which should be coded elsewhere but
        //shop or from a chest //bosses can be from chests
        //OR from a mob drop // mb mobs should only drop coins
        amount.put(x, amount.getOrDefault(amount, 0) + 1);
    }

    public void drawSelf(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        // g.setColor(Color.LIGHT_GRAY);//testing the hitbox

        if (currScreen == 0) {
            g.setColor(Color.LIGHT_GRAY);
        } else if (currScreen == 1) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
        //g2d.drawImage(characterDown, x, y, diam, diam, null);
    }

    public void navInv(int key) {
        if (key == 65 || key == 37)
            currScreen--;
        else if (key == 68 || key == 39)
            currScreen++;
        currScreen = Math.abs(currScreen % 3);
        //System.out.println(currScreen);
    }

}
