package core;

import model.Brain;
import model.Dot;
import model.Goal;
import utils.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenerationHandler {

    private Swarm swarm;
    private Goal goal;
    private Dot bestDot;

    public GenerationHandler(Goal goal, Swarm swarm) {
        this.goal = goal;
        this.swarm = swarm;
    }

    public Dot[] naturalSelection() {
        List<DotHolder> fitnessEnrichedDots = Arrays.stream(swarm.getDots())
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

        Dot[] dots = generateNewDots(fitnessEnrichedDots, fitnessSum);

        return dots;
    }

    private Dot[] generateNewDots(List<DotHolder> fitnessEnrichedDots, float fitnessSum) {
        Dot[] dots = new Dot[this.swarm.getDots().length];
        Dot previousBestDot = bestDot;
        dots[0] = swarm.newDot(previousBestDot.getBrain().cloneBrain()).toBestDot();

        for (int i = 1; i < dots.length; i++) {
            DotHolder ancestor1 = randomFitnessBiasedDot(fitnessEnrichedDots, fitnessSum);
            DotHolder ancestor2 = randomFitnessBiasedDot(fitnessEnrichedDots, fitnessSum);
            Brain mergedBrain = Brain.mergeBrains(ancestor1.getDot().getBrain(), ancestor2.getDot().getBrain());

            dots[i] = swarm.newDot(mergedBrain.mutatedBrain());
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

//    private float calculateFitness(Dot dot) {
//        float fitness = dot.getPath().stream()
//                .map((v) -> 1.0f / Vector.dist(v, new Vector(goal.getX(), goal.getY())))
//                .reduce(0.0f, (accumulator, elem) -> accumulator + elem);
//
//        return fitness;
//    }

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
