package proyecto.base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

/**
 * Toma una captura de pantalla cuando un test falla.
 * Se usa TestExecutionExceptionHandler porque corre ANTES de @AfterEach: en ese momento
 * el navegador sigue abierto. (Con TestWatcher el navegador ya estaría cerrado.)
 */
public class CapturaAlFallar implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext contexto, Throwable error) throws Throwable {
        Object instancia = contexto.getRequiredTestInstance();
        if (instancia instanceof BaseTest prueba) {
            prueba.capturar(contexto.getDisplayName());
        }
        throw error; // el test igual debe aparecer como fallido
    }
}
