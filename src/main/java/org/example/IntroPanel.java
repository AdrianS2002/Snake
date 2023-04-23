package org.example;

import org.example.HowToPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class IntroPanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    CardLayout cardLayout;
    JButton buttonStart = new JButton("Start");
    JButton buttonHowTo = new JButton("How to play");

    JLabel labelTitle = new JLabel("Welcome to Snake");
    JPanel parentPanel; // add a reference to the parent panel
    GamePanel gamePanel; // add a reference to the GamePanel
    HowToPanel howToPlayPanel; // add a reference to the HowToPlayPanel

    public IntroPanel(JPanel parentPanel) throws HeadlessException{
        this.parentPanel = parentPanel;
        this.cardLayout = (CardLayout) parentPanel.getLayout(); // get the CardLayout from the parent panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);

        labelTitle.setBounds(330, 1, 400, 150);
        labelTitle.setFont(new Font("times new roman", Font.BOLD, 50));
        labelTitle.setForeground(Color.yellow);
        labelTitle.setVisible(true);
        this.setLayout(null);
        this.add(labelTitle);

        buttonStart.setBounds(450,480, 150, 100);
        buttonStart.setFont(new Font("times new roman", Font.BOLD, 35));
        buttonStart.setForeground(Color.WHITE);
        buttonStart.setBackground(Color.RED);
        buttonStart.setVisible(true);
        buttonStart.addActionListener(this);
        this.setLayout(null);
        this.add(buttonStart);

        buttonHowTo.setBounds(400,680, 250, 100);
        buttonHowTo.setFont(new Font("times new roman", Font.BOLD, 35));
        buttonHowTo.setForeground(Color.WHITE);
        buttonHowTo.setBackground(Color.RED);
        buttonHowTo.setVisible(true);
        buttonHowTo.addActionListener(this);
        this.setLayout(null);
        this.add(buttonHowTo);
        this.setFocusable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonStart) {
            cardLayout.show(parentPanel, "game");
        } else if (e.getSource() == buttonHowTo) {
            if (howToPlayPanel == null) {
                howToPlayPanel = new HowToPanel(parentPanel);
                parentPanel.add(howToPlayPanel, "howToPlay"); // add the HowToPanel to the parent panel
            }
            cardLayout.show(parentPanel, "howToPlay");
        }
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
