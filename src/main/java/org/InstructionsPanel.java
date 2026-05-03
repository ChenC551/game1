package org;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;

public class InstructionsPanel extends JPanel {

    private BufferedImage backgroundImage;

    public InstructionsPanel() {
        setLayout(null);

        try {
            InputStream inputStream = InstructionsPanel.class.getResourceAsStream("/instructions.png");
            backgroundImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JButton backButton = new JButton("BACK");
        backButton.setBounds(240, 400, 250, 65); // אם צריך, תכווני X/Y
        designButton(backButton);
        add(backButton);

        backButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new StartPanel());
            frame.revalidate();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void designButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setText("");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}