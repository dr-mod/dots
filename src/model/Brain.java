package model;

import utils.Vector;

import java.util.Arrays;

public class Brain {
    private Vector[] directions;
    private int step = 0;

    public Brain(int size) {
        directions = new Vector[size];
        fillRandomDirections();
    }

    public Brain(Vector[] directions) {
        this.directions = directions;
    }

    public Vector nextStep() {
        return directions[step++];
    }

    public boolean hasNextStep() {
        return directions.length > step;
    }

    public int getStep() {
        return step;
    }

    public Brain cloneBrain() {
        return new Brain(directions.clone());
    }

    public static Brain mergeBrains(Brain brain, Brain brain2) {
        final int PORTION = 20;
        if (brain.directions.length != brain2.directions.length)
            throw new IllegalArgumentException("Brains' direction lengths are different.");
        if (brain.directions.length % PORTION != 0)
            throw new RuntimeException("Brain's direction length cannot be divided by " + PORTION);

        var mergedDirections = new Vector[brain.directions.length];
        for (int i = 0; i < mergedDirections.length; i += PORTION) {
            double random = Math.random();
            if (random < 0.9) {
                System.arraycopy(brain.directions, i, mergedDirections, i, PORTION);
            } else {
                System.arraycopy(brain2.directions, i, mergedDirections, i, PORTION);
            }
        }

        return new Brain(mergedDirections);
    }

    public Brain mutatedBrain() {
        double mutationRate = 0.02;
        Brain brain = cloneBrain();
        for (int i = 0; i < brain.directions.length; i++) {
            double mutationRand = Math.random();
            if (mutationRand <= mutationRate) {
//                double rotation = (Math.random() * Math.PI) - Math.PI / 2.0;
//                brain.directions[i].rotate((float) rotation);
                brain.directions[i] = randomVector();
            }
        }
        return brain;
    }

    private void fillRandomDirections() {
        Arrays.setAll(directions, i -> randomVector());
    }

    private Vector randomVector() {
        return Vector.fromAngle((float) (Math.random() * 2 * Math.PI));
    }
}
