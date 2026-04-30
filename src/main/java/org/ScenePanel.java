package org;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ScenePanel extends JPanel implements Runnable {

    private BufferedImage backgroundImage;
    private Player player;
    private Candy candy;
    private Bomb bomb;

    Thread gameThread;

    public ScenePanel() {

        player = new Player(300, 270);
        candy = new Candy(300,0,130,160);
        bomb = new Bomb(400,0,180,210);

        setFocusable(true); // פוקוס על הדמות
        addKeyListener(new MovementListener(player));
        SwingUtilities.invokeLater(() -> requestFocusInWindow()); // מריץ את הפוקוס רק אחרי שהחלון נטען, כדי שהמקלדת תעבוד

        gameThread = new Thread(this);
        gameThread.start();

        setLayout(null);
        //הוספת רקע
        try {
            InputStream inputStream = ScenePanel.class.getResourceAsStream("/game__background.jpeg");
            backgroundImage = ImageIO.read(inputStream);
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
        player.draw(g);
        candy.draw(g);
        bomb.draw(g);
    }

        }
