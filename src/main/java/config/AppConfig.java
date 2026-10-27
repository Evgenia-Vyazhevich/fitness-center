package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {
    private static final Properties PROPERTIES = loadProperties();

    private AppConfig() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = AppConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("Не найден application.properties");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Не удалось прочитать настройки приложения", exception);
        }
    }

    public static int getMaxParticipants() {
        return Integer.parseInt(PROPERTIES.getProperty("training.maxParticipants"));
    }

    public static String getProfileName() {
        return PROPERTIES.getProperty("app.profile.name");
    }
}
