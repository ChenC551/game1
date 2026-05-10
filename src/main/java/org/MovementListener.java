package org;
import java.awt.event.KeyEvent; //מידע על מקש שנלחץ
import java.awt.event.KeyListener; //ממשק שמאפשר להקשיב

public class MovementListener implements KeyListener { //אחראית להקשיב ללחיצות מקלדת ולהזיז את השחקן בהתאם.

    private Player player;//כדי שכאשר לוחצים על מקשים —
//נוכל לשנות את התנועה של השחקן.

    public MovementListener(Player player) {   //כשיוצרים את ה־listener, שולחים אליו את השחקן שעליו הוא ישלוט.
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {  //הפונקציה רצה ברגע שלוחצים על מקש
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {         //בודק אם נלחץ החץ הימני
            player.setDirection(Player.RIGHT);            //משנה את כיוון התנועה של השחקן לימין
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { //בודק אם נלחץ החץ השמאלי
            player.setDirection(Player.LEFT);            //מזיז את השחקן שמאלה
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //הפונקציה רצה כשעוזבים את המקש
         player.setDirection(Player.HOLD); //אומר לשחקן תפסיק לזוז
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
 //הזרימה בפועל?
//המשתמש לוחץ →
//keyPressed()
//ה־listener משנה direction →
//player.setDirection(...)
//ה־Game Loop מעדכן →
//השחקן זז
//המשתמש עוזב →
//keyReleased()
//direction = HOLD →
//השחקן עוצר

// שורה תחתונה
//המחלקה הזאת:
//✔ מאזינה למקלדת
//✔ מזהה חצים
//✔ משנה את כיוון השחקן
//✔ לא מזיזה בפועל — רק מעדכנת direction
//התנועה האמיתית כנראה קורית בתוך Player.update() או move() בלולאת המשחק