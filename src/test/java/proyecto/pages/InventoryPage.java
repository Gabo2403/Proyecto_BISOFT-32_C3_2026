package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Página de inventario (productos). Más acciones se agregan aquí: ordenar, agregar al carrito, etc. */
public class InventoryPage extends BasePage {

    private final By titulo = By.cssSelector("[data-test='title']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String tituloDePagina() {
        return textoDe(titulo);
    }
}
