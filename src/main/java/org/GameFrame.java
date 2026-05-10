package org;

import javax.swing.*;

public class GameFrame { //המחלקה הזאת היא החלון הראשי של המשחק

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 500;
    public GameFrame() {
        JFrame window = new JFrame("My game"); //זה יוצר חלון חדש עם כותרת "My game"
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //גודל החלון .רוחב: 800 , גובה: 500
        window.setResizable(false); //המשתמש לא יכול לשנות את גודל החלון
        window.setLocationRelativeTo(null); //ממקם את החלון באמצע המסך
        window.setLayout(null); //יש כל מיני סוגים של LAYOUT כמו גריד וכו ואנחנו רוצים שלא הוא יקבע לנו אלא שתהיה שליטה מלאה שלי וקביעה בדיוק באיזה X וY (פיקסל) יתחיל כל רכיב ומה יהיה הגודל המדוייק שלו
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//כשסוגרים את החלון → התוכנית נסגרת לגמרי

        window.setContentPane(new StartPanel()); // חיבור הstatrPanel לחלון הראשי
        window.setVisible(true); //הצגת החלון בלי זה — החלון לא יופיע בכלל
    }
}
//GameFrame = המעטפת של המשחק
//
//אבל:
//
//לא מציירת משחק
//לא מנהלת לוגיקה
//רק מחזיקה את המסך (StartPanel / GamePane