
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Background {
    int height;
    int width;
    int x, y;
    HashMap<Integer, ArrayList<BackgroundSprite>> backgrounds = new HashMap<>();
    HashMap<Integer, ImageIcon> maps = new HashMap<>();
    HashMap<Integer, ArrayList<NPC>> npcs = new HashMap<>();
    int currScreen = 0;


    public Background(int width, int height) {
        this.height = height;
        this.width = width;
        x = 0;
        y = -Driver.HEIGHT;
        initialize();
    }

    public void drawSelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image img = maps.get(currScreen).getImage();
        g2d.drawImage(img, x, y, width, height, null);
    }

    public boolean inBoundsVert(int velY, int playerY) {

        if (y + height + velY <= Driver.HEIGHT)//if  youre about to go down outside frame
        {
            //if ur going up and ur above threshold
            return velY < 0 && (playerY + velY) <= Driver.HEIGHT / 2;//do
        }
        if(velY < 0 && (playerY + velY) > Driver.HEIGHT/2)//if ur going up and ur above threshhold
            return true;//do
        return y != 0 || playerY + velY > Driver.HEIGHT / 2;
    }


    public void moveVert(int velY) {
        y += -1 * velY;
        for (BackgroundSprite curr : backgrounds.get(currScreen)) {
            curr.updateVert(-velY);
        }
        for (NPC curr : npcs.get(currScreen)) {
            curr.updateVert(-velY);
        }
    }

    //inBounds means that its the bg moving
    //lol its so jank rn // f i c k s e d
    public boolean inBoundsHori(int velX, int playerX) {
        int nextX = playerX + velX;
        if (nextX > Driver.WIDTH / 2)//tryna go right
        {
            //at the edge
            return x + width > Driver.WIDTH;
        }
        return x != 0;
    }

    public void moveHori(int velX) {
        x += -1 * velX;
        for (BackgroundSprite curr : backgrounds.get(currScreen)) {
            curr.updateHori(-velX);
        }
        for (NPC curr : npcs.get(currScreen)) {
            curr.updateHori(-velX);
        }
    }

    public void initialize()
    {
        //Tile naming
        String tileBegin = "Images/Starter Tiles Platformer/";
        String grassBlock = tileBegin + "BasicGreenTiles/";
        String tileEnd = "_16x16.png";
        System.out.println(grassBlock + "Grass_3" + tileEnd);

        //Objects
        ArrayList<BackgroundSprite> boxes = new ArrayList<>();

        //boxes.add(new Box(Driver.WIDTH / 2, Driver.HEIGHT - 130, 30));
        boxes.add(new Box(Driver.WIDTH / 2 - 50, Driver.HEIGHT - 150, 30));
        boxes.add(new Box(0, Driver.HEIGHT - 150, 30));
        boxes.add(new Box(100, Driver.HEIGHT - 225, 30));
        boxes.add(new Box(200, Driver.HEIGHT - 300, 30));
        boxes.add(new Box(100, Driver.HEIGHT - 420, 30));
        boxes.add(new Box(200, Driver.HEIGHT - 530, 30));
        boxes.add(new Box(100, Driver.HEIGHT - 650, 30));
        boxes.add(new Box(200, Driver.HEIGHT - 750, 30));
        ImageIcon icon1 = new ImageIcon(Objects.requireNonNull(Driver.class.getResource(grassBlock + "Green_3" + tileEnd)));
        boxes.add(new Section(0, Driver.HEIGHT - 100, Driver.WIDTH * 2, icon1));
        boxes.add(new Building(100, Driver.HEIGHT - 200, 100, 100, 50, 50, 25, 50, 1));

        backgrounds.put(0, boxes);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/TestingBg.png")));

        maps.put(0, icon);
        ArrayList<NPC> npc = new ArrayList<>();
        npc.add(new NPC(Driver.WIDTH / 2 - 200, Driver.HEIGHT - 150));
        npcs.put(0, npc);


        boxes = new ArrayList<>();
        boxes.add(new Section(0, Driver.HEIGHT - 100, Driver.WIDTH * 2, icon1));

        backgrounds.put(1, boxes);
        maps.put(1, icon);
    }


}
