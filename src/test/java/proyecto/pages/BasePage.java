package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import proyecto.base.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait espera;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("esperaSegundos")));
    }

    public void abrir(String ruta) {
        driver.get(Config.get("urlBase") + ruta);
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

    protected void clicHastaQue(By localizador, ExpectedCondition<?> efecto) {
        WebElement elemento = espera.until(ExpectedConditions.elementToBeClickable(localizador));
        elemento.click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(efecto);
        } catch (TimeoutException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(localizador));
            espera.until(efecto);
        }
    }

    protected String textoDe(By localizador) {
        return espera.until(ExpectedConditions.visibilityOfElementLocated(localizador)).getText();
    }

    protected List<String> textosDe(By localizador) {
        return espera.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizador))
                .stream().map(WebElement::getText).toList();
    }

    protected int contarConEspera(By localizador) {
        return espera.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizador)).size();
    }

    protected int contarSinEspera(By localizador) {
        return driver.findElements(localizador).size();
    }
}
