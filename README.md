# Pruebas automatizadas de Swag Labs (BISOFT-32)

Proyecto final de Calidad, Verificación y Validación del Software, Universidad CENFOTEC.
Aplicación bajo prueba: https://www.saucedemo.com

## Requisitos
- Java 17 o superior (`java -version`)
- Maven 3.8 o superior (`mvn -version`)
- Navegador instalado (Chrome por defecto). Selenium descarga el driver solo, así que se necesita internet.

## Ejecutar todas las pruebas (un solo comando)
```
mvn test
```

## Resultados
- Reporte HTML: `target/site/surefire-report.html`
- Capturas de los tests que fallan: `target/screenshots/`

## Cambiar navegador o modo sin ventana
```
mvn test -Dnavegador=firefox
mvn test -Dnavegador=edge
mvn test -Dheadless=true
```
Los valores por defecto están en `src/test/resources/config.properties`.

## Estructura
- `base/`: BaseTest (abre y cierra el navegador), Config, captura de pantalla al fallar
- `pages/`: Page Objects (localizadores y acciones, sin asserts)
- `tests/`: casos de prueba (pasos y verificaciones, sin localizadores)
- `resources/`: configuración y datos de prueba externos (CSV)

## Convenciones del equipo
- Cada test indica su caso: `@DisplayName("TC05 ...")`.
- Rangos de IDs: persona A TC01–TC11, persona B TC12–TC22, persona C TC23–TC33.
- Cada persona trabaja en su rama (`feature/login`, `feature/carrito`, `feature/checkout`) y abre un Pull Request.

## Equipo
Gabriel Guzman Leiva
Carlos Quesada Ramirez
Ignacio Molina Alvarado