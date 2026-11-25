package proyecto.base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class CapturaAlFallar implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext contexto, Throwable error) throws Throwable {
        Object instancia = contexto.getRequiredTestInstance();
        if (instancia instanceof BaseTest prueba) {
            prueba.capturar(contexto.getDisplayName());
        }
        throw error; 
    }
}
