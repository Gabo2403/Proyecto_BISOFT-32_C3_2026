# Comprobaciones pendientes para TC06, TC10, TC11 y TC13

## Regla de registro

Para cada caso se debe anotar evidencia objetiva y luego comparar con el resultado esperado. No se debe marcar Pasó o Falló hasta ejecutar el caso en un ambiente identificado. Este documento no contiene resultados de ejecución.

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

**Límite**

TC13 no comprueba descripción, imagen ni los detalles de los otros cinco productos. Esas afirmaciones no deben darse por verificadas con este caso.

