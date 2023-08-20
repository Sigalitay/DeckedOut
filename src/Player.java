
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
//import javax.swing.ImageIcon;

public class Player extends Sprite {


    private HealthBar health = new HealthBar(100, 20);
    private Inventory inv = new Inventory();

    public Player() {
        super(Driver.WIDTH / 2 - 100, Driver.HEIGHT - 150);

        heightLimit = 100;
    }

    public void drawSelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "PlayerTest.png")));
        Image image = icon.getImage();
        g2d.drawImage(image, x,y, diam, diam, null);
        health.drawSelf(g);
        //g2d.drawImage(characterDown, x, y, diam, diam, null);
    }

    public void increaseHealth(int inc)
    {
        if(health.currHealth + inc < health.totalHealth)
            health.currHealth += inc;
        else
            health.currHealth = health.totalHealth;
    }
    public void decreaseHealth(int dmg)
    {
        if(health.currHealth > 0)
            health.currHealth -= dmg;
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
                    System.out.println("Interacted! id = " + build.id);
                    Driver.scene.currScreen = build.id;
                    return;
                }
            }
        }
        if(Driver.scene.sprites.get(Driver.scene.currScreen) != null) {
            for (Sprite curr : bg.sprites.get(bg.currScreen)) {
                if (curr.hitbox.intersects(hitbox))//player is at the npc
                {
                    if(curr instanceof NPC) {
                        System.out.println("Interacted!");
                        ((NPC) curr).interact();
                    }

                    return;
                }
            }
        }
    }

    public void getAttacked(Attack atk)
    {
        //keep it basic for now
        decreaseHealth(atk.damage);
    }

    public void attackDetector()
    {
        if(Driver.scene.sprites.get(Driver.scene.currScreen) != null) {
            for (Sprite curr : bg.sprites.get(bg.currScreen)) {
                if (curr.hitbox.intersects(hitbox)) {
                    if (curr instanceof Enemy) {
                        if(!(((Enemy) curr).getAttack().cooldown.getCoolDownActive()))
                        {
                            System.out.println("Attacked");
                            getAttacked(((Enemy) curr).getAttack());
                            ((Enemy) curr).getAttack().cooldown.activateCoolDown();
                        }
                    }
                }
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

        attackDetector();
    }//finally done with update for now...:weary: 9/23/22 7:37
    //lol u thought. working on background movement now
    //YES I DID THE BACKGROUND CODE TOO NOW 10/16/22 12:07 pm


}
