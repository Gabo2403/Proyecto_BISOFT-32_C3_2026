package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By titulo              = By.cssSelector("[data-test='title']");
    private final By campoNombre         = By.cssSelector("[data-test='firstName']");
    private final By campoApellido       = By.cssSelector("[data-test='lastName']");
    private final By campoCodigoPostal   = By.cssSelector("[data-test='postalCode']");
    private final By botonContinuar      = By.cssSelector("[data-test='continue']");
    private final By botonCancelar       = By.cssSelector("[data-test='cancel']");
    private final By mensajeError        = By.cssSelector("[data-test='error']");
    private final By etiquetaSubtotal    = By.cssSelector("[data-test='subtotal-label']");
    private final By etiquetaImpuesto    = By.cssSelector("[data-test='tax-label']");
    private final By etiquetaTotal       = By.cssSelector("[data-test='total-label']");
    private final By botonFinalizar      = By.cssSelector("[data-test='finish']");
    private final By encabezadoConfirmar = By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String tituloDePagina() {
        return textoDe(titulo);
    }

    // ----- Paso 1: datos del comprador -----
    public void llenarDatos(String nombre, String apellido, String codigoPostal) {
        escribir(campoNombre, nombre);
        escribir(campoApellido, apellido);
        escribir(campoCodigoPostal, codigoPostal);
    }

    public void continuar() {
        clic(botonContinuar);
    }

    public String textoDelError() {
        return textoDe(mensajeError);
    }

    public CartPage cancelar() {
        clic(botonCancelar);
        return new CartPage(driver);
    }

    // ----- Paso 2: resumen -----
    public String subtotal() {
        return textoDe(etiquetaSubtotal);
    }

    public String impuesto() {
        return textoDe(etiquetaImpuesto);
    }

    public String total() {
        return textoDe(etiquetaTotal);
    }

    public void finalizar() {
        clic(botonFinalizar);
    }

    // ----- Paso 3: confirmación -----
    public String mensajeDeConfirmacion() {
        return textoDe(encabezadoConfirmar);
    }
}
