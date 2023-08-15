import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Sprite{

    protected int id;
    private Attack attack;//make into a list in the future
    //lol public enemy #1
    public Enemy(int x, int y, int id)
    {
        super(x, y);
        this.id = id;

        //in the future use the id to match the attack but for now basic attack
        attack = new Attack(20, new Cooldown(500));
    }

    public Attack getAttack(){
        return attack;
    }

    public void drawSelf(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        //String idIcon = idMap.get(id).get(0);//get the current ids image name

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "MiniPeasant.png")));
        Image image = icon.getImage();
        g2d.drawImage(image, x, y, diam, diam, null);
    }
}
