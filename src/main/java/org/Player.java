package org;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class Player { //המחלקה שמייצגת את הדמות עצמה

    //ערכים קבועים שמייצגים מצב תנועה
    public static final int RIGHT = 1; //ימינה
    public static final int LEFT = 2; //ימינה
    public static final int HOLD = 0; //עצירה
    private int direction = HOLD;     //שומר לאיזה כיוון השחקן כרגע זז.
    private BufferedImage playerImage; // משתנה המחלקה שיחזיק את התמונה

    private int x; //מיקום השחקן על המסך.
    private int y; //מיקום השחקן על המסך.
    private int width = 210; // (התמונה של השחקן) גודל הדמות
    private int height = 240;// (התמונה של השחקן) גודל הדמות
    private int speed = 15;


    public Player(int x, int y) { // מיקום התחלתי
        this.x = x;
        this.y  = y;

        try {
            // טעינת הנתיב של התמונה מתוך תיקיית ה-resources
            URL imgUrl = getClass().getResource("/player.png");
//בדיקה אם הקובץ קיים
            if (imgUrl == null) { //אם התמונה לא נמצאה.
                System.err.println("Error: Could not find image file at /player.png"); //מדפיס שגיאה
            } else {
                // התיקון הקריטי: השמה ישירה למשתנה המחלקה
                this.playerImage = ImageIO.read(imgUrl);
            }
        } catch (IOException e) {
            System.err.println("Error reading the image file!");
            e.printStackTrace();
        }
    }public Rectangle getRect() { //יוצר מלבן להתנגשות. המלבן קטן יותר מהתמונה כדי שהפגיעה תהיה מדויקת.
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
        if (x<-40){ //גבול שמאל של המסך
            x=-40;//מונע מהשחקן לצאת יותר מדי שמאלה
        }
        if (x > panelWidth - width+40) { //גבול ימין של המסך בודק אם עבר את צד ימין
            x = panelWidth - width; // מחזיר אותו פנימה
        }
    }


    public void draw(Graphics g) { //מצייר את הדמות.
        if (playerImage != null) { //אם התמונה קיימת

            g.drawImage(playerImage, x, y, width, height, null); //מצייר את התמונה על המסך.

           /* g.setColor(Color.RED);
            Rectangle r = getRect();
            g.drawRect(r.x, r.y, r.width, r.height); */

        } else {
            //אם התמונה לא נטענה:
            //יצייר מלבן אדום
            //כדי שלא “ייעלם” השחקן
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
//✔ שומרת את מצב השחקן
//✔ טוענת את התמונה
//✔ מזיזה את הדמות
//✔ מציירת אותה
//✔ מטפלת בהתנגשויות
//✔ שומרת גבולות מסך