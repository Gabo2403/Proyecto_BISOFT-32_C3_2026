package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import proyecto.base.BaseTest;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class InventoryTest extends BaseTest {

    private InventoryPage iniciarComoEstandar() {
        return new LoginPage(driver).iniciarSesionComo("standard_user");
    }

    private static <T> List<T> ordenada(List<T> lista, Comparator<T> criterio) {
        List<T> copia = new ArrayList<>(lista);
        copia.sort(criterio);
        return copia;
    }

    @Test
    @DisplayName("TC12 El inventario muestra 6 productos completos")
    void inventarioCompleto() {
        InventoryPage inventario = iniciarComoEstandar();

        assertEquals(6, inventario.cantidadDeProductos());
        assertEquals(6, inventario.cantidadDeImagenes());
        assertTrue(inventario.nombresDeProductos().stream().noneMatch(String::isBlank));
        assertTrue(inventario.preciosEnTexto().stream().noneMatch(String::isBlank));
        assertTrue(inventario.descripcionesDeProductos().stream().noneMatch(String::isBlank));
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/sort_cases.csv", numLinesToSkip = 1)
    void ordenamiento(String caso, String opcion, String criterio) {
        InventoryPage inventario = iniciarComoEstandar();

        inventario.ordenarPor(opcion);

        List<String> nombres = inventario.nombresDeProductos();
        List<Double> precios = inventario.preciosDeProductos();
        switch (criterio) {
            case "nombre_asc" -> assertEquals(ordenada(nombres, Comparator.naturalOrder()), nombres);
            case "nombre_desc" -> assertEquals(ordenada(nombres, Comparator.reverseOrder()), nombres);
            case "precio_asc" -> assertEquals(ordenada(precios, Comparator.naturalOrder()), precios);
            case "precio_desc" -> assertEquals(ordenada(precios, Comparator.reverseOrder()), precios);
            default -> fail("Criterio desconocido en el CSV: " + criterio);
        }
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/cart_counter_cases.csv", numLinesToSkip = 1)
    void contadorDelCarrito(String caso, int cantidad, int contadorEsperado) {
        InventoryPage inventario = iniciarComoEstandar();

        inventario.agregarProductos(cantidad);

        assertEquals(contadorEsperado, inventario.productosEnElCarrito());
        assertEquals(cantidad, inventario.botonesQuitarVisibles());
    }

    private InventoryPage iniciarComo(String usuario) {
        return new LoginPage(driver).iniciarSesionComo(usuario);
    }

    @Test
    @DisplayName("TC35 error_user agrega Bolt T-Shirt al carrito")
    void errorUserAgregaProducto() {
        InventoryPage inventario = iniciarComo("error_user");

        inventario.clicAgregar("sauce-labs-bolt-t-shirt");

        assertTrue(inventario.botonRemoveVisible("sauce-labs-bolt-t-shirt"),
                "Tras agregar, el botón debería cambiar a Remove");
    }
}
