package presentation;

import java.awt.*;

public class GraphicsAwt implements Graphics {

    private Graphics2D graphics2D;

    public GraphicsAwt(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    @Override
    public void rect(int x, int y, int width, int height) {
        graphics2D.fillRect(x, y, width, height);
    }

    @Override
    public void oval(int x, int y, int width, int height) {
        graphics2D.fillOval(x, y, width, height);
    }

    @Override
    public void circle(int x, int y, int radius) {
        graphics2D.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public void text(int x, int y, String text) {
        graphics2D.drawString(text, x, y);
    }

    @Override
    public void setColor(Color color) {
        graphics2D.setColor(translateColor(color));
    }

    private java.awt.Color translateColor(Color color) {
        switch (color) {
            case RED: return java.awt.Color.red;
            case GREEN: return java.awt.Color.green;
            case BLUE: return java.awt.Color.blue;
            case YELLOW: return java.awt.Color.yellow;
            case BLACK: return java.awt.Color.black;
            case WHITE: return java.awt.Color.white;
        }
        return null;
    }
}
