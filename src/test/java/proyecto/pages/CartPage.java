package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By titulo = By.cssSelector("[data-test='title']");
    private final By productos = By.cssSelector("[data-test='inventory-item']");
    private final By botonCheckout = By.cssSelector("[data-test='checkout']");
    private final By botonSeguirComprando = By.cssSelector("[data-test='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int cantidadDeProductos() {
        textoDe(titulo); 
        return contarSinEspera(productos);
    }

    public CheckoutPage irACheckout() {
        clicHastaQue(botonCheckout, ExpectedConditions.urlContains("checkout-step-one"));
        return new CheckoutPage(driver);
    }

    public InventoryPage seguirComprando() {
        clic(botonSeguirComprando);
        return new InventoryPage(driver);
    }

    public boolean botonRemoveVisible(String idProducto) {
        return contarConEspera(By.cssSelector("[data-test='remove-" + idProducto + "']")) > 0;
    }

}