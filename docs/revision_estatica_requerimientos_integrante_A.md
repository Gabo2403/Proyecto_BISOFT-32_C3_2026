# Revisión estática de requerimientos — Integrante A

## Alcance y fuente

Esta revisión se basa únicamente en las hojas `Trazabilidad` y `Casos` de `Registro_QA_SwagLabs.xlsx`. El libro se trató como fuente de solo lectura. No se ejecutó la aplicación y, por tanto, este documento no declara casos aprobados, fallidos ni bloqueados.

## Criterios aplicados

- **Claridad:** el texto identifica una conducta concreta y usa términos comprensibles.
- **Ambigüedad:** existe una sola interpretación razonable de actores, datos, condiciones y resultados.
- **Completitud:** se indican precondiciones, alcance, reglas, datos y excepciones necesarios.
- **Verificabilidad:** el resultado puede comprobarse mediante observaciones y criterios objetivos.

## Lista de cotejo

| Ítem | Pregunta de revisión | Resultado global | Observación |
|---|---|---|---|
| 1 | ¿Cada requerimiento identifica la función que se espera del sistema? | Sí | Los diez requerimientos describen una función reconocible. |
| 2 | ¿Se identifica el usuario o estado aplicable? | Parcial | RF-01 habla de “usuario válido” y de “todos los usuarios de prueba” sin enumerarlos. |
| 3 | ¿Los términos importantes están definidos? | Parcial | “Mensaje claro”, “correctos”, “válidos” y “páginas internas” requieren definición. |
| 4 | ¿Los criterios de aceptación son observables? | Sí, con reservas | La mayoría incluye textos o cambios visibles, pero algunos criterios carecen de datos de referencia. |
| 5 | ¿Los valores numéricos tienen unidad y regla de cálculo? | Parcial | RF-09 fija 8 %, pero no define el redondeo de forma inequívoca; el límite de 3 s solo aparece en TC06. |
| 6 | ¿Se definen las precondiciones necesarias? | Parcial | El estado del carrito y la sesión se infieren en varios criterios, no siempre se declaran. |
| 7 | ¿Se cubren rutas positivas y negativas? | Parcial | Login y datos del comprador tienen ambas rutas; otros flujos se concentran en la ruta positiva. |
| 8 | ¿Cada criterio evita unir varias obligaciones independientes? | Parcial | RF-03, RF-05, RF-07 y RF-10 agrupan comportamientos que conviene comprobar por separado. |
| 9 | ¿El requerimiento evita depender de información externa no citada? | Parcial | RF-05 usa “correctos” sin catálogo esperado y RF-09 presupone una tasa. |
| 10 | ¿Cada criterio puede enlazarse con al menos un caso específico? | Parcial | Todos los RF tienen casos, pero RF-04/CA3, RF-07/CA3 y RF-09/CA1 carecen de verificación directa suficiente. |

## Evaluación de los 10 requerimientos

| ID | Claridad | Ambigüedad | Completitud | Verificabilidad | Resultado de la revisión |
|---|---|---|---|---|---|
| RF-01 | Parcial | No conforme | Parcial | Parcial | “Usuario válido” y “todos los usuarios de prueba” no están enumerados. TC05 y TC06 no cubren todos los usuarios citados por CA3. |
| RF-02 | Conforme | Parcial | Conforme | Conforme | Los mensajes están definidos, pero “mensaje claro” es subjetivo y redundante frente a los textos exactos. |
| RF-03 | Parcial | Parcial | Parcial | Conforme | “Páginas internas” no delimita las URL protegidas. TC08 y TC09 cubren logout e inventario, no una familia definida de páginas. |
| RF-04 | Conforme | Conforme | Parcial | Parcial | CA1 y CA2 son comprobables; CA3 (“All Items”) no tiene caso directo. No se especifica el destino de About. |
| RF-05 | Parcial | No conforme | Parcial | Parcial | “Disponibles” y “correctos” requieren un catálogo esperado. TC13 solo fija nombre y precio de un producto. |
| RF-06 | Conforme | Conforme | Conforme | Conforme | Las cuatro opciones y el sentido del orden están definidos y tienen casos dedicados. |
| RF-07 | Conforme | Parcial | Parcial | Parcial | No se define persistencia fuera de Continue Shopping ni el orden de artículos. CA3 exige coincidencia exacta, pero ningún caso la verifica directamente. |
| RF-08 | Conforme | Parcial | Parcial | Conforme para campos vacíos | Define obligatoriedad, pero no formato, longitud, caracteres válidos ni tratamiento de espacios. |
| RF-09 | Parcial | Parcial | Parcial | Parcial | La tasa del 8 % está declarada, pero falta una regla explícita de redondeo. CA1 no tiene verificación directa del contenido completo del resumen. |
| RF-10 | Conforme | Parcial | Parcial | Conforme | No delimita usuarios habilitados ni el estado exacto posterior a Finish. TC32 espera éxito con error_user sin que el RF justifique esa variante. |

## Hallazgos y redacción corregida

### H-01 — RF-01 no define el conjunto de usuarios válidos

- **Criterios afectados:** claridad, ambigüedad, completitud y verificabilidad.
- **Evidencia:** RF-01 usa “un usuario válido” y CA3 indica “todos los usuarios de prueba, salvo el bloqueado”. Los casos vinculados son TC05 (standard_user) y TC06 (performance_glitch_user); no verifican todos los usuarios aludidos.
- **Riesgo:** dos revisores pueden usar conjuntos de usuarios distintos y llegar a conclusiones incompatibles.
- **Redacción corregida:** “El sistema debe permitir iniciar sesión con la clave `secret_sauce` a los usuarios habilitados definidos para esta prueba: `standard_user` y `performance_glitch_user`. Tras autenticar a cualquiera de ellos, debe mostrar `/inventory.html` con el título «Products». `locked_out_user` se excluye de este requerimiento y se valida en RF-02.”

### H-02 — El umbral de TC06 no está sustentado por RF-01

- **Criterios afectados:** completitud y verificabilidad.
- **Evidencia:** TC06 exige llegar al inventario “en menos de 3 segundos”, mientras RF-01 no contiene requisito de rendimiento, método de medición ni ambiente.
- **Riesgo:** el caso puede fallar por un criterio que no fue aprobado como requerimiento.
- **Redacción corregida propuesta como requisito separado:** “RNF-01: En el ambiente de prueba acordado, desde la activación del botón Login hasta que el título «Products» sea visible, `performance_glitch_user` debe completar el acceso en menos de 3,0 segundos. Se deben registrar navegador, versión, equipo, red, herramienta de medición y al menos tres repeticiones.” Si no se aprueba este RNF, debe retirarse el umbral de TC06 y conservar solo la comprobación funcional.

### H-03 — RF-03 no delimita las páginas protegidas

- **Criterios afectados:** ambigüedad y completitud.
- **Evidencia:** el texto usa “páginas internas”, pero CA3 y TC09 solo mencionan `/inventory.html`.
- **Riesgo:** no queda claro si carrito, detalle y checkout también deben rechazar acceso sin sesión.
- **Redacción corregida:** “Sin una sesión autenticada, una solicitud directa a `/inventory.html` debe mostrar un error que indique que es necesario iniciar sesión y no debe mostrar el inventario. Al seleccionar Logout desde una sesión activa, el sistema debe mostrar la página de inicio de sesión; una navegación posterior a `/inventory.html` debe continuar bloqueada.”

### H-04 — RF-04/CA3 no tiene un caso directo

- **Criterios afectados:** completitud y verificabilidad.
- **Evidencia:** CA3 establece que All Items lleva al inventario. TC10 solo observa las opciones y TC11 solo ejecuta Reset App State.
- **Riesgo:** la presencia del enlace puede confundirse con su funcionamiento.
- **Redacción corregida:** “Al abrir el menú lateral se deben mostrar All Items, About, Logout y Reset App State. Al seleccionar All Items desde una vista distinta del inventario, el sistema debe navegar a `/inventory.html` y mostrar «Products». Al seleccionar Reset App State con al menos un producto agregado, el contador debe desaparecer y el botón del producto debe volver a «Add to cart».”
- **Acción de cobertura:** crear un caso específico para activar All Items desde el detalle o el carrito.

### H-05 — RF-05 usa “correctos” sin una fuente esperada

- **Criterios afectados:** claridad, ambigüedad y verificabilidad.
- **Evidencia:** CA2 exige nombre y precio “correctos”; solo TC13 fija `Sauce Labs Backpack` y `$29.99`.
- **Riesgo:** no existe un oráculo explícito para los demás productos.
- **Redacción corregida:** “El inventario de `standard_user` debe mostrar exactamente seis productos. Cada tarjeta debe contener nombre, descripción no vacía, precio con formato `$0.00` e imagen. Para `Sauce Labs Backpack`, el detalle debe mostrar el nombre «Sauce Labs Backpack» y el precio «$29.99»; Back to products debe regresar a `/inventory.html`.”

### H-06 — RF-07/CA3 no se comprueba de forma directa

- **Criterios afectados:** completitud y verificabilidad.
- **Evidencia:** CA3 exige que el carrito liste exactamente los productos agregados. TC19 comprueba botón y contador; TC20 agrega los seis; TC21 quita uno; TC22 comprueba conservación, pero ninguno compara explícitamente identidad y cantidad de agregados contra la lista del carrito.
- **Riesgo:** un contador correcto podría ocultar artículos incorrectos o duplicados.
- **Redacción corregida:** “Después de agregar un conjunto conocido de productos y abrir el carrito, la lista debe contener una sola línea por cada producto agregado, con el mismo nombre, precio y cantidad, y no debe contener productos no agregados. El contador debe ser igual al número total de unidades.”
- **Acción de cobertura:** agregar un caso con dos productos identificados y comparación exacta de la lista.

### H-07 — RF-08 solo define ausencia, no validez de datos

- **Criterios afectados:** ambigüedad y completitud.
- **Evidencia:** se habla de “datos completos” y “datos válidos”, pero solo se prueba vacío frente a no vacío.
- **Riesgo:** espacios, longitudes extremas o códigos postales no válidos pueden interpretarse de manera diferente.
- **Redacción corregida:** “Nombre, apellido y código postal son obligatorios. Para el alcance actual, un valor se considera suministrado cuando contiene al menos un carácter distinto de espacio. Si un campo obligatorio está vacío, el sistema debe mostrar el mensaje exacto asociado y permanecer en la primera etapa del checkout. Con `Ana`, `Perez` y `30101`, Continue debe abrir el resumen.” Cualquier regla adicional de formato debe aprobarse por separado.

### H-08 — RF-09 no define inequívocamente el redondeo y CA1 queda débil

- **Criterios afectados:** completitud y verificabilidad.
- **Evidencia:** RF-09 dice “impuesto es el 8 % del subtotal, con dos decimales”, sin indicar el modo de redondeo. TC28 y TC29 usan un único conjunto; ninguno declara comparar la lista completa del resumen con el carrito.
- **Riesgo:** valores con tercer decimal pueden producir expectativas diferentes.
- **Redacción corregida:** “El resumen debe listar exactamente los productos presentes en el carrito. El subtotal debe ser la suma de sus precios. El impuesto debe calcularse como subtotal × 0,08 y redondearse a dos decimales mediante redondeo decimal convencional (mitades hacia arriba). El total debe ser subtotal + impuesto redondeado.”

## Conclusión

Los diez IDs tienen al menos un caso asociado, pero la revisión estática no permite declarar cobertura completa de criterios. Antes de ejecutar, deben acordarse el alcance de usuarios de RF-01, el requisito de rendimiento detrás de TC06, el catálogo esperado de RF-05 y las reglas de cálculo de RF-09. También deben añadirse comprobaciones directas para RF-04/CA3, RF-07/CA3 y RF-09/CA1.

## Evidencias disponibles relacionadas con los hallazgos

Las capturas complementan la revisión, pero no corrigen las ambigüedades del texto ni amplían por sí solas el alcance de los casos:

- RF-01 y el límite no trazado de TC06: [login exitoso](../evidencias/integrante_A/RF01_Login_Exitoso.png), [captura manual de rendimiento](../evidencias/integrante_A/TC06_03_Performance.png) y [cinco mediciones Selenium](../evidencias/integrante_A/TC06_resultados.csv). Las cinco mediciones superan 3000 ms, pero este resultado de ejecución no corrige la ausencia del límite en RF-01.
- RF-03: [acceso no autorizado](../evidencias/integrante_A/RF03_Acceso_No_Autorizado.png) y [cierre de sesión](../evidencias/integrante_A/RF03_Cierre_Sesion.png).
- RF-04: [opciones observadas en TC10](../evidencias/integrante_A/TC10_Menu_Lateral.png), [estado anterior al reinicio](../evidencias/integrante_A/TC11_01_Antes_Reset.png) y [botón Remove después del reinicio](../evidencias/integrante_A/TC11_02_Despues_Reset_Remove.png).
- RF-05: [detalle comprobado en TC13](../evidencias/integrante_A/TC13_01_Detalle.png).
- RF-07: [productos agregados](../evidencias/integrante_A/RF07_Productos_Agregados.png), [contenido del carrito](../evidencias/integrante_A/RF07_Contenido_Carrito.png) y [producto eliminado](../evidencias/integrante_A/RF07_Producto_Eliminado.png).
- RF-08: [nombre obligatorio](../evidencias/integrante_A/RF08_Nombre_Obligatorio.png), [apellido obligatorio](../evidencias/integrante_A/RF08_Apellido_Obligatorio.png) y [código postal obligatorio](../evidencias/integrante_A/RF08_Codigo_Postal_Obligatorio.png).
- RF-09: [resumen de compra](../evidencias/integrante_A/RF09_Resumen_Compra.png).

