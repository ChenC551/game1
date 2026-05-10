package org;
import java.awt.*;

    public class FallingObject {

        protected int x;
        protected int y;
        protected int width;
        protected int height;
        protected  int speed;

        public FallingObject(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void fall() {
            y += 5;
            y += speed;
        }

        public void draw(Graphics g) { //זו מתודת ברירת מחדל שנועדה לתת ציור גנרי שמייצר עיגול אדום במקרה שמחלקה יורשת לא מחליפה אותה עושה ovveride.
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }

        public int getX() {
            return x;
        }

        public int getWidth() {
            return width;
        }

        public int getY() {
            return y;
        }

        public int getHeight() {
            return height;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setY(int y) {
            this.y = y;
        }
    }