package model;

import presentation.Graphics;
import utils.Vector;


public class Dot implements Drawable {

    public final static int RADIUS = 2;
    public final static int DIAMETER = RADIUS * 2;
    private final static int MAX_NUMBER_OF_STEPS = 400;

    protected Vector position;
    private Vector velocity = new Vector(0, 0);
    private Brain brain;

    private boolean stop = false;
    private boolean goalReached = false;

    public Dot() {
        this(new Brain(MAX_NUMBER_OF_STEPS));
    }

    public Dot(Brain brain) {
        this.brain = brain;
        position = new Vector(620, 150);
    }

    @Override
    public void show(Graphics g) {
        g.setColor(Graphics.Color.WHITE);
        g.oval((int) position.x, (int) position.y, DIAMETER, DIAMETER);
    }

    public void update() {
        if (stop) return;
        move();
    }

    private void move() {
        if (!brain.hasNextStep()) {
            stop = true;
            return;
        }

        velocity.add(brain.nextStep());
        velocity.limit(4);
        position.add(velocity);
    }

    public boolean isStoped() {
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

    static public class BestDot extends Dot {

        public BestDot(Brain brain) {
            super(brain);
        }

        @Override
        public void show(Graphics g) {
            g.setColor(Graphics.Color.BLUE);
            g.oval((int) position.x, (int) position.y, DIAMETER * 2, DIAMETER * 2);
        }

    }
}
