import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    HashMap<Item, Integer> amount = new HashMap<>();
    int margin = 50;

    int currScreen = 0;//STATS, MAP, ARMORY/LOADOUT

    //loadout
    Slot[] currLoadout = new Slot[4];
    ArrayList<Item> fullInventory = new ArrayList<>();
    int currRow = 0;
    int currCol = 0;
    Slot[][] inv = new Slot[2][5];

    public Inventory(){
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

    public void organizeInv()//initiallize and organize the inv
    {
        if(currLoadout[0] == null)
        {
            for(int i = 0; i< 4; i++)
                currLoadout[i] = new Slot();
        }
        int arrayListCurr = 0;
        for(int r = 0; r < inv.length; r++)
        {
            for(int c = 0; c < inv[r].length; c++)
            {
                if(inv[r][c] == null)
                    inv[r][c] = new Slot();
                if(fullInventory.size() > arrayListCurr)
                {
                    inv[r][c].item = fullInventory.get(arrayListCurr);
                    arrayListCurr++;
                }
            }
        }
    }

    public void addToLoadout(int key){
        Item selectedItem = inv[currRow][currCol].item;
        //TODO: Add click and drag feature to loadout and fluidly move item
        if(currLoadout[key].item != null)//slot is occupied
            fullInventory.add(currLoadout[key].item);//put the slotted item back into inventory
        fullInventory.remove(selectedItem);
        currLoadout[key].item = selectedItem;
        organizeInv();
    }

    public void drawSelf(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        if (currScreen == 0) {//Loadout/Armoury
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
            //TODO: unhard code this
            int x = 700;
            for(int r = 0; r < inv.length; r++)
            {
                int y = 100;
                for(int c = 0; c < inv[r].length; c++)
                {
                    g.setColor(Color.blue);
                    g.drawOval(x,y,50,50);
                    if(inv[r][c].item != null)
                        inv[r][c].item.drawSelf(g, x+15, y+15);
                    y+=75;
                }
                x+=75;
            }
        } else if (currScreen == 1) {
            g.setColor(Color.GRAY);
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
        }
       // g.fillRect(margin, margin, Driver.WIDTH - margin*2, Driver.HEIGHT - margin*2);
       //g2d.drawImage(characterDown, x, y, diam, diam, null);
    }

    public void navInv(int key) {
        if (key == 65)
            currScreen--;
        else if (key == 68)
            currScreen++;
        currScreen = Math.abs(currScreen % 3);

        if(currScreen == 0)//navigate inv with arrows and enter FOR NOW
        {
            //TODO: Update inv navigation to click and drag
            if(key == 38)//up
            {
               if(currRow != 0)
                   currRow--;
            }
            else if(key == 40)//down
            {
                if(currRow != 5)
                    currRow++;
            }
            if(key == 37)//left
            {
                if(currCol != 0)
                    currCol--;
            }
            else if(key == 39)//right
            {
                if(currCol != 1)
                    currCol++;
            }
        }
    }

}
