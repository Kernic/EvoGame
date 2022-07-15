package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp_1"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp_2"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown_1"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown_2"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft_1"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft_2"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight_1"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight_2"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction="down";
    }

    public void update () {
        if (keyH.upPressed){
            direction = "up";
            y -= speed;
        }
        if (keyH.downPressed) {
            direction = "down";
            y += speed;
        }
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
