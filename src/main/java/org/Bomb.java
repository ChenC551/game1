package org;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Bomb extends FallingObject {

    private BufferedImage image;

    public Bomb(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;

        try {
            InputStream stream = getClass().getResourceAsStream("/bomb.png");
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);

       /* g.setColor(Color.BLUE);
        Rectangle r = getRect();
        g.drawRect(r.x, r.y, r.width, r.height); */
    }

    public Rectangle getRect() {
        return new Rectangle(x+80, y + 100, 30, 20);
    }

}
