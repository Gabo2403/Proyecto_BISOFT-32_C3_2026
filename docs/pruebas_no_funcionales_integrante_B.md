
# Resultados de pruebas no funcionales — Integrante B

## 1. Información general

- Proyecto: Pruebas automatizadas de Swag Labs.
- Curso: Calidad, Verificación y Validación del Software.
- Universidad: CENFOTEC.
- Responsable: Carlos Quesada Ramírez.
- Fecha de ejecución: 25 de noviembre de 2026.
- Aplicación evaluada: https://www.saucedemo.com/

## 2. Objetivo

Evaluar la compatibilidad, el rendimiento y la accesibilidad de Swag Labs mediante pruebas automatizadas en diferentes navegadores y auditorías realizadas con Google Lighthouse.

## 3. Ambiente de ejecución

| Componente | Descripción |
|---|---|
| Sistema operativo | macOS |
| Arquitectura | ARM64 (Apple Silicon) |
| Maven | 3.10.2 |
| Selenium WebDriver | 4.27.0 |
| Framework de pruebas | JUnit |
| Firefox | 161.0 |
| Microsoft Edge | 158.0.4370.33 |
| Microsoft Edge WebDriver | 158.0.4370.33 |
| Navegador para Lighthouse | Google Chrome |
| Modalidad de Lighthouse | Desktop |
| Editor | Visual Studio Code |

## 4. Pruebas de compatibilidad

### 4.1 Metodología

Se ejecutó la misma suite de pruebas automatizadas en Mozilla Firefox y Microsoft Edge, utilizando Selenium WebDriver y Maven.

El objetivo consistió en comparar los resultados funcionales obtenidos en ambos navegadores, identificar diferencias de comportamiento y registrar posibles problemas de compatibilidad.

Se conservaron los registros de ejecución, reportes de Surefire, reportes HTML y capturas generadas durante las pruebas.

### 4.2 Ejecución en Firefox

Comando utilizado:

```bash
mvn test -Dnavegador=firefox
```

| Indicador | Resultado |
|---|---:|
| Pruebas ejecutadas | 25 |
| Pruebas aprobadas | 23 |
| Fallos de aserción | 0 |
| Errores | 2 |
| Pruebas omitidas | 0 |
| Tasa de aprobación | 92 % |
| Tasa de errores | 8 % |
| Tiempo total aproximado | 1 min 47 s |

Durante la configuración inicial, Firefox presentó el mensaje Profile Missing, debido a un problema de inicialización del perfil.

El inconveniente se resolvió iniciando el navegador manualmente antes de ejecutar nuevamente la suite.

### 4.3 Ejecución en Microsoft Edge

La primera ejecución presentó un problema relacionado con la descarga automática de Microsoft Edge WebDriver por parte de Selenium.

Este inconveniente impidió inicialmente la ejecución funcional de las pruebas.

Para resolverlo se descargó el controlador oficial correspondiente a la versión 158.0.4370.33 para Apple Silicon y se configuró su ubicación mediante una propiedad de ejecución de Maven.

Comando utilizado:

```bash
mvn clean test -Dnavegador=edge -Dwebdriver.edge.driver="$HOME/Downloads/edgedriver_mac64_m1/msedgedriver"
```

La ubicación del controlador corresponde al ambiente local utilizado y deberá ajustarse cuando se ejecute desde otro equipo.

No fue necesario modificar el código fuente ni los archivos de configuración compartidos del proyecto.

| Indicador | Resultado |
|---|---:|
| Pruebas ejecutadas | 25 |
| Pruebas aprobadas | 23 |
| Fallos de aserción | 0 |
| Errores | 2 |
| Pruebas omitidas | 0 |
| Tasa de aprobación | 92 % |
| Tasa de errores | 8 % |
| Tiempo total aproximado | 1 min 13 s |

Durante la ejecución también se presentaron advertencias relacionadas con la compatibilidad de Chrome DevTools Protocol (CDP) y la versión de Selenium. Estas advertencias no impidieron completar la ejecución de la suite.

### 4.4 Comparación de resultados

| Indicador | Firefox | Microsoft Edge |
|---|---:|---:|
| Pruebas ejecutadas | 25 | 25 |
| Pruebas aprobadas | 23 | 23 |
| Fallos de aserción | 0 | 0 |
| Errores | 2 | 2 |
| Pruebas omitidas | 0 | 0 |
| Tasa de aprobación | 92 % | 92 % |
| Tasa de errores | 8 % | 8 % |
| Tiempo total aproximado | 107 s | 73 s |

Se registraron 50 ejecuciones acumuladas entre ambos navegadores, de las cuales 46 fueron satisfactorias y 4 presentaron errores.

Estas cifras corresponden a ejecuciones acumuladas de la misma suite y no representan 50 casos de prueba diferentes.

### 4.5 Análisis de los errores

En Microsoft Edge se identificaron errores en los siguientes métodos de prueba:

- compraConUsuarioConErrores.
- errorUserAgregaProducto.

Los errores se relacionan con tiempos de espera de Selenium durante escenarios asociados a usuarios especiales de Swag Labs.

Ambos navegadores presentaron el mismo número de errores. Sin embargo, esto no permite atribuir automáticamente su causa a un defecto de la aplicación, de la automatización o de un navegador específico.

Se recomienda revisar los criterios de aceptación, los localizadores y las condiciones de espera de los casos afectados.

### 4.6 Conclusión de compatibilidad

Los resultados muestran un comportamiento global consistente entre Firefox y Microsoft Edge, con una tasa de aprobación del 92 % en ambos ambientes.

Microsoft Edge presentó un menor tiempo total de ejecución en esta medición. No obstante, al tratarse de una ejecución por navegador, no se puede concluir que Edge sea sistemáticamente más rápido que Firefox.

No se identificaron diferencias en la tasa global de aprobación entre los navegadores evaluados.

La compatibilidad observada se limita a los escenarios automatizados ejecutados y no constituye una garantía de compatibilidad completa de la aplicación.

## 5. Evaluación mediante Lighthouse

### 5.1 Metodología

Se utilizó Google Lighthouse desde las herramientas de desarrollo de Google Chrome, en una ventana de navegación privada.

Las auditorías se realizaron bajo la siguiente configuración:

- Modo: Navigation.
- Dispositivo: Desktop.
- Categorías: Performance y Accessibility.
- Usuario autenticado para pantallas internas: standard_user.

Se seleccionaron cuatro pantallas representativas de los principales flujos de la aplicación:

1. Inicio de sesión.
2. Catálogo de productos.
3. Carrito de compras.
4. Formulario de información del comprador.

### 5.2 Escala de interpretación

Lighthouse utiliza una escala de puntuación de 0 a 100.

| Puntuación | Interpretación |
|---|---|
| 0–49 | Deficiente |
| 50–89 | Necesita mejoras |
| 90–100 | Bueno |

Esta clasificación corresponde a la interpretación de Lighthouse y no sustituye criterios de aceptación formalmente establecidos por el equipo.

## 6. Resultados de rendimiento

| Pantalla | Ruta evaluada | Puntuación |
|---|---|---:|
| Inicio de sesión | / | 99 |
| Catálogo | /inventory.html | 99 |
| Carrito | /cart.html | 99 |
| Checkout | /checkout-step-one.html | 97 |
| Promedio descriptivo | | 98,5 |

Las cuatro pantallas obtuvieron puntuaciones dentro del rango considerado bueno por Lighthouse.

El formulario de checkout presentó la puntuación más baja, con 97 puntos, aunque permanece dentro del rango favorable.

### 6.1 Métricas de inicio de sesión

| Métrica | Resultado |
|---|---:|
| First Contentful Paint (FCP) | 0,5 s |
| Largest Contentful Paint (LCP) | 0,9 s |

First Contentful Paint representa el tiempo transcurrido hasta que se visualiza el primer contenido de la página.

Largest Contentful Paint representa el tiempo requerido para visualizar el elemento de contenido de mayor tamaño identificado durante la carga.

### 6.2 Repetición de la auditoría del catálogo

Durante la primera auditoría del catálogo se obtuvo una puntuación de rendimiento de 100 y una puntuación de accesibilidad de 100.

Debido a una advertencia de redirección, se realizó una segunda auditoría, obteniendo 99 puntos de rendimiento y 100 puntos de accesibilidad.

Para la comparación consolidada se utilizó el resultado de la segunda ejecución, correspondiente al reporte HTML conservado como evidencia.

### 6.3 Relación con el caso TC06

La revisión del integrante A identificó tiempos superiores al límite de tres segundos establecido en el caso TC06 para el usuario performance_glitch_user.

Los resultados favorables de Lighthouse no contradicen necesariamente dicho hallazgo, debido a que corresponden a mediciones y condiciones diferentes.

Las auditorías de Lighthouse evalúan la carga de páginas bajo condiciones específicas, mientras que TC06 mide un escenario funcional asociado a un usuario especial.

Adicionalmente, la revisión de trazabilidad identificó que el límite de tres segundos de TC06 no se encuentra explícitamente establecido en RF-01.

## 7. Resultados de accesibilidad

| Pantalla | Puntuación |
|---|---:|
| Inicio de sesión | 100 |
| Catálogo | 100 |
| Carrito | 100 |
| Checkout | 100 |
| Promedio descriptivo | 100 |

Las cuatro auditorías obtuvieron la puntuación máxima en las comprobaciones automatizadas de accesibilidad de Lighthouse.

Estos resultados constituyen un indicador favorable dentro del alcance de la herramienta.

Sin embargo, una puntuación de 100 no garantiza el cumplimiento completo de WCAG ni la ausencia de barreras de accesibilidad.

Para una evaluación más amplia se recomienda complementar las auditorías con:

- Navegación mediante teclado.
- Verificación manual del orden de enfoque.
- Pruebas con lectores de pantalla.
- Evaluación de mensajes de error.
- Revisión de la accesibilidad de los flujos interactivos.

Estas comprobaciones complementarias no fueron ejecutadas como parte de las auditorías aquí documentadas.

## 8. Incidencias y limitaciones

| ID | Observación | Impacto | Tratamiento |
|---|---|---|---|
| OBS-01 | Error inicial Profile Missing en Firefox | Impedía iniciar el navegador | Apertura manual del navegador y reejecución |
| OBS-02 | Error de descarga automática de EdgeDriver | Bloqueaba la ejecución de Edge | Instalación manual del controlador oficial compatible |
| OBS-03 | Advertencias de compatibilidad CDP | Posible limitación de funciones avanzadas de Selenium | Registro de la advertencia y continuación de las pruebas |
| OBS-04 | Errores de tiempo de espera en escenarios con usuarios especiales | Dos ejecuciones con error por navegador | Revisión recomendada de automatización y criterios |
| OBS-05 | Advertencias de redirección en Lighthouse | Posible afectación de las mediciones | Conservación de reportes y verificación recomendada de las capturas auditadas |
| OBS-06 | Auditorías limitadas a Desktop | No permite concluir sobre dispositivos móviles | Ampliar las auditorías en futuras ejecuciones |
| OBS-07 | Accesibilidad evaluada mediante comprobaciones automatizadas | Cobertura parcial de accesibilidad | Complementar con pruebas manuales |
| OBS-08 | Número reducido de mediciones | Variabilidad de rendimiento no caracterizada | Repetir mediciones en futuras evaluaciones |

Las auditorías del catálogo, carrito y checkout presentaron advertencias de redirección hacia rutas alternativas de Swag Labs.

Se verificaron visualmente las capturas internas de los cuatro reportes HTML, confirmando que corresponden a las pantallas previstas: inicio de sesión, catálogo, carrito y formulario de información del comprador.

Los reportes del catálogo, carrito y checkout también presentaron errores en el análisis detallado de LCP. Estas advertencias se conservan como limitaciones de las auditorías, sin descartar las puntuaciones reportadas por Lighthouse.

## 9. Evidencias

### 9.1 Compatibilidad

- [Registro de ejecución de Firefox](../evidencias/integrante_B/compatibilidad/firefox.log)
- [Registro de ejecución de Edge](../evidencias/integrante_B/compatibilidad/edge.log)
- [Reportes y capturas de Firefox](../evidencias/integrante_B/compatibilidad/firefox/)
- [Reportes y capturas de Edge](../evidencias/integrante_B/compatibilidad/edge/)

### 9.2 Lighthouse

- [Reporte de inicio de sesión](../evidencias/integrante_B/lighthouse/login_desktop.html)
- [Reporte de catálogo](../evidencias/integrante_B/lighthouse/inventory_desktop.html)
- [Reporte de carrito](../evidencias/integrante_B/lighthouse/cart_desktop.html)
- [Reporte de checkout](../evidencias/integrante_B/lighthouse/checkout_desktop.html)

## 10. Recomendaciones

- Revisar los casos automatizados que presentaron errores de tiempo de espera.
- Confirmar el comportamiento esperado de los usuarios especiales.
- Evaluar la actualización de Selenium para mejorar la compatibilidad con navegadores recientes.
- Mantener un respaldo independiente de las evidencias por navegador.
- Corregir la ubicación del reporte HTML indicada en el README.
- Completar las brechas de cobertura identificadas en la revisión de trazabilidad.
- Complementar Lighthouse con pruebas manuales de accesibilidad.
- Repetir las mediciones de rendimiento bajo condiciones controladas.
- Ampliar las auditorías a dispositivos móviles.
- Verificar las páginas efectivamente evaluadas en los reportes con advertencias de redirección.

## 11. Conclusión general

Las pruebas no funcionales permitieron obtener información sobre la compatibilidad, el rendimiento y la accesibilidad de Swag Labs.

Las ejecuciones automatizadas en Firefox y Microsoft Edge obtuvieron una tasa de aprobación del 92 % en ambos navegadores, mostrando resultados globales consistentes dentro del alcance evaluado.

Las auditorías de Lighthouse reportaron puntuaciones de rendimiento entre 97 y 99 puntos en las mediciones consolidadas, con un promedio descriptivo de 98,5 puntos. Las comprobaciones automatizadas de accesibilidad reportaron 100 puntos en las cuatro pantallas seleccionadas.

Los resultados son favorables dentro del alcance definido, aunque permanecen errores de automatización o comportamiento pendientes de analizar, advertencias de redirección y limitaciones propias de las herramientas utilizadas.

Por lo tanto, los resultados deben incorporarse al informe final junto con las pruebas funcionales, los hallazgos de revisión estática, la matriz de trazabilidad y el registro de defectos del proyecto.