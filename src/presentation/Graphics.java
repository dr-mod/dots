package presentation;

public interface Graphics {
    enum Color {
        RED, GREEN, BLUE, YELLOW, BLACK, WHITE
    }

    void rect(int x, int y, int width, int height);
    void oval(int x, int y, int width, int height);
    void text(int x, int y, String text);
    void setColor(Color color);
}
