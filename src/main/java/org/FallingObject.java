package org;
import java.awt.*;

    public class FallingObject {

        protected int x;
        protected int y;
        protected int width;
        protected int height;

        public FallingObject(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void fall() {
            y += 5;
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }
    }