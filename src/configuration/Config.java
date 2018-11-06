package configuration;

public class Config {

    private final int areaWidth;
    private final int areaHeight;
    private final int refresh;

    public Config(int areaWidth, int areaHeight, int refresh) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.refresh = refresh;
    }

    public int getAreaWidth() {
        return areaWidth;
    }

    public int getAreaHeight() {
        return areaHeight;
    }

    public int getRefresh() {
        return refresh;
    }
}
