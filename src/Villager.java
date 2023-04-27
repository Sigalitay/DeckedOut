import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Villager extends NPC{

    HashMap<Integer, ArrayList<String>> idMap = new HashMap<>();
    Graphics g;
    boolean interaction = false;
    StringUtility letterByLetter;

    public Villager(int x, int y, int id)
    {
        super(x, y, id);
        initialize();
        letterByLetter = new StringUtility(idMap.get(id).get(1), 200);
    }

    public void initialize()
    {
        //0
        idMap.put(0, new ArrayList<>());
        idMap.get(0).add("MiniOldMan.png");//imageIcon
        idMap.get(0).add("Hello!");//script
    }

    public void drawSelf(Graphics g) throws IOException, FontFormatException {
        if(this.g == null)
            this.g = g;
        Graphics2D g2d = (Graphics2D) g;
        String idIcon = idMap.get(id).get(0);//get the current ids image name

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + idIcon)));
        Image image = icon.getImage();
        g2d.drawImage(image, x, y, diam, diam, null);

        if(interaction)
        {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("prstart.ttf");
            assert stream != null;
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);

            g.setColor(Color.lightGray);//making a box for the text
            g.fillRect(50, 50, 775, 100);

            g.setColor(Color.black);
            g.setFont(customFont);
            g.drawString(letterByLetter.retrieve(), 100,100);

        }
    }

    public void interact(){
        letterByLetter.reset();
        interaction = !interaction;
        Sprite.movementAllowed = !Sprite.movementAllowed;
    }



}
