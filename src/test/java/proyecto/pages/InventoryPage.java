package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class InventoryPage extends BasePage {

    private final By titulo = By.cssSelector("[data-test='title']");
    private final By nombres = By.cssSelector("[data-test='inventory-item-name']");
    private final By precios = By.cssSelector("[data-test='inventory-item-price']");
    private final By descripciones = By.cssSelector("[data-test='inventory-item-desc']");
    private final By imagenes = By.cssSelector(".inventory_item_img img");
    private final By selectorOrden = By.cssSelector("[data-test='product-sort-container']");
    private final By botonesAgregar = By.cssSelector("[data-test^='add-to-cart']");
    private final By botonesQuitar = By.cssSelector("[data-test^='remove']");
    private final By contadorCarrito = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By enlaceCarrito = By.cssSelector("[data-test='shopping-cart-link']");
    private final By botonMenu = By.id("react-burger-menu-btn");
    private final By opcionCerrarSesion = By.id("logout_sidebar_link");
    private final By opcionReiniciar = By.id("reset_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String tituloDePagina() {
        return textoDe(titulo);
    }

    // ----- Listado -----
    public int cantidadDeProductos() {
        return contarConEspera(nombres);
    }

    public int cantidadDeImagenes() {
        return contarConEspera(imagenes);
    }

    public List<String> nombresDeProductos() {
        return textosDe(nombres);
    }

    public List<String> preciosEnTexto() {
        return textosDe(precios);
    }

    public List<Double> preciosDeProductos() {
        return textosDe(precios).stream()
                .map(texto -> Double.parseDouble(texto.replace("$", "")))
                .toList();
    }

    public List<String> descripcionesDeProductos() {
        return textosDe(descripciones);
    }

    public void ordenarPor(String opcionVisible) {
        WebElement selector = espera.until(ExpectedConditions.visibilityOfElementLocated(selectorOrden));
        new Select(selector).selectByVisibleText(opcionVisible);
    }


    public void agregarProductos(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            clic(botonesAgregar);
            int esperados = i;
            espera.until(d -> d.findElements(botonesQuitar).size() == esperados);
        }
    }


    public void agregarProductoPorId(String id) {
        clic(By.cssSelector("[data-test='add-to-cart-" + id + "']"));
        espera.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='remove-" + id + "']")));
    }

    public int productosEnElCarrito() {
        List<WebElement> insignias = driver.findElements(contadorCarrito);
        return insignias.isEmpty() ? 0 : Integer.parseInt(insignias.get(0).getText());
    }

    public int botonesQuitarVisibles() {
        return contarSinEspera(botonesQuitar);
    }

    public CartPage abrirCarrito() {
        clicHastaQue(enlaceCarrito, ExpectedConditions.urlContains("cart.html"));
        return new CartPage(driver);
    }

    // ----- Menú -----
    public LoginPage cerrarSesion() {
        clicHastaQue(botonMenu, ExpectedConditions.visibilityOfElementLocated(opcionCerrarSesion));
        clicHastaQue(opcionCerrarSesion, ExpectedConditions.not(ExpectedConditions.urlContains("inventory.html")));
        return new LoginPage(driver);
    }

    public void reiniciarEstado() {
        clic(botonMenu);
        clic(opcionReiniciar);
    }

    public void clicAgregar(String idProducto) {
    By agregar = By.cssSelector("[data-test='add-to-cart-" + idProducto + "']");
    By quitar  = By.cssSelector("[data-test='remove-" + idProducto + "']");
    clicHastaQue(agregar, ExpectedConditions.presenceOfElementLocated(quitar));
}

public boolean botonRemoveVisible(String idProducto) {
    try {
        return contarConEspera(By.cssSelector("[data-test='remove-" + idProducto + "']")) > 0;
    } catch (TimeoutException e) {   
        return false;                
    }
}

}
