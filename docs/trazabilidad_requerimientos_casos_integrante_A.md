# Revisión de trazabilidad — Integrante A

## Método

Se revisó el vínculo declarado en la columna Requerimiento de los 34 casos y, por separado, la correspondencia entre cada criterio de aceptación y lo que realmente comprueban los pasos y resultados esperados. “Cubierto por ID” solo indica que existe un enlace; no demuestra cobertura completa del criterio ni ejecución satisfactoria.

## Matriz por requerimiento

| Requerimiento | Casos vinculados | Cantidad | Cobertura por ID | Revisión a nivel de criterio |
|---|---|---:|---|---|
| RF-01 | TC05, TC06 | 2 | Sí | Parcial: CA1 y CA2 se cubren para dos usuarios; CA3 afirma “todos los usuarios de prueba” sin casos para todo el conjunto. TC06 añade un límite de 3 s ausente en RF-01. |
| RF-02 | TC01, TC02, TC03, TC04, TC07 | 5 | Sí | Adecuada para campos vacíos, credenciales incorrectas, usuario bloqueado y sensibilidad de mayúsculas. TC07 amplía el criterio de credenciales inválidas. |
| RF-03 | TC08, TC09 | 2 | Sí | Parcialmente adecuada: logout y acceso directo a inventario están cubiertos. La frase general “páginas internas” no se prueba fuera de `/inventory.html`. |
| RF-04 | TC10, TC11 | 2 | Sí | Incompleta: TC10 comprueba presencia de opciones y TC11 Reset App State. Falta activar All Items y verificar el destino de CA3. |
| RF-05 | TC12, TC13, TC33, TC34 | 4 | Sí | Parcial: TC12 y TC13 cubren inventario y un detalle. TC33 y TC34 prueban usuarios especiales. Falta un catálogo explícito para decidir qué datos son “correctos” en todos los productos. |
| RF-06 | TC14, TC15, TC16, TC17 | 4 | Sí | Adecuada: hay un caso por cada opción de ordenamiento. Conviene comparar la secuencia completa, no solo el primer precio. |
| RF-07 | TC18, TC19, TC20, TC21, TC22 | 5 | Sí | Parcial: contador, fronteras, eliminación y Continue Shopping están cubiertos. Falta comprobar directamente que la lista del carrito contiene exactamente los productos seleccionados (CA3). |
| RF-08 | TC23, TC24, TC25, TC26, TC27 | 5 | Sí | Adecuada para obligatoriedad, avance con datos definidos y Cancel. No hay reglas aprobadas para formato o espacios, por lo que no deben inventarse. |
| RF-09 | TC28, TC29 | 2 | Sí | Parcial: subtotal, impuesto y total se comprueban con dos productos. Falta validar explícitamente que el resumen lista exactamente el contenido del carrito (CA1) y definir redondeo. |
| RF-10 | TC30, TC31, TC32 | 3 | Sí | Parcialmente adecuada: confirmación, vaciado y Back Home están cubiertos. Debe confirmarse si error_user pertenece al alcance exitoso antes de usar TC32 como prueba de conformidad. |

## Evidencias disponibles por requerimiento

Las evidencias seleccionadas muestran observaciones concretas de ejecución. No sustituyen los casos faltantes ni convierten una cobertura parcial en completa.

- **RF-01:** [login exitoso](../evidencias/integrante_A/RF01_Login_Exitoso.png).
- **RF-02:** [usuario obligatorio](../evidencias/integrante_A/RF02_Usuario_Obligatorio.png), [contraseña obligatoria](../evidencias/integrante_A/RF02_Contrasena_Obligatoria.png), [credenciales incorrectas](../evidencias/integrante_A/RF02_Credenciales_Incorrectas.png) y [usuario bloqueado](../evidencias/integrante_A/RF02_Usuario_Bloqueado.png).
- **RF-03:** [cierre de sesión](../evidencias/integrante_A/RF03_Cierre_Sesion.png) y [acceso no autorizado](../evidencias/integrante_A/RF03_Acceso_No_Autorizado.png).
- **RF-04:** no quedó una captura con nombre RF-04 en la selección definitiva. Las evidencias de TC10 y TC11 están vinculadas en los documentos específicos de esos casos.
- **RF-05:** no quedó una captura con nombre RF-05 en la selección definitiva. Las evidencias de TC13 están vinculadas en los documentos específicos de ese caso.
- **RF-06:** [nombre ascendente](../evidencias/integrante_A/RF06_Nombre_Ascendente.png), [nombre descendente](../evidencias/integrante_A/RF06_Nombre_Descendente.png), [precio ascendente, parte 1](../evidencias/integrante_A/RF06_Precio_Ascendente_01.png), [precio ascendente, parte 2](../evidencias/integrante_A/RF06_Precio_Ascendente_02.png) y [precio descendente](../evidencias/integrante_A/RF06_Precio_Descendente.png).
- **RF-07:** [productos agregados](../evidencias/integrante_A/RF07_Productos_Agregados.png), [contenido del carrito](../evidencias/integrante_A/RF07_Contenido_Carrito.png) y [producto eliminado](../evidencias/integrante_A/RF07_Producto_Eliminado.png).
- **RF-08:** [nombre obligatorio](../evidencias/integrante_A/RF08_Nombre_Obligatorio.png), [apellido obligatorio](../evidencias/integrante_A/RF08_Apellido_Obligatorio.png) y [código postal obligatorio](../evidencias/integrante_A/RF08_Codigo_Postal_Obligatorio.png).
- **RF-09:** [resumen de compra](../evidencias/integrante_A/RF09_Resumen_Compra.png).
- **RF-10:** [confirmación de compra](../evidencias/integrante_A/RF10_Confirmacion_Compra.png), [carrito vacío](../evidencias/integrante_A/RF10_Carrito_Vacio.png) y [regreso al inventario](../evidencias/integrante_A/RF10_Regreso_Inventario.png).

## Resumen cuantitativo del diseño

- Requerimientos definidos: 10.
- Casos diseñados: 34.
- Requerimientos con al menos un caso vinculado: 10 de 10.
- Casos sin ID de requerimiento: 0.
- IDs de requerimiento usados por casos pero no definidos: 0.
- Estados de ejecución registrados: ninguno en las filas de los 34 casos.
- Cobertura de criterios: no es 100 %; se identificaron brechas directas en RF-04/CA3, RF-07/CA3 y RF-09/CA1, además de ambigüedades de alcance.

## Acciones recomendadas antes de ejecución

| Prioridad | Acción | Motivo |
|---|---|---|
| Alta | Aprobar o retirar el requisito de rendimiento de TC06. | El límite de 3 s no es trazable a RF-01. |
| Alta | Añadir caso para All Items desde una vista distinta al inventario. | Cierra RF-04/CA3. |
| Alta | Añadir caso que compare exactamente dos productos agregados con la lista del carrito. | Cierra RF-07/CA3. |
| Alta | Ampliar el resumen de compra para validar identidad, precio y cantidad de los productos. | Cierra RF-09/CA1. |
| Media | Definir conjunto de usuarios habilitados de RF-01. | Evita una obligación abierta de “todos”. |
| Media | Definir catálogo esperado o limitar RF-05 a datos concretos. | Convierte “correctos” en un oráculo reproducible. |
| Media | Definir regla de redondeo del impuesto. | Evita discrepancias en subtotales con más de dos decimales calculados. |

## Límite de esta revisión

Esta matriz evalúa el diseño documental. No se ejecutó Swag Labs y no se asignaron valores a Estado, Defecto relacionado ni métricas de aprobación.

