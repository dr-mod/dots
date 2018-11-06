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
            if (dot.isStoped()) continue;

            if (isOutOfBoundaries(dot) || isCollidedObstacle(dot)) {
                dot.stop();
            } else if (goal.collision(dot.getX(), dot.getY(), Dot.RADIUS)) {
                dot.goalReached();
                dot.stop();
            }
        }
    }

    public void naturalSelection() {
        GenerationHandler generation = new GenerationHandler(goal, swarm.getDots());
        Dot[] dots = generation.naturalSelection();
        swarm.setNewDots(dots);
        generation.getBestNumberOfSteps().ifPresent(steps -> System.out.println("Best number of steps: " + steps));
    }

    private boolean isOutOfBoundaries(Dot dot) {
        return dot.getX() < 0 ||
                dot.getY() < 0 ||
                dot.getX() > ConfigHolder.getInstance().getAreaWidth() - Dot.DIAMETER ||
                dot.getY() > ConfigHolder.getInstance().getAreaHeight() - Dot.DIAMETER;
    }

    private boolean isCollidedObstacle(Dot dot) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collision(dot.getX(), dot.getY(), Dot.RADIUS)) {
                return true;
            }
        }
        return false;
    }

}
