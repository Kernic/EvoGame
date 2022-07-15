import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    /*
        Screen settings :
     */

    final int originalTileSize = 16; // Tiles will be 16x16
    final int scale = 3; // Will Scale tile to be bigger on screen

    final int tileSize = originalTileSize * scale; // Setting tile on screen size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    // Will set a ratio of 4x3

    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight= tileSize * maxScreenRow; // 576px

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // FPS
    int FPS = 60;


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        /*
            Game Loop
         */
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime(); // Getting the current time in nano seconds
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update(); // Updates the frame

                repaint(); // Calls paintComponent

                delta --;
            }
        }
    }
    public void update () {
        if (keyH.upPressed){
            playerY -= playerSpeed;
        } else if (keyH.downPressed) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g); // when using painComponent function use this

        Graphics2D g2 = (Graphics2D)g; // Cast Graphics to 2D Graphics

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();

    }
}
