package core;

import configuration.ConfigHolder;
import java.util.Arrays;
import model.Brain;
import model.Dot;
import presentation.Graphics;

public class Swarm {

    private Dot[] dots;
    private int generation = 0;

    private int startingX;
    private int startingY;

    public Swarm(int size, int x, int y) {
        this.startingX = x;
        this.startingY = y;
        dots = new Dot[size];
        Arrays.setAll(dots, i -> new Dot(startingX, startingY));
    }

    public void show(Graphics g) {
        for (Dot dot : dots) {
            dot.show(g);
        }
        g.setColor(Graphics.Color.WHITE);
        g.text(20, ConfigHolder.getInstance().getAreaHeight() - 20, "Generation " + generation);
    }

    public void update() {
        for (Dot dot : dots) {
            dot.update();
        }
    }

    public boolean areAllDotsDead() {
        for (Dot dot : dots) {
            if (!dot.isStopped()) {
                return false;
            }
        }
        return true;
    }

    public void replaceDots(Dot[] dots) {
        this.dots = dots;
        this.generation++;
    }

    public Dot newDot(Brain brain) {
        return new Dot(brain, startingX, startingY);
    }

    Dot[] getDots() {
        return dots;
    }

}
