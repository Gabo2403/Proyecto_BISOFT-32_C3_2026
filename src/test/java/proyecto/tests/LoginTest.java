package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import proyecto.base.BaseTest;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/login_cases.csv", numLinesToSkip = 1)
    void loginRechazado(String caso, String usuario, String clave, String mensajeEsperado) {
        LoginPage login = new LoginPage(driver);

        login.iniciarSesion(usuario, clave);

        String obtenido = login.textoDelError();
        assertTrue(obtenido.contains(mensajeEsperado),
                "Se esperaba un mensaje con «" + mensajeEsperado + "» pero fue «" + obtenido + "»");
    }

    @Test
    @DisplayName("TC05 Login exitoso con usuario estándar")
    void loginExitoso() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("standard_user");

        assertTrue(inventario.tituloDePagina().equalsIgnoreCase("Products"));
    }

    @Test
    @DisplayName("TC08 Cerrar sesión desde el menú")
    void cerrarSesion() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("standard_user");

        LoginPage login = inventario.cerrarSesion();

        assertTrue(login.botonLoginVisible());
    }

    @Test
    @DisplayName("TC09 Acceso directo al inventario sin sesión")
    void accesoSinSesion() {
        LoginPage login = new LoginPage(driver);

        login.abrir("/inventory.html");

        String error = login.textoDelError();
        assertTrue(error.contains("You can only access"),
                "Se esperaba un error que pida iniciar sesión, pero fue «" + error + "»");
    }
}
