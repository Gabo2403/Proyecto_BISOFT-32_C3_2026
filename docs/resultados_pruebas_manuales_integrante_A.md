# Resultados de pruebas manuales — Integrante A

## Alcance de la ejecución

- Aplicación evaluada: Swag Labs.
- Modalidad: pruebas manuales.
- Navegador informado: Google Chrome.
- Sistema operativo informado: Windows.
- Casos incluidos: TC06, TC10, TC11 y TC13.
- Fuente de resultados: observaciones reales proporcionadas por el integrante A.

No se modificó el Excel ni se completaron sus columnas de Estado o Defecto relacionado. Los estados de este documento se asignan únicamente cuando el resultado esperado permite una comparación inequívoca.

## Resumen

| Caso | Resultado esperado del Excel | Resultado observado | Evaluación |
|---|---|---|---|
| TC06 | Acceder al inventario con «Products» en menos de 3 segundos. | El acceso con `performance_glitch_user` fue exitoso y presentó una demora considerable. DevTools registró 5013 ms de scripting. | **Veredicto pendiente.** Se comprobó la parte funcional, pero 5013 ms de scripting no equivale al tiempo transcurrido entre Login y la visualización del inventario. |
| TC10 | Mostrar All Items, About, Logout y Reset App State. | Se mostraron las cuatro opciones esperadas y también Dynamic Catalog. | **Cumple el mínimo esperado, con observación pendiente.** El Excel no aclara si la lista debe ser exacta o si admite opciones adicionales. |
| TC11 | Tras Reset App State, el contador desaparece y el botón vuelve a «Add to cart». | El contador desapareció y el carrito quedó vacío, pero el botón permaneció como «Remove». | **Falló.** Una de las dos condiciones explícitas no se cumplió. |
| TC13 | Mostrar «Sauce Labs Backpack», «$29.99» y regresar al listado con Back to products. | Se verificaron nombre, imagen, descripción, precio de $29.99, Add to cart y Back to products; se regresó al inventario. | **Pasó.** Se cumplieron las comprobaciones exigidas por el resultado esperado. |

## Detalle por caso

### TC06 — Login con usuario de bajo rendimiento

**Datos utilizados**

- Usuario: `performance_glitch_user`.
- Clave: `secret_sauce`.

**Resultado observado**

El inicio de sesión fue exitoso y se accedió al inventario. Durante la interacción se percibió una demora considerable. La captura de rendimiento de Chrome DevTools registró **5013 ms de scripting**.

**Contraste con el resultado esperado**

- Acceso exitoso al inventario: comprobado.
- Título «Products»: el acceso al inventario fue informado, pero no se proporcionó una medición separada del instante en que el título se hizo visible.
- Tiempo total menor de 3 segundos: no comprobable con la medición disponible.

**Evaluación**

**Veredicto pendiente.** Los 5013 ms corresponden al tiempo de scripting mostrado por DevTools y no deben reinterpretarse como duración total desde presionar Login hasta acceder al inventario. Para decidir el umbral se necesita una medición de extremo a extremo con inicio y fin definidos.

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

**Cumple el mínimo esperado, con observación pendiente.** No se asigna Falló porque el texto actual solo exige que aparezcan cuatro opciones y no dice “exactamente”. Tampoco se cierra como conformidad total hasta que el responsable defina si las opciones adicionales son válidas.

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

No se vinculan capturas. En las carpetas locales disponibles del trabajo no se encontraron archivos de imagen que pudieran asociarse correctamente con TC06, TC10, TC11 o TC13. La mención de una captura de rendimiento en TC06 proviene del resultado informado, pero el archivo de esa captura no estaba disponible y no se inventó una ruta.

## Cambios que debe realizar el encargado del Excel

1. Mantener TC06 sin veredicto hasta medir el tiempo total con un método aprobado.
2. Aclarar en RF-04/TC10 si la lista de opciones es exacta o mínima y decidir el tratamiento de Dynamic Catalog.
3. Registrar el fallo real de TC11 y, si corresponde, crear un defecto con el título sugerido.
4. Registrar TC13 como Pasó.
5. Conservar las brechas de cobertura ya documentadas para RF-04/CA3, RF-07/CA3 y RF-09/CA1.

Este documento no sustituye la actualización controlada del registro maestro por la persona responsable del Excel.

