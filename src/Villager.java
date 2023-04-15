import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Villager extends NPC{

    HashMap<Integer, ArrayList<String>> idMap = new HashMap<>();
    Graphics g;
    boolean interaction = false;
    public Villager(int x, int y, int id)
    {
        super(x, y, id);
        {
            idMap.put(0, new ArrayList<>());
            idMap.get(0).add("MiniOldMan.png");//imageIcon
            idMap.get(0).add("Hello!");//script
        }
    }

    public void drawSelf(Graphics g) {
        if(this.g == null)
            this.g = g;
        Graphics2D g2d = (Graphics2D) g;
        String idIcon = idMap.get(id).get(0);//get the current ids image name

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + idIcon)));
        Image image = icon.getImage();
        g2d.drawImage(image, x, y, diam, diam, null);
        if(interaction)
        {
            Color c = new Color(10,10,10,20);
            g.setColor(Color.lightGray);//making a box for the text
            g.fillRect(50, 50, 775, 100);
        }

    }
    public void interact(){
        interaction = !interaction;
        Sprite.movementAllowed = !Sprite.movementAllowed;
    }

}
