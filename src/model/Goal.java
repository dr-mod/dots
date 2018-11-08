package model;

import presentation.Graphics;
import utils.Vector;

public class Goal implements Drawable, Collidable {

    private static final int GOAL_RADIUS = 20;

    private int x;
    private int y;

    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void show(Graphics g) {
        g.setColor(Graphics.Color.GREEN);
        g.circle(this.x, this.y, GOAL_RADIUS);
    }

    @Override
    public boolean collision(float dotX, float dotY, int radius) {
        return Vector.dist(new Vector(dotX, dotY), new Vector(x, y))
            < GOAL_RADIUS + radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
