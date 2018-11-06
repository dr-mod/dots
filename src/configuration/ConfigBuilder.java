package configuration;

public class ConfigBuilder {
    private int areaWidth = 600;
    private int areaHeight = 600;
    private int refresh = 20;

    public ConfigBuilder setAreaWidth(int areaWidth) {
        this.areaWidth = areaWidth;
        return this;
    }

    public ConfigBuilder setAreaHeight(int areaHeight) {
        this.areaHeight = areaHeight;
        return this;
    }

    public ConfigBuilder setRefresh(int refresh) {
        this.refresh = refresh;
        return this;
    }

    public Config createConfig() {
        return new Config(areaWidth, areaHeight, refresh);
    }
}