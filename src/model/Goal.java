package model;

import presentation.Graphics;
import utils.Vector;

public class Goal implements Drawable, Collidable {

    private static final int RADIUS = 20;
    private static final int DIAMETER = RADIUS * 2;

    private int x;
    private int y;

    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void show(Graphics g) {
        g.setColor(Graphics.Color.GREEN);
        g.oval(this.x, this.y, DIAMETER, DIAMETER);
    }

    @Override
    public boolean collision(float dotX, float dotY, int radius) {
        return Vector.dist(new Vector(dotX + radius, dotY + radius), new Vector(x + RADIUS, y + RADIUS)) < RADIUS + radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
