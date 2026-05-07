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
    private BufferedImage [] livesImages = new BufferedImage[4];
    private int lives = 3;
    private BufferedImage scoreBar;
    private BufferedImage soundIcon;
    private BufferedImage soundOffIcon;
    private boolean soundMuted = false;
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
    private int explosionFrames = 0;
    private BufferedImage gameOverImage;
    private boolean gameOver = false;
    private BufferedImage winImage;
    private boolean win = false;
    private int endScreenSize = 0;
    private JButton playAgainButton;
    private JButton homeButton;
    private SoundManager soundManager = new SoundManager();
    private boolean winSoundPlayed = false;
    private int countdown = 3;
    private boolean gameStarted = false;

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

        int playerWidth = 210;
        player = new Player((800 - playerWidth) / 2, 260);

        setFocusable(true);
        addKeyListener(new MovementListener(player));
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                Rectangle soundButton =
                        new Rectangle(700, 10, 70, 70);

                if (soundButton.contains(e.getPoint())) {
                    soundMuted = !soundMuted;
                    if (soundMuted) {
                        soundManager.stopBackgroundMusic();
                        soundManager.stopEffect();
                    }
                    else {
                        soundManager.playBackgroundMusic("/MUSICBEKROUND.wav");
                    }

                    repaint();
                }
            }
        });

        gameThread = new Thread(this);
        gameThread.start();

        setLayout(null);

        playAgainButton = new JButton();
        playAgainButton.setBounds(265, 300, 128, 30);
        makeButtonTransparent(playAgainButton);
        playAgainButton.setVisible(false);
        add(playAgainButton);


        homeButton = new JButton();
        homeButton.setBounds(410, 300, 128, 30);
        makeButtonTransparent(homeButton);
        homeButton.setVisible(false);
        add(homeButton);

        playAgainButton.addActionListener(e -> {
            soundManager.stopEffect();
            soundManager.stopBackgroundMusic();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            ScenePanel newGame = new ScenePanel();
            frame.setContentPane(newGame);
            frame.revalidate();
            frame.repaint();
            newGame.requestFocusInWindow();
        });


        homeButton.addActionListener(e -> {
            soundManager.stopEffect();
            soundManager.stopBackgroundMusic();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new StartPanel());
            frame.revalidate();
            frame.repaint();
        });

        //הוספת רקע
        try {
            InputStream inputStream = ScenePanel.class.getResourceAsStream("/game__background.png");
            backgroundImage = ImageIO.read(inputStream);
            livesImages[0] = ImageIO.read(getClass().getResourceAsStream("/lives_0.png"));
            livesImages[1] = ImageIO.read(getClass().getResourceAsStream("/lives_1.png"));
            livesImages[2] = ImageIO.read(getClass().getResourceAsStream("/lives_2.png"));
            livesImages[3] = ImageIO.read(getClass().getResourceAsStream("/lives_3.png"));
            scoreBar = ImageIO.read(ScenePanel.class.getResourceAsStream("/score_.png"));
            soundIcon = ImageIO.read(ScenePanel.class.getResourceAsStream("/sound_on.png"));
            soundOffIcon = ImageIO.read(ScenePanel.class.getResourceAsStream("/sound_off.png"));
            arrowLeft = ImageIO.read(ScenePanel.class.getResourceAsStream("/arrow_left.png"));
            arrowRight = ImageIO.read(ScenePanel.class.getResourceAsStream("/arrow_right.png"));
            explosionImage = ImageIO.read(ScenePanel.class.getResourceAsStream("/explosion.png"));
            gameOverImage = ImageIO.read(ScenePanel.class.getResourceAsStream("/gameOver.png"));
            winImage = ImageIO.read(ScenePanel.class.getResourceAsStream("/winner.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //SwingUtilities.invokeLater(() -> requestFocusInWindow());

    }
   private void makeButtonTransparent(JButton button) { //כפתור שקוף
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }


    @Override
    public void run() {
        try {

            repaint();
            Thread.sleep(500);

            countdown = 2;
            repaint();
            Thread.sleep(500);

            countdown = 1;
            repaint();
            Thread.sleep(500);

            countdown = 0;
            repaint();
            Thread.sleep(500);

            gameStarted = true;

            setFocusable(true);
            requestFocusInWindow();

            soundManager.playBackgroundMusic("/MUSICBEKROUND.wav");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            if (gameOver || win) {

                while (endScreenSize < 400) {

                    endScreenSize += 20;
                    repaint();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                playAgainButton.setVisible(true);
                homeButton.setVisible(true);

                break;
            }

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
                if (explosionFrames > 0) {
                    explosionFrames--;
                } else {
                    showExplosion = false;
                }
            }

            checkCollision();
            repaint(); // ציור מחדש

            try {
                Thread.sleep(50); // שליטה במהירות
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
                playSound("candy_Win.wav");

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
                playSound("explosion.wav");
                if (lives <= 0) {
                    lives = 0;
                    gameOver = true;
                    soundManager.stopBackgroundMusic();
                    soundManager.stopEffect();
                }

                // מיקום הפיצוץ
                explosionX = bombs[i].getX();
                explosionY = bombs[i].getY();
                showExplosion = true;
                explosionFrames = 20;

                // מחזיר את הפצצה למעלה
                bombs[i].setY(-50);
                bombs[i].setX(random.nextInt(800));
            }
        }
        if (score >= 300 && !win) {
            win = true;
            soundManager.stopBackgroundMusic();
            soundManager.stopEffect();
            if (!winSoundPlayed) {
                if (!soundMuted) {
                    soundManager.playEffect("/win.wav");
                }

                winSoundPlayed = true;
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

        g.drawImage(livesImages[lives], -20, 5, 230, 80, this);

        g.drawImage(scoreBar, 290, 3, 230, 90, this);

        g.setColor(new Color(90, 20, 90));
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(String.valueOf(score), 445, 56);

        if (soundMuted) {
            g.drawImage(soundOffIcon, 700, 10, 70, 70, this);
        }
        else {
            g.drawImage(soundIcon, 700, 10, 70, 70, this);
        }
        g.drawImage(arrowLeft, 10, 400, 65, 65, this);
        g.drawImage(arrowRight, 80, 400, 65, 65, this);
        if (gameOver) {
            g.drawImage(gameOverImage,
                    400 - endScreenSize / 2,
                    250 - endScreenSize / 3,
                    endScreenSize,
                    endScreenSize * 250 / 400,
                    this);
        }

        if (win) {
            g.drawImage(winImage,
                    400 - endScreenSize / 2,
                    250 - endScreenSize / 3,
                    endScreenSize,
                    endScreenSize * 250 / 400,
                    this);
        }
        if (!gameStarted) {

            String text;

            if (countdown == 3) {
                text = "3";
            }

            else if (countdown == 2) {
                text = "2";
            }

            else if (countdown == 1) {
                text = "1";
            }

            else {
                text = "GO!";
            }

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            Font font = new Font("Comic Sans MS", Font.BOLD, 140);

            g2d.setFont(font);

            FontMetrics fm = g2d.getFontMetrics();

            int textWidth = fm.stringWidth(text);

            int x = (getWidth() - textWidth) / 2;

            int y = getHeight() / 2 +50;

            // צל
            g2d.setColor(new Color(255, 105, 180));

            g2d.drawString(text, x + 6, y + 6);

            // כתב לבן
            g2d.setColor(Color.WHITE);

            g2d.drawString(text, x, y);
        }

    }
    public void playSound(String fileName) {
        if (soundMuted) {
            return;
        }
        try {
            // טעינת הקובץ מתיקיית ה-resources
            java.net.URL url = getClass().getResource("/" + fileName);
            if (url == null) {
                System.out.println("Error: Could not find file " + fileName);
                return;
            }

            javax.sound.sampled.AudioInputStream audioIn = javax.sound.sampled.AudioSystem.getAudioInputStream(url);
            javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioIn);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
