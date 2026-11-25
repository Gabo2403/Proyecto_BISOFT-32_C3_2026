# Guía de comprobación de TC06, TC10, TC11 y TC13

## Regla de registro

Para cada caso se debe anotar evidencia objetiva y luego comparar con el resultado esperado. Esta guía describe qué comprobar; los resultados reales y sus estados se encuentran en [Resultados de pruebas manuales — Integrante A](resultados_pruebas_manuales_integrante_A.md).

## TC06 — Login con usuario de bajo rendimiento

**Preparación**

- Abrir la página de inicio de sesión sin sesión activa.
- Usar `performance_glitch_user` y `secret_sauce`.
- Registrar navegador, versión, equipo, condición de red y método de cronometraje.

**Comprobar exactamente**

1. Al activar Login, el sistema inicia el intento de autenticación.
2. Se abre el inventario y aparece el título exacto «Products».
3. No aparece un mensaje de rechazo de credenciales.
4. Medir el tiempo desde la activación de Login hasta que «Products» sea visible.
5. Registrar el valor medido, sin redondearlo para forzar el cumplimiento.
6. Comparar el valor con “menos de 3,0 s” solo si el equipo aprueba ese umbral y su método de medición.

**Evidencia mínima**

- Captura del inventario con «Products».
- Tiempo medido y herramienta utilizada.
- Condiciones del ambiente.

**Evidencia disponible**

- [Captura de rendimiento de TC06](../evidencias/integrante_A/TC06_03_Performance.png). Muestra el inventario y Chrome DevTools; los 5013 ms corresponden a scripting y no prueban el tiempo total de extremo a extremo.
- [Resultados de las cinco mediciones Selenium](../evidencias/integrante_A/TC06_resultados.csv).
- Capturas posteriores a la visualización de «Products»: [ejecución 1](../evidencias/integrante_A/TC06_ejecucion_01.png), [ejecución 2](../evidencias/integrante_A/TC06_ejecucion_02.png), [ejecución 3](../evidencias/integrante_A/TC06_ejecucion_03.png), [ejecución 4](../evidencias/integrante_A/TC06_ejecucion_04.png) y [ejecución 5](../evidencias/integrante_A/TC06_ejecucion_05.png).

La medición automatizada inició con `System.nanoTime()` inmediatamente antes de activar Login y terminó cuando Selenium observó el título visible con texto exacto «Products». Los tiempos fueron 5118, 5114, 5129, 5107 y 5123 ms en Chrome 158.0.8127.88. Las cinco ejecuciones superaron 3000 ms.

**Advertencia de trazabilidad**

El acceso al inventario es trazable a RF-01. El límite de 3 segundos no aparece en RF-01; debe aprobarse como requisito no funcional o eliminarse del criterio de aprobación de TC06.

## TC10 — Opciones del menú lateral

**Preparación**

- Iniciar sesión con `standard_user`.
- Permanecer en el inventario.

**Comprobar exactamente**

1. El control del menú lateral puede abrirse.
2. El menú abierto muestra estas cuatro opciones, con esos rótulos:
   - All Items
   - About
   - Logout
   - Reset App State
3. No falta ninguna de las cuatro opciones.
4. No registrar como parte de TC10 que los enlaces navegan correctamente: los pasos actuales solo exigen observar las opciones.

**Evidencia mínima**

- Una captura del menú abierto donde sean legibles las cuatro opciones.

**Evidencia disponible**

- [Menú lateral de TC10](../evidencias/integrante_A/TC10_Menu_Lateral.png). Se observan All Items, Dynamic Catalog, About, Logout y Reset App State.

Según la interpretación literal del resultado esperado, TC10 **Pasó con observación**: las cuatro opciones exigidas están presentes. Dynamic Catalog se mantiene como observación de alcance pendiente de aprobación y no como defecto confirmado.

**Advertencia de cobertura**

TC10 cubre RF-04/CA1. No cubre RF-04/CA3 porque no activa All Items. Esa navegación necesita un caso adicional.

## TC11 — Reiniciar el estado vacía el carrito

**Preparación**

- Iniciar sesión con `standard_user`.
- Elegir un producto identificable y agregarlo al carrito.
- Confirmar antes del reinicio que el contador muestra al menos 1 y que el botón de ese producto muestra «Remove».

**Comprobar exactamente**

1. Abrir el menú lateral.
2. Activar Reset App State.
3. Verificar que el contador del carrito desaparece; no basta con que muestre cero si el resultado esperado exige que no se vea.
4. Verificar que, para el producto agregado en la preparación, el botón vuelve a mostrar «Add to cart».
5. Si la vista no actualiza de inmediato, registrar el comportamiento observado sin recargar o añadir pasos no definidos.

**Evidencia mínima**

- Evidencia previa con contador y botón «Remove».
- Evidencia posterior con contador ausente y botón «Add to cart» del mismo producto.

**Evidencia disponible**

- [Antes de Reset App State](../evidencias/integrante_A/TC11_01_Antes_Reset.png): el producto está agregado y el botón muestra «Remove».
- [Después de Reset App State](../evidencias/integrante_A/TC11_02_Despues_Reset_Remove.png): el contador desapareció, pero el botón continúa en «Remove».
- [Carrito vacío después del reinicio](../evidencias/integrante_A/TC11_03_Carrito_Vacio.png).

La selección disponible documenta la inconsistencia real. No existe una captura posterior con «Add to cart» porque esa condición no se cumplió.

**Límite**

No inferir que el carrito interno quedó vacío únicamente porque desapareció el contador; el caso actual exige dos señales visibles. Una comprobación adicional abriendo el carrito requeriría ampliar formalmente los pasos.

## TC13 — Detalle de un producto

**Preparación**

- Iniciar sesión con `standard_user`.
- Permanecer en el inventario.
- Ubicar `Sauce Labs Backpack`.

**Comprobar exactamente**

1. Activar el nombre del producto `Sauce Labs Backpack`.
2. Verificar que se abre la vista de detalle.
3. Verificar el nombre exacto «Sauce Labs Backpack».
4. Verificar el precio exacto «$29.99».
5. Activar Back to products.
6. Verificar que se regresa al listado de inventario; como señal observable, debe volver a mostrarse «Products» y la lista de productos.

**Evidencia mínima**

- Captura del detalle con nombre y precio.
- Captura posterior del inventario tras Back to products.

**Evidencia disponible**

- [Detalle de Sauce Labs Backpack](../evidencias/integrante_A/TC13_01_Detalle.png).
- [Regreso al inventario](../evidencias/integrante_A/TC13_02_Regreso_Inventario.png).

**Límite**

TC13 no comprueba descripción, imagen ni los detalles de los otros cinco productos. Esas afirmaciones no deben darse por verificadas con este caso.

