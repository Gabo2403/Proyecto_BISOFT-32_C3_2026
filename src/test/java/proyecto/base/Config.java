package proyecto.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = Config.class.getResourceAsStream("/config.properties")) {
            if (in == null) {
                throw new IllegalStateException("No se encontró config.properties en src/test/resources");
            }
            PROPS.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer config.properties", e);
        }
    }

    private Config() { }

    public static String get(String clave) {
        return System.getProperty(clave, PROPS.getProperty(clave));
    }

    public static int getInt(String clave) {
        return Integer.parseInt(get(clave));
    }
}
