import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Inventory {

    HashMap<Item, Integer> amount = new HashMap<>();
    int margin = 50;

    int currScreen = 0;//STATS, MAP, ARMORY/LOADOUT

    //loadout
    Slot[] currLoadout = new Slot[4];
    int currLoadoutSlot = 0;
    ArrayList<Item> fullInventory = new ArrayList<>();
    int currCol = 0;
    int currRow = 0;
    Slot[][] inv = new Slot[2][5];

    public Inventory(){
        initInv();
        organizeInv();
        addItemToArmory(new Item(""));
    }

    public void addItem(Item x) {
        //two ways of getting items which should be coded elsewhere but
        //shop or from a chest //bosses can be from chests
        //OR from a mob drop // mb mobs should only drop coins
        amount.put(x, amount.getOrDefault(amount, 0) + 1);

    }

    public void addItemToArmory(Item x) {
        fullInventory.add(x);
        organizeInv();
    }

    public void initInv()//TODO: unhard code all of this. make it based off the inv size width/height
    {
        int loadoutY = 100;
        for(int i = 0; i< 4; i++)
            currLoadout[i] = new Slot(300,loadoutY+=100,50);

        int x = 700;
        for(int r = 0; r < inv.length; r++)
        {
            int y = 200;
            for(int c = 0; c < inv[r].length; c++)
            {
                inv[r][c] = new Slot(x,y,50);
                y+=75;
            }
            x+=75;
        }
    }

    public void organizeInv()//initiallize and organize the inv
    {
        int arrayListCurr = 0;
        for(int r = 0; r < inv.length; r++)
        {
            for(int c = 0; c < inv[r].length; c++)
            {
                if(fullInventory.size() > arrayListCurr)
                {
                    inv[r][c].item = fullInventory.get(arrayListCurr);
                    arrayListCurr++;
                }
                else
                    inv[r][c].item = null;
            }
        }
    }

    public void addToLoadout(){
        Item selectedItem = inv[currRow][currCol].item;
        //TODO: Add click and drag feature to loadout and fluidly move item
        if(currLoadout[currLoadoutSlot].item != null)//slot is occupied
            fullInventory.add(currLoadout[currLoadoutSlot].item);//put the slotted item back into inventory
        fullInventory.remove(selectedItem);
        currLoadout[currLoadoutSlot].item = selectedItem;
        organizeInv();
    }

    public void drawSelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (currScreen == 0) {//Loadout/Armoury
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
            //draw slots
            for(int r = 0; r < inv.length; r++)
                for(int c = 0; c < inv[r].length; c++)
                    inv[r][c].drawSelf(g);
            for(Slot curr : currLoadout)
                curr.drawSelf(g);

            //highlight curr slot
            g.setColor(Color.red);
            g.drawOval(inv[currRow][currCol].x-5,inv[currRow][currCol].y-5, inv[currRow][currCol].diam+10,inv[currRow][currCol].diam+10);
            g.drawOval(currLoadout[currLoadoutSlot].x-5,currLoadout[currLoadoutSlot].y-5,currLoadout[currLoadoutSlot].diam + 10, currLoadout[currLoadoutSlot].diam +10);
        } else if (currScreen == 1) {//Player Stats
            g.setColor(new Color(12, 63, 101));
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(Driver.class.getResource("Images/" + "PlayerTest.png")));
            Image image = icon.getImage();
            g2d.drawImage(image, 200,250, 250, 250, null);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
        }
    }

    public void navInv(int key) {
        if (key == 65)
            currScreen--;
        else if (key == 68)
            currScreen++;
        currScreen = Math.abs(currScreen % 3);

        if(currScreen == 0)//navigate inv with arrows and enter FOR NOW
        {
            navScreen1(key);
        }

    }

    public void navScreen1(int key)
    {
        //TODO: Update inv navigation to click and drag
        if(key == 38)//up
        {
            if (currCol != 0)
                currCol--;
        }
        else if(key == 40)//down
        {
            if(currCol != 5)
                currCol++;
        }
        if(key == 37)//left
        {
            if (currRow != 0)
                currRow--;
        }
        else if(key == 39)//right
        {
            if(currRow != 1)
                currRow++;
        }

        if(key == 49)
            currLoadoutSlot = 0;
        else if(key == 50)
            currLoadoutSlot = 1;
        else if(key == 51)
            currLoadoutSlot = 2;
        else if(key == 52)
            currLoadoutSlot = 3;

        if(key == 10)//enter
        {
            addToLoadout();
        }

    }

}
