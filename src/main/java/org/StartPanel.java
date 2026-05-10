package org;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;

    public class StartPanel extends JPanel { //מסך הפתיחה של המשחק זה פאנל גרפי שמוצג בתוך ה־GameFrame.
        // מיקום כפתורים
        public static final int BUTTON_WIDTH = 270;
        public static final int BUTTON_HEIGHT = 55;

        //מיקום כפתור START
        public static final int START_BUTTON_X = 265;
        public static final int START_BUTTON_Y = 250;

        //מיקום כפתור הוראות INSTRUCTIONS
        public static final int INSTRUCTIONS_BUTTON_X = 265;
        public static final int INSTRUCTIONS_BUTTON_Y = 320;

        //משתנה ששומר את תמונת הרקע.
        private BufferedImage backgroundImage;

        public StartPanel() { //הבנאי

            setLayout(null); //ביטלנו layout אוטומטי ומיקמנו כפתורים ידנית

            try {
                InputStream inputStream = StartPanel.class.getResourceAsStream("/background.png"); //טעינת תמונת רקע
                backgroundImage = ImageIO.read(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JButton startButton = new JButton("START GAME"); //יצירת כפתור START
            startButton.setBounds(START_BUTTON_X, START_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT); //מיקום וגודל
            designButton(startButton); //הופך אותו לשקוף.
            add(startButton); //בלי זה הכפתור לא יופיע.
            startButton.addActionListener(e -> { // גורמת לכפתור לעבור למסך של המשחק
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // מוצאת את החלון שבו הפאנל נמצא
                ScenePanel scenePanel = new ScenePanel(); //יוצר את המשחק עצמו.

                frame.setContentPane(scenePanel); //מחליף את StartPanel ב־ScenePanel.
                frame.revalidate();
                frame.repaint();
              });


            JButton instructionsButton = new JButton("INSTRUCTIONS"); //יוצר כפתור הוראות
            instructionsButton.setBounds(INSTRUCTIONS_BUTTON_X, INSTRUCTIONS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            designButton(instructionsButton);
            add(instructionsButton);

            instructionsButton.addActionListener(e -> {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.setContentPane(new InstructionsPanel()); //מחליף את StartPanel במסך הוראות
                frame.revalidate();
            });
        }
        @Override
        protected void paintComponent(Graphics g) {  //מצייר את הפאנל.
            super.paintComponent(g);  //מנקה ציור קודם.

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); //מצייר את תמונת הרקע על כל המסך.
        }


        private void designButton(JButton button) { //מעצבת כפתורים.

            //כפתור שקוף:
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        //בלי מסגרת
        button.setBorderPainted(false);
       //בלי focus
        button.setFocusPainted(false);

        button.setText("");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //כשהעכבר מעל הכפתור נהיה סימון יד


            }
        }
//✔ מסך הבית של המשחק
//✔ מציגה רקע
//✔ יוצרת כפתורים
//✔ מעבירה בין מסכים
//✔ מחברת את המשתמש למשחק עצמו
