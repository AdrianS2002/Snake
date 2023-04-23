package org.example;

import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {

    static final int SCREEN_WIDTH = 1000;

    static final int SCREEN_HEIGHT = 1000;

    public Interface() {
        JPanel parentPanel = new JPanel(); // create a new parent panel
        CardLayout cardLayout = new CardLayout(); // create a CardLayout for the parent panel
        parentPanel.setLayout(cardLayout); // set the CardLayout to the parent panel

        IntroPanel introPanel = new IntroPanel(parentPanel); // pass the parent panel to the IntroPanel constructor
        GamePanel gamePanel = new GamePanel();
        introPanel.setGamePanel(gamePanel); // set the GamePanel in the IntroPanel


        parentPanel.add(introPanel, "intro"); // add the IntroPanel to the parent panel
        parentPanel.add(gamePanel, "game"); // add the GamePanel to the parent panel

        this.add(parentPanel); // add the parent panel to the JFrame

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

