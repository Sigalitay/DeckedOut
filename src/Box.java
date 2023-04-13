import java.awt.*;

public class Box extends BackgroundSprite{

    Rectangle hitbox;

    public Box(int x, int y, int w) {
        super(x,y,w, 30);
        hitbox = new Rectangle(x,y,w,height);
    }

    public void updateVert(int y)
    {
        this.y +=  y;
        hitbox.y += y;
    }
    public void updateHori(int x)
    {
        this.x += x;
        hitbox.x += x;
    }




}
