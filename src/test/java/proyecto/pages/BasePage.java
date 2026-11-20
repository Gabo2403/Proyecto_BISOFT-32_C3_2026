package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import proyecto.base.Config;

import java.time.Duration;

/** Acciones comunes con esperas explícitas. Nunca Thread.sleep. */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait espera;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("esperaSegundos")));
    }

    protected void escribir(By localizador, String texto) {
        WebElement campo = espera.until(ExpectedConditions.visibilityOfElementLocated(localizador));
        campo.clear();
        if (texto != null && !texto.isEmpty()) {
            campo.sendKeys(texto);
        }
    }

    protected void clic(By localizador) {
        espera.until(ExpectedConditions.elementToBeClickable(localizador)).click();
    }

    protected String textoDe(By localizador) {
        return espera.until(ExpectedConditions.visibilityOfElementLocated(localizador)).getText();
    }
}
