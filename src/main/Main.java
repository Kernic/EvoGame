package main;

import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        JFrame window = new JFrame();

        /*
            Window Configuration
         */
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to exit code when window is close
        window.setResizable(false); // Can not resize the window
        window.setTitle("EvoGame by Setaz Games"); // Setting title

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // adding panel to window
        window.pack();  // Setting window size to Panel size
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null); // Set window on screen center
        window.setVisible(true); // Display window

    }
}
