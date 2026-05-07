package org;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class Player {
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int HOLD = 0;
    private int direction = HOLD;
    // משתנה המחלקה שיחזיק את התמונה
    private BufferedImage playerImage;
    private int x;
    private int y;
    private int width = 210; //גודל הדמות
    private int height = 240;
    private int speed = 15;


    public Player(int x, int y) {
        this.x = x;
        this.y  = y;

        try {
            // טעינת הנתיב של התמונה מתוך תיקיית ה-resources
            URL imgUrl = getClass().getResource("/player.png");

            if (imgUrl == null) {
                System.err.println("Error: Could not find image file at /player.png");
            } else {
                // התיקון הקריטי: השמה ישירה למשתנה המחלקה
                this.playerImage = ImageIO.read(imgUrl);
            }
        } catch (IOException e) {
            System.err.println("Error reading the image file!");
            e.printStackTrace();
        }
    }public Rectangle getRect() {
        return new Rectangle(x + 90, y+40, 50, 30);
    }


    public void setDirection(int dir) {
        this.direction = dir;
    }


    public void move(int panelWidth) {
        if (direction == RIGHT) {
            x += speed;
        } else if (direction == LEFT) {
            x -= speed;
        }
        if (x<-40){
            x=-40;
        }
        if (x > panelWidth - width+40) {
            x = panelWidth - width;
        }
    }


    public void draw(Graphics g) {
        if (playerImage != null) {

            g.drawImage(playerImage, x, y, width, height, null);

           /* g.setColor(Color.RED);
            Rectangle r = getRect();
            g.drawRect(r.x, r.y, r.width, r.height); */

        } else {

            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}