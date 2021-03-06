package model;

import presentation.Graphics;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;


public class Dot implements Drawable {

    public final static int DOT_RADIUS = 2;
    private final static int MAX_NUMBER_OF_STEPS = 400;

    protected Vector position;
    private Vector velocity = new Vector(0, 0);
    private Brain brain;
    private List<Vector> path = new ArrayList<>(MAX_NUMBER_OF_STEPS);

    private boolean stop = false;
    private boolean goalReached = false;

    public Dot(int x, int y) {
        this(new Brain(MAX_NUMBER_OF_STEPS), x, y);
    }

    public Dot(Brain brain, int x, int y) {
        this.brain = brain;
        position = new Vector(x, y);
    }

    @Override
    public void show(Graphics g) {
        g.setColor(Graphics.Color.WHITE);
        g.circle((int) position.x, (int) position.y, DOT_RADIUS);
    }

    public void update() {
        if (stop) return;
        move();
        path.add(position);
    }

    private void move() {
        if (!brain.hasNextStep()) {
            stop = true;
            return;
        }

        velocity = velocity.add(brain.nextStep()).limit(4);
        position = position.add(velocity);
    }

    public boolean isStopped() {
        return stop;
    }

    public Brain getBrain() {
        return brain;
    }

    public int getX() {
        return (int) position.x;
    }

    public int getY() {
        return (int) position.y;
    }

    public void stop() {
        this.stop = true;
    }

    public boolean isGoalReached() {
        return this.goalReached;
    }

    public void goalReached() {
        this.stop = true;
        this.goalReached = true;
    }

    public List<Vector> getPath() {
        return path;
    }

    public Dot toBestDot() {
        return new BestDot(brain, (int) position.x, (int) position.y);
    }
    static public class BestDot extends Dot {


        public BestDot(Brain brain, int x, int y) {
            super(brain, x, y);
        }

        @Override
        public void show(Graphics g) {
            g.setColor(Graphics.Color.BLUE);
            g.circle((int) position.x, (int) position.y, DOT_RADIUS * 2);
        }

    }
}
