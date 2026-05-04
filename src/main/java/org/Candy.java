package org;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Candy extends FallingObject {

    private BufferedImage image;

    public Candy(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;

        try {
            InputStream stream = getClass().getResourceAsStream("/candy.png");
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fall (){
        y+=5;
    }

    public Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}