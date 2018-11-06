package core;

import model.Dot;
import presentation.Graphics;

import java.util.Arrays;

public class Swarm {

    private Dot[] dots;
    private int generation = 0;

    public Swarm(int size) {
        dots = new Dot[size];
        Arrays.setAll(dots, i -> new Dot());
    }

    public void show(Graphics g) {
        for (Dot dot : dots) {
            dot.show(g);
        }
        g.setColor(Graphics.Color.WHITE);
        g.text( 20, 800 - 20, "Generation " + generation);
    }

    public void update() {
        for (Dot dot : dots) {
            dot.update();
        }
    }

    public boolean areAllDotsDead() {
        for (Dot dot : dots) {
            if (!dot.isStoped()) return false;
        }
        return true;
    }

    public void setNewDots(Dot[] dots) {
        this.dots = dots;
        this.generation++;
    }

    Dot[] getDots() {
        return dots;
    }

}
