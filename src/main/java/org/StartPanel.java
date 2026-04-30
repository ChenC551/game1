package org;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;

    public class StartPanel extends JPanel {
        // כפתורים
        public static final int BUTTON_WIDTH = 260;
        public static final int BUTTON_HEIGHT = 55;

        public static final int START_BUTTON_X = 270;
        public static final int START_BUTTON_Y = 280;

        public static final int INSTRUCTIONS_BUTTON_X = 270;
        public static final int INSTRUCTIONS_BUTTON_Y = 350;

        private BufferedImage backgroundImage;

        public StartPanel() {

            setLayout(null);

            try {
                InputStream inputStream = StartPanel.class.getResourceAsStream("/background.jpeg");
                backgroundImage = ImageIO.read(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JButton startButton = new JButton("START GAME");
            startButton.setBounds(START_BUTTON_X, START_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            designButton(startButton);
            add(startButton);
            startButton.addActionListener(e -> { // גורמת לכפתור לעבור למסך של המשחק
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // מוצאת את החלון שבו הפאנל נמצא
                    frame.setContentPane(new ScenePanel()); //מחליף את המסך למסך של השמחק (הscenePanel)
                    frame.revalidate(); // מרענן את המסך
                });


            JButton instructionsButton = new JButton("INSTRUCTIONS");
            instructionsButton.setBounds(INSTRUCTIONS_BUTTON_X, INSTRUCTIONS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            designButton(instructionsButton);
            add(instructionsButton);
            instructionsButton.addActionListener(e -> {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.setContentPane(new InstructionsPanel());
                frame.revalidate();
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        private void designButton(JButton button) {
            button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
            button.setForeground(new Color(150, 40, 140));
            button.setBackground(new Color(230, 255, 245));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(210, 80, 170), 3));
        }
    }