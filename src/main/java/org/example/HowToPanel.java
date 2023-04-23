package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    private JButton buttonBack = new JButton("Back");
    private JLabel labelTitle = new JLabel("How to play");

    private JLabel lableWASD = new JLabel("Use W A S D or arrow keys to move");

    private JLabel lablePoint = new JLabel("Collect points to grow. The longer you get, the faster you go!");
    private JLabel lableCondition = new JLabel("If you hit the wall or yourself, you lose!");
    private JLabel labelEnd=new JLabel("Good luck and have fun!");
    private JPanel parentPanel;

    public HowToPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);

        labelTitle.setBounds(400, 1, 400, 150);
        labelTitle.setFont(new Font("times new roman", Font.BOLD, 50));
        labelTitle.setForeground(Color.YELLOW);
        labelTitle.setVisible(true);
        this.setLayout(null);
        this.add(labelTitle);

        lableWASD.setBounds(250, 300, 1000, 150);
        lableWASD.setFont(new Font("times new roman", Font.PLAIN, 25));
        lableWASD.setForeground(Color.RED);
        lableWASD.setVisible(true);
        this.setLayout(null);
        this.add(lableWASD);


        lablePoint.setBounds(250, 350, 800, 150);
        lablePoint.setFont(new Font("times new roman", Font.PLAIN, 25));
        lablePoint.setForeground(Color.RED);
        lablePoint.setVisible(true);
        this.setLayout(null);
        this.add(lablePoint);

        lableCondition.setBounds(250, 400, 800, 150);
        lableCondition.setFont(new Font("times new roman", Font.PLAIN, 25));
        lableCondition.setForeground(Color.RED);
        lableCondition.setVisible(true);
        this.setLayout(null);
        this.add(lableCondition);

        labelEnd.setBounds(330, 550, 800, 150);
        labelEnd.setFont(new Font("times new roman", Font.BOLD, 35));
        labelEnd.setForeground(Color.RED);
        labelEnd.setVisible(true);
        this.setLayout(null);
        this.add(labelEnd);


        buttonBack.setBounds(450,780, 150, 100);
        buttonBack.setFont(new Font("times new roman", Font.BOLD, 35));
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBackground(Color.RED);
        buttonBack.setVisible(true);
        buttonBack.addActionListener(this);
        this.setLayout(null);
        this.add(buttonBack);
        this.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
            cardLayout.show(parentPanel, "intro");
        }
    }
}
