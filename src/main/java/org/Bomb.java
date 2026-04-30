package org;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Bomb extends FallingObject {

    private BufferedImage image;

    public Bomb(int x, int y, int width, int height) {
        super(x, y, width, height);

        try {
            InputStream stream = getClass().getResourceAsStream("/bomb.png");
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void fall() {
        y += 5;
    }
}