package core;

import model.Goal;
import model.Obstacle;
import presentation.Graphics;

public class Orchestrator {

    private Swarm swarm;
    private Obstacle[] obstacles;
    private Goal goal;
    private Overseer overseer;

    public Orchestrator() {
        setUp();
        overseer = new Overseer(goal, swarm, obstacles);
    }

    private void setUp() {
        goal = new Goal(130, 650);
        swarm = new Swarm(150, 620, 150);
        obstacles = new Obstacle[]{
                new Obstacle(670, 100, 350, 30),
                new Obstacle(670, 550, 50, 30),

                new Obstacle(540, 100, 200, 30),
                new Obstacle(540, 350, 120, 30),

                new Obstacle(300, 470, 30, 270),
                new Obstacle(300, 600, 30, 200),
                new Obstacle(600, 600, 30, 100),

                new Obstacle(150, 100, 100, 100),
                new Obstacle(50, 400, 100, 100),
                new Obstacle(300, 250, 100, 100)
        };
    }

    public void showDispatcher(Graphics g) {
        goal.show(g);
        for (Obstacle obstacle : obstacles)
            obstacle.show(g);
        swarm.show(g);
    }

    public void nextIteration() {
        if (!swarm.areAllDotsDead()) {
            swarm.update();
            overseer.checkCollisions();
        } else {
            overseer.naturalSelection();
        }
    }
}
