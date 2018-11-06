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

    public int getStep(){
        return step;
    }

    public Brain cloneBrain() {
        Vector[] newDirections = new Vector[this.directions.length];
        for(int i = 0; i < newDirections.length; i++) {
            newDirections[i] = this.directions[i].copy();
        }
        return new Brain(newDirections);
    }

    public Brain mutatedBrain() {
        double mutationRate = 0.02;
        Brain brain = cloneBrain();
        for(int i = 0; i < brain.directions.length; i++) {
            double rand = Math.random();
            if( rand <= mutationRate) {
                brain.directions[i] = randomVector();
            }
        }
        return brain;
    }

    private void fillRandomDirections() {
        Arrays.setAll(directions, i -> randomVector());
    }

    private Vector randomVector() {
        return Vector.fromAngle( (float) (Math.random() * 2 * Math.PI) );
    }
}
