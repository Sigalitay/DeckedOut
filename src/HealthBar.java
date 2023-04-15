import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HealthBar {
    int currHealth;
    int totalHealth;
    public HealthBar(int health)
    {
         currHealth = health;
         totalHealth = health;
    }
    public HealthBar(int health, int missingHealth)
    {
        currHealth = health - missingHealth;
        totalHealth = health;
    }

    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //health bar
        ImageIcon redIcon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "RedBar.png")));
        Image redImage = redIcon.getImage();
        ImageIcon emptyIcon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "EmptyBar.png")));
        Image emptyImage = emptyIcon.getImage();
        g2d.drawImage(redImage, 12, 9, 10, totalHealth, null);
        g2d.drawImage(emptyImage, 8, 8, 20, totalHealth - currHealth, null);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "HealthBar.png")));
        Image image = icon.getImage();
        g2d.drawImage(image, 5, 5, 25, 110, null);
    }
}
