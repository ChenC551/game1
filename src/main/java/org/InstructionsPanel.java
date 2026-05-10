package org;

import javax.swing.*;  //Swing → כפתורים, פאנלים, חלונות
import java.awt.*; //AWT → גרפיקה
import javax.imageio.ImageIO; //ImageIO → קריאת תמונות מקבצים
import java.awt.image.BufferedImage; //BufferedImage → שמירת תמונה בזיכרון
import java.io.InputStream; //InputStream → קריאת קבצים מתוך resources
import java.io.IOException; //IOException → טיפול בשגיאות קריאה

public class InstructionsPanel extends JPanel {  //תת חלון

    private BufferedImage backgroundImage; //משתנה ששומר את תמונת הרקע של מסך ההוראות

    public InstructionsPanel() { //זה מה שרץ כשאתה יוצר את המסך
        setLayout(null);

        try {
            InputStream inputStream = InstructionsPanel.class.getResourceAsStream("/instructions.jpeg");
            backgroundImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JButton backButton = new JButton("BACK"); // כפתור עם טקסט “BACK”
        backButton.setBounds(240, 400, 250, 65); // אם צריך, תכווני X/Y מיקום וגודל הכפתור
        designButton(backButton);
        add(backButton);//מוסיף את הכפתור לפאנל כדי שיראה במסך

        backButton.addActionListener(e -> { //כשילחצו על הכפתור → הקוד בפנים ירוץ
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); //מוצא את החלון הראשי שבו הפאנל נמצא
            frame.setContentPane(new StartPanel()); //מחליף את המסך חזרה ל־StartPanel (מסך פתיחה)
            frame.revalidate();
        });
    }

    @Override
    protected void paintComponent(Graphics g) { //מתודה שנקראת כל פעם שמציירים את המסך
        super.paintComponent(g); //מנקה את המסך לפני ציור חדש
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); //מצייר את תמונת הרקע על כל המסך
    }

    private void designButton(JButton button) { //פונקציה שמעצבת כפתור
        button.setOpaque(false); //הופך את הכפתור לשקוף
        button.setContentAreaFilled(false); //מבטל רקע של הכפתור
        button.setBorderPainted(false); //מבטל מסגרת
        button.setFocusPainted(false);
        button.setText(""); //מוחק את הטקסט (למרות שכתבתי "BACK
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

// יוצרת מסך הוראות
// מציירת תמונת רקע
// מוסיפה כפתור שחוזר למסך הפתיחה