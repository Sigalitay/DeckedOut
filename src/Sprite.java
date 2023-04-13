import java.awt.*;
import java.util.ArrayList;

public class Sprite {
    protected int x, y;
    protected int velX, velY, gravity = 5;
    protected final int vel = 5;
    protected final int diam = 20;

    //jumping vars
    protected boolean jumping;
    protected boolean hitLimit;
    protected int heightLimit = 50;
    protected boolean landed;
    protected int heightCounter = 0;

    protected boolean ableUp, ableDown, ableRight, ableLeft;

    static Background bg;
    //hitbox
    Rectangle hitbox;

    public Sprite(int x, int y)
    {
        this.x = x;
        this.y = y;
        velY = gravity;
        hitbox = new Rectangle(this.x, this.y, diam, diam);

    }
    public void drawSelf(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);//testing the hitbox
        g.fillRect(x, y, diam, diam);
        //g2d.drawImage(characterDown, x, y, diam, diam, null);
    }
    //Update code (goes in loop)
    public void update() {
        ArrayList<BackgroundSprite> backgroundSprites = bg.backgrounds.get(bg.currScreen);
        ableRight = true;
        ableLeft = true;
        ableUp = true;
        ableDown = true;

        //will update acc to backgroundSprites and provide new currGround
        ArrayList<Box> boxes = new ArrayList<>();
        for (BackgroundSprite curr : backgroundSprites) {
            if (curr instanceof Box)
                boxes.add((Box) curr);
        }
        int currGround = objectUpdates(boxes);
        vertiUpdate(currGround);

        //updates players horizontal
        if ((velX < 0 && ableLeft) || (velX > 0 && ableRight)) {
            x += velX;
        }

        //more jumping code
        jumpUpdate(currGround);

        //final update
        hitbox.x = x;
        hitbox.y = y;
    }

    public void updateVert(int velY)
    {
        hitbox.y += velY;
        y += velY;
    }
    public void updateHori(int velX)
    {
        hitbox.x += velX;
        x += velX;
    }

    public void vertiUpdate(int currGround) {
        if (!hitLimit && jumping)//while the height limit hasnt been reached
        {
            if (heightCounter <= heightLimit && ableUp) {
                y += velY;
                heightCounter -= velY;
            } else {
                hitLimit = true;
                velY = gravity;//go down
            }
        } else if (y < currGround - 1 && ableDown) {//if currY is still in the air go down
            if (y + velY >= currGround)
                y += (y + velY) - currGround - 1;
            else
                y += velY;
        }
    }

    public int objectUpdates(ArrayList<Box> boxes) {
        int currGround = Driver.HEIGHT;
        int futureX = hitbox.x + velX;
        int futureY = hitbox.y + velY;

        //Objects
        for (Box box : boxes) {
            if (box.hitbox.intersects(new Rectangle(futureX, hitbox.y, diam, diam))) {
                if (velX < 0)
                    ableLeft = false;
                else if (velX > 0)
                    ableRight = false;
            }

            if (box.hitbox.intersects(new Rectangle(hitbox.x, futureY, diam, diam))) {
                if (velY < 0)
                    ableUp = false;
                else {
                    ableDown = false;
                    currGround = box.y - diam;
                }
            }

            //corners
            if (ableDown && ableUp && ableLeft && ableRight) {
                if (box.hitbox.intersects(new Rectangle(futureX, futureY, diam, diam))) {
                    if (velY < 0)
                        ableUp = false;
                    else
                        ableDown = false;

                    if (velX < 0)
                        ableLeft = false;
                    else if (velX > 0)
                        ableRight = false;
                }
            }

        }
        return currGround;
    }
    public void bgVertiUpdate(int currGround) {
        if (!hitLimit && jumping)//while the height limit hasnt been reached
        {
            if (heightCounter <= heightLimit && ableUp) {
                bg.moveVert(velY);
                heightCounter -= velY;
            } else {
                hitLimit = true;
                velY = gravity;//go down
            }

        } else if (y < currGround - 1 && ableDown)//is above the currGround
        {
            if (y + velY >= currGround)
                bg.moveVert((y + velY) - currGround - 1);
            else
                bg.moveVert(velY);
        }
    }

    public void jumpUpdate(int currGround) {
        landed = y >= currGround - 1;//if y is on the "ground" it landed
        if (landed) {
            jumping = false;
            hitLimit = false;
            heightCounter = 0;
        }
    }

    public void reset() {
        x = Driver.WIDTH / 2 - 100;
        y = Driver.HEIGHT / 2 - diam;
        velX = 0;

        gravity = 5;
        velY = gravity;
        heightLimit = 100;
        heightCounter = 0;

        jumping = false;
        hitLimit = false;
        landed = false;

        ableRight = false;
        ableLeft = false;
        ableUp = false;
        ableDown = false;

        hitbox.y = y;
        hitbox.x = x;
    }



}
