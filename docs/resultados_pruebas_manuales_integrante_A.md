# Resultados de pruebas manuales — Integrante A

## Alcance de la ejecución

- Aplicación evaluada: Swag Labs.
- Modalidad: pruebas manuales y medición automatizada temporal de TC06 con Selenium.
- Navegador: Google Chrome 158.0.8127.88 para la medición automatizada de TC06; Google Chrome sobre Windows para las observaciones manuales.
- Sistema operativo informado: Windows.
- Casos incluidos: TC06, TC10, TC11 y TC13.
- Fuente de resultados: observaciones reales proporcionadas por el integrante A.

No se modificó el Excel ni se completaron sus columnas de Estado o Defecto relacionado. Los estados de este documento se asignan únicamente cuando el resultado esperado permite una comparación inequívoca.

## Resumen

| Caso | Resultado esperado del Excel | Resultado observado | Evaluación |
|---|---|---|---|
| TC06 | Acceder al inventario con «Products» en menos de 3 segundos. | Cinco mediciones Selenium de extremo a extremo: 5118, 5114, 5129, 5107 y 5123 ms. | **Falló respecto al criterio del caso.** Las cinco ejecuciones superaron 3000 ms. El límite sigue sin estar definido en RF-01. |
| TC10 | Mostrar All Items, About, Logout y Reset App State. | Se mostraron las cuatro opciones esperadas y también Dynamic Catalog. | **Pasó con observación.** La interpretación literal exige presencia, no exclusividad. Dynamic Catalog queda pendiente de aprobación de alcance y no se clasifica como defecto confirmado. |
| TC11 | Tras Reset App State, el contador desaparece y el botón vuelve a «Add to cart». | El contador desapareció y el carrito quedó vacío, pero el botón permaneció como «Remove». | **Falló.** Una de las dos condiciones explícitas no se cumplió. |
| TC13 | Mostrar «Sauce Labs Backpack», «$29.99» y regresar al listado con Back to products. | Se verificaron nombre, imagen, descripción, precio de $29.99, Add to cart y Back to products; se regresó al inventario. | **Pasó.** Se cumplieron las comprobaciones exigidas por el resultado esperado. |

## Detalle por caso

### TC06 — Login con usuario de bajo rendimiento

**Datos utilizados**

- Usuario: `performance_glitch_user`.
- Clave: `secret_sauce`.

**Resultado observado**

El inicio de sesión fue exitoso en cinco ejecuciones independientes y en todas se observó el título «Products». La medición se ejecutó con Selenium 4.27.0 y Chrome 158.0.8127.88 en modo headless.

El cronómetro se implementó con `System.nanoTime()`: comenzó inmediatamente antes de `click()` sobre Login y terminó cuando una espera explícita confirmó que el título era visible y su texto exacto era «Products».

| Ejecución | Tiempo (ms) | ¿Menor de 3000 ms? |
|---:|---:|---|
| 1 | 5118 | No |
| 2 | 5114 | No |
| 3 | 5129 | No |
| 4 | 5107 | No |
| 5 | 5123 | No |
| **Promedio** | **5118,2** | **No** |
| **Mínimo** | **5107** | **No** |
| **Máximo** | **5129** | **No** |

La captura manual anterior de Chrome DevTools registró **5013 ms de scripting**. Ese dato se conserva como observación histórica, pero no se usa como sustituto de las nuevas mediciones de extremo a extremo.

**Contraste con el resultado esperado**

- Acceso exitoso al inventario: comprobado en las cinco ejecuciones.
- Título «Products» visible: comprobado como condición de fin en las cinco ejecuciones.
- Tiempo total menor de 3 segundos: no cumplido en las cinco ejecuciones.

**Evaluación**

**Falló respecto al criterio existente de TC06.** Ninguna ejecución fue menor de 3000 ms. Esta conclusión evalúa el caso tal como está escrito; no elimina la brecha de trazabilidad porque RF-01 no define un requisito de rendimiento.

**Brecha para incorporar al Excel**

TC06 añade un umbral que no aparece en RF-01. El encargado del Excel debe:

1. aprobar un requisito no funcional con el límite de 3,0 segundos;
2. definir el punto inicial, el punto final, el ambiente y el número de repeticiones; o
3. retirar el umbral y conservar únicamente la validación funcional.

### TC10 — Opciones del menú lateral

**Resultado observado**

Al abrir el menú lateral se visualizaron:

1. All Items
2. Dynamic Catalog
3. About
4. Logout
5. Reset App State

**Contraste con el resultado esperado**

Las cuatro opciones indicadas en el Excel estaban presentes. Además, apareció **Dynamic Catalog**, opción no mencionada en RF-04 ni en TC10.

**Evaluación**

**Pasó con observación.** El texto actual exige que aparezcan las cuatro opciones y no dice “exactamente”, por lo que el resultado literal se cumplió. Dynamic Catalog permanece como observación de alcance pendiente de aprobación por el equipo y no se clasifica como defecto confirmado.

**Brecha para incorporar al Excel**

El encargado debe escoger una formulación:

- Si el conjunto debe ser cerrado: “El menú muestra exactamente All Items, About, Logout y Reset App State, sin opciones adicionales”.
- Si se permiten extensiones: “El menú incluye, como mínimo, All Items, About, Logout y Reset App State; pueden aparecer opciones adicionales autorizadas”.

También debe decidir si Dynamic Catalog es una función aceptada y si necesita requerimiento y casos propios.

### TC11 — Reiniciar el estado vacía el carrito

**Resultado observado**

Se agregó un producto al carrito y se ejecutó Reset App State. Después de la acción:

- el contador del carrito desapareció;
- el carrito quedó vacío;
- el botón del producto permaneció como «Remove».

**Contraste con el resultado esperado**

El resultado esperado exige conjuntamente que el contador desaparezca y que el botón vuelva a «Add to cart». La primera condición se cumplió; la segunda no.

**Evaluación**

**Falló.** La interfaz quedó inconsistente: el estado del carrito se reinició, pero el control del producto continuó representando el estado anterior.

**Hallazgo para registrar por el encargado**

- Título sugerido: “Reset App State vacía el carrito, pero mantiene el botón del producto en Remove”.
- Resultado esperado: “El contador desaparece, el carrito queda vacío y el botón del producto cambia a Add to cart”.
- Resultado obtenido: “El contador desapareció y el carrito quedó vacío, pero el botón permaneció como Remove”.
- Caso relacionado: TC11.

No se asignan severidad, prioridad, ambiente detallado ni identificador de defecto porque esos datos no fueron suministrados.

### TC13 — Detalle de un producto

**Resultado observado**

En el detalle de `Sauce Labs Backpack` se verificaron:

- nombre del producto;
- imagen;
- descripción;
- precio de **$29.99**;
- botón Add to cart;
- control Back to products.

Al activar Back to products se regresó al inventario.

**Contraste con el resultado esperado**

El Excel exige el nombre «Sauce Labs Backpack», el precio «$29.99» y el regreso al listado. Las tres condiciones fueron comprobadas. La imagen, la descripción y Add to cart son observaciones adicionales y no amplían por sí solas la cobertura de otros productos.

**Evaluación**

**Pasó.**

## Evidencias locales

Se vinculan únicamente las capturas conservadas en la selección definitiva:

- **TC06:** [datos de las cinco mediciones](../evidencias/integrante_A/TC06_resultados.csv); capturas de [ejecución 1](../evidencias/integrante_A/TC06_ejecucion_01.png), [ejecución 2](../evidencias/integrante_A/TC06_ejecucion_02.png), [ejecución 3](../evidencias/integrante_A/TC06_ejecucion_03.png), [ejecución 4](../evidencias/integrante_A/TC06_ejecucion_04.png) y [ejecución 5](../evidencias/integrante_A/TC06_ejecucion_05.png). Se conserva también la [captura manual de rendimiento](../evidencias/integrante_A/TC06_03_Performance.png), cuyos 5013 ms de scripting no se equiparan al tiempo total.
- **TC10:** [menú lateral](../evidencias/integrante_A/TC10_Menu_Lateral.png), con Dynamic Catalog visible como opción adicional.
- **TC11:** [antes del reinicio](../evidencias/integrante_A/TC11_01_Antes_Reset.png), [después del reinicio con Remove](../evidencias/integrante_A/TC11_02_Despues_Reset_Remove.png) y [carrito vacío](../evidencias/integrante_A/TC11_03_Carrito_Vacio.png).
- **TC13:** [detalle del producto](../evidencias/integrante_A/TC13_01_Detalle.png) y [regreso al inventario](../evidencias/integrante_A/TC13_02_Regreso_Inventario.png).

No se mencionan capturas eliminadas ni se infieren mediciones adicionales a partir de las imágenes.

## Cambios que debe realizar el encargado del Excel

1. Registrar TC06 como Falló respecto al umbral actualmente escrito, manteniendo señalado que ese límite no proviene de RF-01.
2. Registrar TC10 como Pasó con observación y decidir el alcance futuro de Dynamic Catalog sin tratarlo todavía como defecto confirmado.
3. Registrar el fallo real de TC11 y, si corresponde, crear un defecto con el título sugerido.
4. Registrar TC13 como Pasó.
5. Conservar las brechas de cobertura ya documentadas para RF-04/CA3, RF-07/CA3 y RF-09/CA1.

Este documento no sustituye la actualización controlada del registro maestro por la persona responsable del Excel.

