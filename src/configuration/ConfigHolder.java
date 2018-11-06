package configuration;

public class ConfigHolder {

    private static final Config config = new ConfigBuilder()
            .setAreaHeight(800)
            .setAreaWidth(800)
            .setRefresh(20)
            .createConfig();

    private ConfigHolder() {

    }

    public static Config getInstance() {
        return config;
    }

}
