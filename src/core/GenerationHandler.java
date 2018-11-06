package core;

import model.Dot;
import model.Goal;
import utils.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenerationHandler {

    private Dot[] dots;
    private Goal goal;
    private Dot bestDot;

    public GenerationHandler(Goal goal, Dot[] dots) {
        this.goal = goal;
        this.dots = dots;
    }

    public Dot[] naturalSelection() {
        List<DotHolder> fitnessEnrichedDots = Arrays.stream(dots)
                .map(dot -> new DotHolder(dot, calculateFitness(dot)))
                .collect(Collectors.toList());

        this.bestDot = fitnessEnrichedDots.stream()
                .max((x, y) -> x.getFitness() >= y.getFitness() ? 1 : -1)
                .get()
                .getDot();

        float fitnessSum = (float) fitnessEnrichedDots
                .stream()
                .mapToDouble(DotHolder::getFitness)
                .reduce(0, (x, y) -> x + y);

        Dot[] dots = new Dot[this.dots.length];
        Dot previousBestDot = bestDot;
        dots[0] = new Dot.BestDot(previousBestDot.getBrain().cloneBrain());

        for (int i = 1; i < dots.length; i++) {
            DotHolder ancestor = randomFitnessBiasedDot(fitnessEnrichedDots, fitnessSum);
            dots[i] = new Dot(ancestor.getDot().getBrain().mutatedBrain());
        }

        return dots;
    }

    public Optional<Integer> getBestNumberOfSteps() {
        if (!this.bestDot.isGoalReached()) return Optional.empty();
        return Optional.of(this.bestDot.getBrain().getStep());
    }

    private DotHolder randomFitnessBiasedDot(List<DotHolder> dotHolders, float fitnessSum) {
        double random = Math.random() * fitnessSum;

        float sum = 0;
        for (DotHolder dot : dotHolders) {
            sum += dot.getFitness();
            if (sum >= random) {
                return dot;
            }
        }

        return dotHolders.get(0);
    }

    private float calculateFitness(Dot dot) {
        float fitness;
        if (dot.isGoalReached()) {
            fitness = (float) (0.0625 + 10000.0 / Math.pow(dot.getBrain().getStep(), 2));
        } else {
            float destinationToGoal = Vector.dist(new Vector(dot.getX(), dot.getY()), new Vector(goal.getX(), goal.getY()));
            fitness = 1 / (destinationToGoal * destinationToGoal);
        }
        return fitness;
    }

    private class DotHolder {

        private Dot dot;
        float fitness;

        public DotHolder(Dot dot, float fitness) {
            this.dot = dot;
            this.fitness = fitness;
        }

        public Dot getDot() {
            return dot;
        }

        public float getFitness() {
            return fitness;
        }
    }
}
