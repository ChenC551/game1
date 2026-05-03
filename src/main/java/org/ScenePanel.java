package org;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ScenePanel extends JPanel implements Runnable {

    private BufferedImage backgroundImage;
    private BufferedImage livesImage;
    private BufferedImage scoreBar;
    private BufferedImage soundIcon;
    private BufferedImage arrowLeft;
    private BufferedImage arrowRight;
    private Candy candy;
    private Bomb bomb;
    private Player player;
    private int score = 0;


    Thread gameThread;

    public ScenePanel() {


        candy = new Candy(300, 0, 130, 160);
        bomb = new Bomb(400, 0, 180, 210);
        player = new Player(360, 260);

        setFocusable(true); // פוקוס על הדמות
        addKeyListener(new MovementListener(player));
        SwingUtilities.invokeLater(() -> requestFocusInWindow()); // מריץ את הפוקוס רק אחרי שהחלון נטען, כדי שהמקלדת תעבוד

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void run() {
        while (true) {
            player.move(getWidth()); // תזוזה
            candy.fall();  // סוכריה נופלת
            bomb.fall();   // פצצה נופלת
            repaint(); // ציור
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {//מתודה שאחראית לצייר את הפאנל על המסך
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(livesImage, -20, 5, 230, 80, this);

        g.drawImage(scoreBar, 290, 3, 230, 90, this);

        g.setColor(new Color(90, 20, 90));
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(String.valueOf(score), 445, 56);

        g.drawImage(soundIcon, 700, 10, 70, 70, this);

        g.drawImage(arrowLeft, 10, 400, 65, 65, this);
        g.drawImage(arrowRight, 80, 400, 65, 65, this);

        candy.draw(g);
        bomb.draw(g);
        player.draw(g);

    }
}