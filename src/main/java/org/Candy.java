package org;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Candy extends FallingObject {

    private BufferedImage image;  //משתנה שיכיל את התמונה של הסוכריה
//BufferedImage הוא משתנה ששומר את נתוני התמונה (הפיקסלים) בזיכרון ה-RAM של המחשב, כדי שניתן יהיה לצייר אותה על המסך במהירות וביעילות בזמן ריצת המשחק.
    public Candy(int x, int y, int width, int height, int speed) {   //פונקציה שמופעלת כשיוצרים סוכריה חדשה מקבלת מיקוםXוY גודל ומהירות נפילה
        super(x, y, width, height); //שולחת את הנתונים למחלקת האב כדי שתעדכן את המיקום והגודל הבסיסיים
        this.speed = speed;// מעדכנת את המהירות האובייקט הנוכחי לפי הערך שהתקבל
        try { // ניסיון לטעון את הקובץ
            InputStream stream = getClass().getResourceAsStream("/candy.png"); //פותח ערוץ לקריאת קובץ התמונה שנמצא בתיקיית המשאבים של הפרוייקט
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fall () { // פונקציה שגורמת לסוכריה ליפול הערך גדל ב5 פיקסלים
        y+=5;

    }

    public Rectangle getRect(){//מחזירה מלבן וירטואלי שמייצג את גבולות הסוכרייה. זה משמש בדרך כלל לזיהוי התנגשויות (למשל, אם השחקן נגע בסוכרייה). המלבן כאן קטן יותר מהתמונה עצמה (מוסט ב-x+40 ו-y+62).
        return  new Rectangle(x+40,y+62,50,30);
    }
    @Override

    public void draw(Graphics g) { //המתודה הזו היא "הציירת" של המחלקה; היא אחראית לקחת את המידע הגרפי ולהציג אותו פיזית על המסך בכל פריים של המשחק.

        g.drawImage(image, x, y, width, height, null);

        /* g.setColor(Color.BLUE);
        Rectangle r = getRect();
        g.drawRect(r.x, r.y, r.width, r.height); */
    }
}