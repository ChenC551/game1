package org;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;

    public class StartPanel extends JPanel {
        // כפתורים
        public static final int BUTTON_WIDTH = 270;
        public static final int BUTTON_HEIGHT = 55;

        public static final int START_BUTTON_X = 265;
        public static final int START_BUTTON_Y = 250;

        public static final int INSTRUCTIONS_BUTTON_X = 265;
        public static final int INSTRUCTIONS_BUTTON_Y = 320;

        private BufferedImage backgroundImage;

        public StartPanel() {

            setLayout(null);

            try {
                InputStream inputStream = StartPanel.class.getResourceAsStream("/background.png");
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
                ScenePanel scenePanel = new ScenePanel();

                frame.setContentPane(scenePanel);
                frame.revalidate();
                frame.repaint();
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
        //כפתור שקוף:
        private void designButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setText("");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //


            }
        }

