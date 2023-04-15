
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    //instance variables
    static final int WIDTH = 900;
    static final int HEIGHT = 600;

    //Background
    static Scenes scene = new Scenes(WIDTH * 2, HEIGHT * 2);

    //Objects
    Player player;

    boolean invOpen = false;

    //Default Constructor
    public Driver() {
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Decked Out"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH, HEIGHT + 23)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves

        Sprite.bg = scene;
        player = new Player();

    }

    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) {
        //getting the key pressed
        int key = e.getKeyCode();
        //System.out.println(key);
        if (key == 69)
            invOpen = !invOpen;
        if (!invOpen)
            player.move(key);
        else
            player.navInv(key);

        if (key == 82)
            player.reset();
        if (key == 70)
            player.interact();
        if(key == 45)//dec health
            player.decreaseHealth();
        if(key == 61)//dec health
            player.increaseHealth();
    }

    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyCode() + " " + e.getKeyChar());
        int key = e.getKeyCode();

        player.stopMoving(key);

    }

    //All your UI drawing goes in here
    public void paintComponent(Graphics g) {
        g.drawLine(0, Driver.HEIGHT / 2, Driver.WIDTH, Driver.HEIGHT / 2);
        scene.drawSelf(g);

        for (BackgroundSprite box : scene.backgrounds.get(scene.currScreen)) {
            box.drawSelf(g);
        }
        if(scene.npcs.get(scene.currScreen) != null) {
            for (NPC npc : scene.npcs.get(scene.currScreen)) {
                npc.drawSelf(g);
            }
        }

        player.drawSelf(g);


        if (invOpen)
            player.displayInv(g);
    }

    public void loop() {
        if(player.movementAllowed)
            player.update();
        if(scene.npcs.get(scene.currScreen) != null) {
            for (NPC npc : scene.npcs.get(scene.currScreen)) {
                npc.update();
            }
        }
        repaint();
    }

    public void start(final int ticks) {
        Thread gameThread = new Thread(() -> {
            while (true) {
                loop();
                try {
                    Thread.sleep(1000 / ticks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    public static void main(String[] args) {
        Driver g = new Driver();
        g.start(60);
    }


    //unused methods lol
    public void keyTyped(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
//        for(Box box : boxes)
//        {
//            System.out.println(box.intersecting(player.hitbox));
//        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}
