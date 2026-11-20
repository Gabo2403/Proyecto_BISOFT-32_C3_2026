package proyecto.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/** Abre y cierra el navegador para CADA test: así cada test es independiente. */
@ExtendWith(CapturaAlFallar.class)
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void abrirNavegador() {
        boolean headless = Boolean.parseBoolean(Config.get("headless"));

        switch (Config.get("navegador").toLowerCase()) {
            case "firefox": {
                FirefoxOptions opciones = new FirefoxOptions();
                if (headless) opciones.addArguments("-headless");
                driver = new FirefoxDriver(opciones);
                break;
            }
            case "edge": {
                EdgeOptions opciones = new EdgeOptions();
                if (headless) opciones.addArguments("--headless=new");
                driver = new EdgeDriver(opciones);
                break;
            }
            default: {
                ChromeOptions opciones = new ChromeOptions();
                // Evita el aviso de "cambiar contraseña" de Chrome, que tapa la página
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                opciones.setExperimentalOption("prefs", prefs);
                opciones.addArguments("--window-size=1366,768");
                if (headless) opciones.addArguments("--headless=new");
                driver = new ChromeDriver(opciones);
            }
        }
        driver.get(Config.get("urlBase"));
    }

    @AfterEach
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    /** Guarda target/screenshots/<nombre>.png */
    public void capturar(String nombre) {
        try {
            Path carpeta = Paths.get("target", "screenshots");
            Files.createDirectories(carpeta);
            File origen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String archivo = nombre.replaceAll("[^a-zA-Z0-9_-]", "_") + ".png";
            Files.copy(origen.toPath(), carpeta.resolve(archivo), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.err.println("No se pudo tomar la captura: " + e.getMessage());
        }
    }
}
