package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import proyecto.base.BaseTest;
import proyecto.pages.CheckoutPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTest extends BaseTest {

    private CheckoutPage llegarAlFormulario(String usuario, int productos) {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo(usuario);
        inventario.agregarProductos(productos);
        return inventario.abrirCarrito().irACheckout();
    }

    private CheckoutPage llegarAlResumen(String usuario) {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo(usuario);
        inventario.agregarProductoPorId("sauce-labs-backpack");
        inventario.agregarProductoPorId("sauce-labs-bike-light");
        CheckoutPage checkout = inventario.abrirCarrito().irACheckout();
        checkout.llenarDatos("Ana", "Perez", "30101");
        checkout.continuar();
        return checkout;
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/checkout_info_cases.csv", numLinesToSkip = 1)
    void camposObligatorios(String caso, String nombre, String apellido, String codigoPostal, String mensajeEsperado) {
        CheckoutPage checkout = llegarAlFormulario("standard_user", 1);

        checkout.llenarDatos(nombre, apellido, codigoPostal);
        checkout.continuar();

        String obtenido = checkout.textoDelError();
        assertTrue(obtenido.contains(mensajeEsperado),
                "Se esperaba un mensaje con «" + mensajeEsperado + "» pero fue «" + obtenido + "»");
    }

    @Test
    @DisplayName("TC26 Checkout con datos completos")
    void datosCompletos() {
        CheckoutPage checkout = llegarAlFormulario("standard_user", 1);

        checkout.llenarDatos("Ana", "Perez", "30101");
        checkout.continuar();

        assertTrue(checkout.tituloDePagina().equalsIgnoreCase("Checkout: Overview"));
    }

    @Test
    @DisplayName("TC28 Subtotal del resumen")
    void subtotalDelResumen() {
        CheckoutPage resumen = llegarAlResumen("standard_user");

        String subtotal = resumen.subtotal();
        assertTrue(subtotal.contains("$39.98"), "Subtotal obtenido: " + subtotal);
    }

    @Test
    @DisplayName("TC29 Impuesto y total del resumen")
    void impuestoYTotal() {
        CheckoutPage resumen = llegarAlResumen("standard_user");

        String impuesto = resumen.impuesto();
        String total = resumen.total();
        assertTrue(impuesto.contains("$3.20"), "Impuesto obtenido: " + impuesto);
        assertTrue(total.contains("$43.18"), "Total obtenido: " + total);
    }

    @Test
    @DisplayName("TC30 Compra completa con usuario estándar")
    void compraCompleta() {
        CheckoutPage checkout = llegarAlResumen("standard_user");

        checkout.finalizar();

        assertTrue(checkout.mensajeDeConfirmacion().equalsIgnoreCase("Thank you for your order!"));
    }

    // Este test DEBE fallar: error_user tiene un defecto intencional al finalizar la compra.
    // Su falla es la evidencia del defecto.
    @Test
    @DisplayName("TC32 Finalizar compra con error_user")
    void compraConUsuarioConErrores() {
        CheckoutPage checkout = llegarAlResumen("error_user");

        checkout.finalizar();

        assertTrue(checkout.mensajeDeConfirmacion().equalsIgnoreCase("Thank you for your order!"));
    }
}
