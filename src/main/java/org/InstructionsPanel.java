package org;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {

        public InstructionsPanel() {
            setLayout(null);
            setBackground(new Color(255, 220, 240));

            JLabel title = new JLabel("HOW TO PLAY", SwingConstants.CENTER);
            title.setBounds(250, 50, 400, 60);
            title.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
            title.setForeground(new Color(150, 40, 140));
            add(title);

            JLabel instructions = new JLabel(
                    "<html><div style='text-align:center;'>"
                            + "Move the girl with the arrow keys.<br><br>"
                            + "Catch candies and stars to earn points.<br><br>"
                            + "Avoid bombs — they take away lives.<br><br>"
                            + "The goal is to collect as many sweets as possible!"
                            + "</div></html>",
                    SwingConstants.CENTER
            );

            instructions.setBounds(170, 140, 560, 220);
            instructions.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
            instructions.setForeground(new Color(100, 40, 120));
            add(instructions);
        }
    }

