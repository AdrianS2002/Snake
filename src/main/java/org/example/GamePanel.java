package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    static final int UNIT_SIZE = 35;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int  delay = 325;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int initialBodyParts = 5;
    int scoreMarked = 0;
    int pointX;
    int pointY;
    char directions ='R';
    boolean running = false;
    Timer timer;
    Random random;
    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }

    public void startGame() {
        newPoint();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("times new roman", Font.BOLD, 100));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        g.setColor(Color.red);
        g.setFont(new Font("times new roman", Font.BOLD, 50));
        FontMetrics metricsScore = getFontMetrics(g.getFont());
        g.drawString("Score:"+ scoreMarked, 430, g.getFont().getSize());

        JButton button = new JButton("Restart");
        button.setBounds(430,780, 150, 100);
        button.setFont(new Font("times new roman", Font.BOLD, 35));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.RED);
        button.setVisible(true);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(button);
                removeAll();
                restart();
                revalidate();
                repaint();
            }
        });
    }
    private void restart() {
        // Reset game state
        initialBodyParts = 5;
        scoreMarked = 0;
        delay = 325;
        directions ='R';
        running = false;
        for(int i = 0; i < GAME_UNITS; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        startGame();
    }
    public void move() {

        for(int i = initialBodyParts; i>0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(directions) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void newPoint() {
        pointX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        pointY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;


    }

    public void draw(Graphics g) {
        if(running) {
            g.setColor(Color.yellow);
            g.fillOval(pointX, pointY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < initialBodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.decode("#1eda39"));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.decode("#09fc2c"));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("times new roman", Font.BOLD, 30));
            FontMetrics metricsScore = getFontMetrics(g.getFont());
            g.drawString("Score:"+ scoreMarked, (SCREEN_WIDTH - metricsScore.stringWidth("Score: "+ scoreMarked))/2, g.getFont().getSize());
        }
        else
        {
            gameOver(g);
        }
    }


    public void checkPoint() {
        if((x[0] == pointX) && (y[0] == pointY)) {
            initialBodyParts++;
            scoreMarked++;
            newPoint();
            if (scoreMarked % 5 == 0) {
                delay -= 20;
                timer.setDelay(delay);
            }
        }
    }

    public void checkCollisions() {
        for(int i=initialBodyParts;i>0;i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        if(x[0] < 0) {
            running = false;
        }

        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }

        if(y[0] < 0) {
            running = false;
        }

        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if(!running) {
            timer.stop();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkPoint();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if(directions != 'R') {
                        directions = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(directions != 'L') {
                        directions = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(directions != 'D') {
                        directions = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(directions != 'U') {
                        directions = 'D';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(directions != 'R') {
                        directions = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(directions != 'L') {
                        directions = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(directions != 'D') {
                        directions = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(directions != 'U') {
                        directions = 'D';
                    }
                    break;
            }
        }
    }

}
