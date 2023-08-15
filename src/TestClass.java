import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args)
    {
        ArrayList<String> fullInventory = new ArrayList<>();
        String[][] inv = new String[5][2];

        fullInventory.add("I");
        fullInventory.add("am");
        fullInventory.add("in");
        fullInventory.add("the");
        fullInventory.add("correct");
        fullInventory.add("order");


        int arrayListCurr = 0;
        for(int r = 0; r < inv.length; r++)
        {
            for(int c = 0; c < inv[r].length; c++)
            {
                if(fullInventory.size() > arrayListCurr)
                {
                    inv[r][c] = fullInventory.get(arrayListCurr);
                    arrayListCurr++;
                }
            }
        }
        for(int r = 0; r < inv.length; r++)
        {
            for(int c = 0; c < inv[r].length; c++)
            {
                System.out.println(inv[r][c] + " row = " + r + ", col = " + c);
            }
        }


    }
}
