import java.awt.*;

public class Building extends BackgroundSprite {

    Rectangle doorway;
    int doorWayX, doorWayY;
    int id;


    public Building(int x, int y, int width, int height, int doorHeight, int doorWidth, int doorX, int doorY, int id)
    {
        super(x,y,width, height);
        doorway = new Rectangle(doorX + x, doorY + y, doorWidth, doorHeight);
        doorWayX = doorX;
        doorWayY = doorY;
        this.id = id;
    }

    @Override
    public void drawSelf(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        //actual building
        g.setColor(Color.DARK_GRAY);//testing the hitbox
        g.fillRect(x, y, width, height);

        //doorway
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(doorWayX + x, doorWayY + y, doorway.width, doorway.height);
        //g2d.drawImage(characterDown, x, y, diam, diam, null);
    }



}
