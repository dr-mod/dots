package model;

import presentation.Graphics;

public class Obstacle implements Drawable, Collidable {

    private int x;
    private int y;
    private int height;
    private int width;

    public Obstacle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public void show(Graphics g) {
        g.setColor(Graphics.Color.YELLOW);
        g.rect(x, y, width, height);
    }

    @Override
    public boolean collision(float dotX, float dotY, int radius) {
        return (dotX + radius > x &&
                dotX - radius < (x + width) &&
                dotY + radius > y &&
                dotY - radius < (y + height));
    }

}
