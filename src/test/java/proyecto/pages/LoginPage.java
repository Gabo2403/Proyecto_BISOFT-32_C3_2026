package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import proyecto.base.Config;

public class LoginPage extends BasePage {

    private final By campoUsuario = By.id("user-name");
    private final By campoClave   = By.id("password");
    private final By botonLogin   = By.id("login-button");
    private final By mensajeError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String usuario, String clave) {
        escribir(campoUsuario, usuario);
        escribir(campoClave, clave);
        clic(botonLogin);
    }

    public InventoryPage iniciarSesionComo(String usuario) {
        iniciarSesion(usuario, Config.get("claveComun"));
        return new InventoryPage(driver);
    }

    public String textoDelError() {
        return textoDe(mensajeError);
    }

    public boolean botonLoginVisible() {
        return espera.until(ExpectedConditions.visibilityOfElementLocated(botonLogin)).isDisplayed();
    }
}
