package org;

import javax.swing.*;

public class GameFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 500;
    public GameFrame() {
        JFrame window = new JFrame("My game");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setContentPane(new StartPanel()); // חיבור הstatrPanel לחלון הראשי
        window.setVisible(true);
    }
}