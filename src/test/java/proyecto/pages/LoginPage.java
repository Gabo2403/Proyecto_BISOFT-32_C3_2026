package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Página de inicio de sesión: localizadores y acciones. SIN asserts. */
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

    public String textoDelError() {
        return textoDe(mensajeError);
    }
}
