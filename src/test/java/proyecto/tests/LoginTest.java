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

/** Pasos y verificaciones. SIN localizadores: todo pasa por los métodos de las páginas. */
class LoginTest extends BaseTest {

    // Una sola prueba, una fila del CSV = un caso (la tabla de decisión del login).
    // {0} es la primera columna del CSV: el ID y título del caso, por ejemplo "TC01 Usuario vacío".
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
        LoginPage login = new LoginPage(driver);

        login.iniciarSesion("standard_user", "secret_sauce");

        assertEquals("Products", new InventoryPage(driver).tituloDePagina());
    }
}
