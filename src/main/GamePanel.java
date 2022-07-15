package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    /*
        Screen settings :
     */

    final int originalTileSize = 16; // Tiles will be 16x16
    final int scale = 3; // Will Scale tile to be bigger on screen

    public int tileSize = originalTileSize * scale; // Setting tile on screen size
    final int maxScreenCol = 20;
    final int maxScreenRow = 16;
    // Will set a ratio of 4x3

    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight= tileSize * maxScreenRow; // 576px

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

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
        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime(); // Getting the current time in milliseconds
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update(); // Updates the frame
                repaint(); // Calls paintComponent

                delta --;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update () {
        player.update();
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g); // when using painComponent function use this

        Graphics2D g2 = (Graphics2D)g; // Cast Graphics to 2D Graphics

        player.draw(g2);

        g2.dispose();

    }
}
