
import java.awt.*;
import java.util.ArrayList;
//import javax.swing.ImageIcon;

public class Player extends Sprite {

    private Inventory inv = new Inventory();

    public Player() {
        super(Driver.WIDTH / 2 - 100, Driver.HEIGHT - 150);

        heightLimit = 100;
    }

    public void displayInv(Graphics g) {
        inv.drawSelf(g);
    }

    public void navInv(int key) {
        inv.navInv(key);
    }

    public void move(int dir) {
        //up
        if ((dir == 87 || dir == 38)) {
            if (!jumping && landed) {
                jumping = true;
                velY = -gravity;//go up
            }
        } else if (!jumping)
            velY = gravity;

        if ((dir == 65 || dir == 37))//left
            velX = -vel;
        else if ((dir == 68 || dir == 39))//right
            velX = vel;
    }

    public void stopMoving(int dir) {
        //left/right
        if ((dir == 65 || dir == 37) && velX != vel)//left
            velX = 0;
        else if ((dir == 68 || dir == 39) && velX != -vel)//right
            velX = 0;
    }

    public void interact() {
        for (BackgroundSprite curr : bg.backgrounds.get(bg.currScreen)) {
            if (curr instanceof Building build) {
                if (build.doorway.intersects(hitbox))//player is at the doorway
                {
                    bg.currScreen = build.id;
                    return;
                }
            }
        }
        for (NPC curr : bg.npcs.get(bg.currScreen)) {
            if (curr.hitbox.intersects(hitbox))//player is at the doorway
            {
                System.out.println("Interacted!");
                return;
            }
        }
    }

    public void update() {
        ableRight = true;
        ableLeft = true;
        ableUp = true;
        ableDown = true;
        //will update acc to backgroundSprites and provide new currGround
        ArrayList<Box> boxes = new ArrayList<>();
        for (BackgroundSprite curr : bg.backgrounds.get(bg.currScreen)) {
            if (curr instanceof Box)
                boxes.add((Box) curr);
        }
        int currGround = objectUpdates(boxes);

        //updates players vertical
        if (bg.inBoundsVert(velY, y))
            bgVertiUpdate(currGround);
        else
            vertiUpdate(currGround);


        //updates players horizontal
        if ((velX < 0 && ableLeft) || (velX > 0 && ableRight)) {
            if (bg.inBoundsHori(velX, x))
                bg.moveHori(velX);
            else
                x += velX;
        }

        //more jumping code
        jumpUpdate(currGround);

        //final update
        hitbox.x = x;
        hitbox.y = y;
    }//finally done with update for now...:weary: 9/23/22 7:37
    //lol u thought. working on background movement now
    //YES I DID THE BACKGROUND CODE TOO NOW 10/16/22 12:07 pm


}
