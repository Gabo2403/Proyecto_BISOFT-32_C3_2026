
# Pruebas automatizadas de Swag Labs (BISOFT-32)

Proyecto final de Calidad, Verificación y Validación del Software, Universidad CENFOTEC.

Aplicación bajo prueba: https://www.saucedemo.com

## Requisitos

- Java 17 o superior (`java -version`).
- Maven 3.8 o superior (`mvn -version`).
- Google Chrome, Mozilla Firefox o Microsoft Edge.
- Conexión a Internet para acceder a Swag Labs y descargar dependencias y controladores.

Chrome es el navegador predeterminado. Selenium administra automáticamente los controladores cuando las versiones y el servicio de descarga son compatibles.

## Ejecutar todas las pruebas

```bash
mvn test
```

## Resultados

- Reporte HTML: `target/reports/surefire.html`
- Reportes detallados: `target/surefire-reports/`
- Capturas de los tests que fallan: `target/screenshots/`

El mensaje BUILD SUCCESS no garantiza que todas las pruebas hayan sido aprobadas. Se deben revisar los resultados individuales de Surefire.

## Cambiar navegador o modo sin ventana

```bash
mvn test -Dnavegador=firefox
mvn test -Dnavegador=edge
mvn test -Dheadless=true
```

Los valores por defecto están en `src/test/resources/config.properties`.

### Configuración manual de EdgeDriver

Si Selenium no consigue descargar el controlador de Microsoft Edge automáticamente, se puede descargar una versión compatible desde:

https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/

Posteriormente, ejecutar:

```bash
mvn test -Dnavegador=edge -Dwebdriver.edge.driver="/ruta/al/msedgedriver"
```

La ruta debe ajustarse al equipo utilizado.

## Estructura

- `base/`: BaseTest, configuración y captura de pantalla al fallar.
- `pages/`: Page Objects, localizadores y acciones sin asserts.
- `tests/`: casos de prueba, pasos y verificaciones.
- `resources/`: configuración y datos de prueba externos (CSV).
- `docs/`: documentación del proyecto.
- `evidencias/`: registros, capturas y reportes de ejecución.

## Documentación de pruebas

### Integrante B

- [Plan de pruebas, matriz de riesgos, RACI y estimación de tres puntos](docs/plan_pruebas_integrante_B.md)
- [Resultados de pruebas no funcionales](docs/pruebas_no_funcionales_integrante_B.md)

## Pruebas no funcionales

Se realizaron pruebas de compatibilidad entre navegadores y auditorías de rendimiento y accesibilidad mediante Google Lighthouse.

### Compatibilidad

| Navegador | Ejecutadas | Aprobadas | Errores | Aprobación |
|---|---:|---:|---:|---:|
| Firefox | 25 | 23 | 2 | 92 % |
| Microsoft Edge | 25 | 23 | 2 | 92 % |

- [Reporte HTML de Firefox](evidencias/integrante_B/compatibilidad/firefox/reports/surefire.html)
- [Registro de Firefox](evidencias/integrante_B/compatibilidad/firefox.log)
- [Reporte HTML de Edge](evidencias/integrante_B/compatibilidad/edge/reports/surefire.html)
- [Registro de Edge](evidencias/integrante_B/compatibilidad/edge.log)

### Rendimiento y accesibilidad

Auditorías realizadas mediante Lighthouse en Google Chrome, modalidad Desktop.

| Pantalla | Rendimiento | Accesibilidad | Evidencia |
|---|---:|---:|---|
| Inicio de sesión | 99 | 100 | [Reporte](evidencias/integrante_B/lighthouse/login_desktop.html) |
| Catálogo | 99 | 100 | [Reporte](evidencias/integrante_B/lighthouse/inventory_desktop.html) |
| Carrito | 99 | 100 | [Reporte](evidencias/integrante_B/lighthouse/cart_desktop.html) |
| Checkout | 97 | 100 | [Reporte](evidencias/integrante_B/lighthouse/checkout_desktop.html) |

Los resultados corresponden a las puntuaciones reportadas por Lighthouse. Las advertencias de redirección y las limitaciones de las comprobaciones automatizadas se detallan en el documento de pruebas no funcionales.

## Convenciones del equipo

- Cada test indica su caso: `@DisplayName("TC05 ...")`.
- Rangos originales de IDs: persona A TC01–TC11, persona B TC12–TC22, persona C TC23–TC33.
- Cada persona trabaja en una rama independiente y abre un Pull Request.
- Las evidencias deben conservarse por integrante y ambiente de ejecución.
- Los resultados deben documentarse sin alterar los registros originales.

## Equipo

- Gabriel Guzman Leiva
- Carlos Quesada Ramirez
- Ignacio Molina Alvarado