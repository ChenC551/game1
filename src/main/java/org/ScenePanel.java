package org;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class ScenePanel extends JPanel implements Runnable {

    private BufferedImage backgroundImage;
    private BufferedImage livesImage;
    private BufferedImage scoreBar;
    private BufferedImage soundIcon;
    private BufferedImage arrowLeft;
    private BufferedImage arrowRight;
    private Random random = new Random();
    private Candy [] candies;
    private Bomb [] bombs;
    private Player player;
    private int score = 0;
    private BufferedImage explosionImage;
    private boolean showExplosion = false;
    private int explosionX;
    private int explosionY;
    private int lives = 3;


    Thread gameThread;

    public ScenePanel() {

        candies = new Candy[5];
        bombs = new Bomb[3];

        for (int i = 0; i < candies.length; i++) {
            int x = random.nextInt(800);
            int y = -random.nextInt(600);
            int speed = 2 + random.nextInt(4);
            candies[i] = new Candy(x, y, 130, 160, speed);
        }

        for (int i = 0; i < bombs.length; i++) {
            int x = random.nextInt(800);
            int y = -random.nextInt(600);
            int speed = 2 + random.nextInt(4);
            bombs[i] = new Bomb(x, y, 180, 210, speed);
        }

        player = new Player(360, 260);

        setFocusable(true);
        addKeyListener(new MovementListener(player));


        gameThread = new Thread(this);
        gameThread.start();

        setLayout(null);


        //הוספת רקע
        try {
            InputStream inputStream = ScenePanel.class.getResourceAsStream("/game__background.png");
            backgroundImage = ImageIO.read(inputStream);

            livesImage = ImageIO.read(ScenePanel.class.getResourceAsStream("/lives_3.png"));
            scoreBar = ImageIO.read(ScenePanel.class.getResourceAsStream("/score_.png"));
            soundIcon = ImageIO.read(ScenePanel.class.getResourceAsStream("/sound_on.png"));
            arrowLeft = ImageIO.read(ScenePanel.class.getResourceAsStream("/arrow_left.png"));
            arrowRight = ImageIO.read(ScenePanel.class.getResourceAsStream("/arrow_right.png"));
            explosionImage = ImageIO.read(ScenePanel.class.getResourceAsStream("/explosion.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        while (true) {

            // תזוזת השחקנית
            if(getWidth()>0) {
                player.move(getWidth());
            }

            // סוכריות
            // סוכריות
            for (int i = 0; i < candies.length; i++) {
                candies[i].fall();

                if (candies[i].getY() > getHeight()) {
                    candies[i].setY(-50);
                    candies[i].setX(random.nextInt(800));
                }
            }

// פצצות
            for (int i = 0; i < bombs.length; i++) {
                bombs[i].fall();

                if (bombs[i].getY() > getHeight()) {
                    bombs[i].setY(-50);
                    bombs[i].setX(random.nextInt(800));
                }
            }

            checkCollision();
            repaint(); // ציור מחדש

            try {
                Thread.sleep(20); // שליטה במהירות
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void checkCollision() {

        Rectangle playerRect = player.getRect();

        // 🍬 סוכריות
        for (int i = 0; i < candies.length; i++) {
            Rectangle candyRect = candies[i].getRect();

            if (playerRect.intersects(candyRect)) {
                score += 10;

                // מחזיר למעלה
                candies[i].setY(-50);
                candies[i].setX(random.nextInt(800));
            }
        }

        // 💣 פצצות
        for (int i = 0; i < bombs.length; i++) {
            Rectangle bombRect = bombs[i].getRect();

            if (playerRect.intersects(bombRect)) {

                lives--; // הורדת חיים

                // מיקום הפיצוץ
                explosionX = bombs[i].getX();
                explosionY = bombs[i].getY();
                showExplosion = true;

                // מחזיר את הפצצה למעלה
                bombs[i].setY(-50);
                bombs[i].setX(random.nextInt(800));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {//מתודה שאחראית לצייר את הפאנל על המסך
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        for (int i = 0; i < candies.length; i++) {
            candies[i].draw(g);
        }
        for (int i = 0; i < bombs.length; i++) {
            bombs[i].draw(g);
        }
        if (showExplosion) {
            g.drawImage(explosionImage, explosionX, explosionY, 130, 130, this);
        }
        player.draw(g);

        g.drawImage(livesImage, -20, 5, 230, 80, this);

        g.drawImage(scoreBar, 290, 3, 230, 90, this);

        g.setColor(new Color(90, 20, 90));
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(String.valueOf(score), 445, 56);

        g.drawImage(soundIcon, 700, 10, 70, 70, this);

        g.drawImage(arrowLeft, 10, 400, 65, 65, this);
        g.drawImage(arrowRight, 80, 400, 65, 65, this);

    }
}