package core;

import configuration.ConfigHolder;
import model.Dot;
import model.Goal;
import model.Obstacle;

public class Overseer {

    private Goal goal;
    private Swarm swarm;
    private Obstacle[] obstacles;

    public Overseer(Goal goal, Swarm swarm, Obstacle[] obstacles) {
        this.goal = goal;
        this.swarm = swarm;
        this.obstacles = obstacles;
    }

    public void checkCollisions() {
        for (Dot dot : swarm.getDots()) {
            if (dot.isStopped()) continue;

            if (isOutOfBoundaries(dot) || isCollidedObstacle(dot)) {
                dot.stop();
            } else if (goal.collision(dot.getX(), dot.getY(), Dot.DOT_RADIUS)) {
                dot.goalReached();
                dot.stop();
            }
        }
    }

    public void naturalSelection() {
        GenerationHandler generation = new GenerationHandler(goal, swarm);
        Dot[] dots = generation.naturalSelection();
        swarm.replaceDots(dots);
        generation.getBestNumberOfSteps().ifPresent(steps -> System.out.println("Best number of steps: " + steps));
    }

    private boolean isOutOfBoundaries(Dot dot) {
        return dot.getX() < Dot.DOT_RADIUS ||
                dot.getY() < Dot.DOT_RADIUS ||
                dot.getX() > ConfigHolder.getInstance().getAreaWidth() - Dot.DOT_RADIUS ||
                dot.getY() > ConfigHolder.getInstance().getAreaHeight() - Dot.DOT_RADIUS;
    }

    private boolean isCollidedObstacle(Dot dot) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collision(dot.getX(), dot.getY(), Dot.DOT_RADIUS)) {
                return true;
            }
        }
        return false;
    }

}
