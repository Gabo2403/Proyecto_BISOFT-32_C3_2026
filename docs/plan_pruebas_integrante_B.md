
# Plan de pruebas — Swag Labs

## 1. Información general

- Proyecto: Pruebas automatizadas de Swag Labs.
- Curso: Calidad, Verificación y Validación del Software.
- Universidad: CENFOTEC.
- Aplicación bajo prueba: https://www.saucedemo.com/
- Responsable del documento: Carlos Quesada Ramírez.
- Fecha: 25 de noviembre de 2026.

## 2. Objetivo

Evaluar la calidad del sistema Swag Labs mediante pruebas funcionales y no funcionales, identificando defectos, verificando el cumplimiento de los requerimientos y generando evidencias que permitan respaldar los resultados obtenidos.

### 2.1 Objetivos específicos

- Verificar el cumplimiento de los requerimientos funcionales.
- Evaluar los flujos principales de autenticación, inventario, carrito y compra.
- Ejecutar pruebas automatizadas mediante Selenium WebDriver.
- Evaluar la compatibilidad entre diferentes navegadores.
- Analizar el rendimiento y la accesibilidad mediante Lighthouse.
- Identificar, documentar y comunicar los defectos encontrados.
- Mantener la trazabilidad entre requerimientos, casos y resultados.

## 3. Alcance

El proyecto contempla 10 requerimientos funcionales y 34 casos de prueba diseñados por el equipo.

| ID | Funcionalidad |
|---|---|
| RF-01 | Autenticación exitosa |
| RF-02 | Validación de credenciales |
| RF-03 | Gestión de sesión y acceso autorizado |
| RF-04 | Menú de navegación y restablecimiento del estado |
| RF-05 | Visualización del catálogo y detalle de productos |
| RF-06 | Ordenamiento de productos |
| RF-07 | Gestión del carrito de compras |
| RF-08 | Validación de información del comprador |
| RF-09 | Resumen y cálculo de la compra |
| RF-10 | Confirmación y finalización de la compra |

### 3.1 Pruebas no funcionales

- Rendimiento de las pantallas principales.
- Accesibilidad mediante auditorías automatizadas.
- Compatibilidad entre Chrome, Firefox y Microsoft Edge.

### 3.2 Fuera del alcance

- Pruebas de penetración y seguridad avanzada.
- Pruebas de carga masiva y estrés.
- Integraciones con medios de pago reales.
- Validaciones directas sobre bases de datos.
- Modificaciones al código fuente de Swag Labs.
- Certificación completa de accesibilidad.

## 4. Estrategia de pruebas

| Tipo de prueba | Estrategia | Herramienta |
|---|---|---|
| Revisión estática | Inspección de requerimientos, criterios y casos | Documentación y lista de cotejo |
| Funcional manual | Ejecución de escenarios positivos y negativos | Navegador web |
| Funcional automatizada | Validación de flujos y resultados esperados | Selenium y JUnit |
| Compatibilidad | Ejecución de la suite en distintos navegadores | Selenium WebDriver |
| Rendimiento | Auditoría de carga de las pantallas principales | Lighthouse |
| Accesibilidad | Evaluación automatizada de elementos y estructura | Lighthouse |
| Regresión | Reejecución de casos después de modificaciones | Maven y Selenium |

Se priorizan los flujos críticos relacionados con la autenticación, la gestión del carrito y la finalización de la compra.

La automatización utiliza el patrón Page Object Model, separando los localizadores y las acciones de las verificaciones realizadas por los casos de prueba.

## 5. Ambiente de pruebas

| Componente | Descripción |
|---|---|
| Aplicación | Swag Labs |
| Sistema operativo | macOS |
| Arquitectura | ARM64 (Apple Silicon) |
| Lenguaje | Java |
| Versión mínima de Java | 17 |
| Maven | 3.10.2 |
| Selenium WebDriver | 4.27.0 |
| Framework de pruebas | JUnit |
| Navegadores | Chrome, Firefox y Microsoft Edge |
| Auditorías no funcionales | Lighthouse |
| Control de versiones | Git y GitHub |
| Editor | Visual Studio Code |
| Reportes | Maven Surefire, HTML y registros de ejecución |

### 5.1 Datos de prueba

Se utilizan las cuentas de prueba proporcionadas por Swag Labs:

- standard_user
- locked_out_user
- problem_user
- performance_glitch_user
- error_user
- visual_user

La contraseña común corresponde a secret_sauce.

Los usuarios especiales permiten evaluar comportamientos diferenciados de la aplicación. Sus resultados deben interpretarse según los criterios de aceptación y el alcance definido para cada caso.

## 6. Matriz de riesgos

Para la evaluación de riesgos se utiliza una escala de 1 a 3.

### 6.1 Escala de valoración

- Probabilidad: baja (1), media (2), alta (3).
- Impacto: bajo (1), medio (2), alto (3).

Nivel de riesgo = Probabilidad × Impacto.

- Bajo: 1–2.
- Medio: 3–4.
- Alto: 6–9.

### 6.2 Identificación y tratamiento

| ID | Riesgo | Prob. | Impacto | Valor | Nivel | Mitigación |
|---|---|---:|---:|---:|---|---|
| R01 | Fallos de autenticación | 2 | 3 | 6 | Alto | Validar escenarios positivos y negativos |
| R02 | Inconsistencias en los productos del carrito | 3 | 3 | 9 | Alto | Comparar productos seleccionados con el contenido del carrito |
| R03 | Errores durante la finalización de la compra | 2 | 3 | 6 | Alto | Ejecutar escenarios completos de checkout |
| R04 | Incompatibilidad entre navegadores | 2 | 3 | 6 | Alto | Ejecutar la suite en diferentes navegadores |
| R05 | Tiempos de respuesta elevados | 3 | 2 | 6 | Alto | Ejecutar mediciones y auditorías de rendimiento |
| R06 | Problemas de accesibilidad | 2 | 2 | 4 | Medio | Realizar auditorías con Lighthouse |
| R07 | Incompatibilidad entre Selenium y los controladores | 3 | 3 | 9 | Alto | Verificar versiones e instalar controladores compatibles |
| R08 | Cobertura incompleta de criterios de aceptación | 3 | 3 | 9 | Alto | Revisar y actualizar la matriz de trazabilidad |
| R09 | Conflictos durante la integración del código | 2 | 2 | 4 | Medio | Utilizar ramas independientes y Pull Requests |
| R10 | Pérdida o sobrescritura de evidencias | 2 | 3 | 6 | Alto | Respaldar los reportes después de cada ejecución |
| R11 | Indisponibilidad de Swag Labs o conexión a Internet | 2 | 3 | 6 | Alto | Registrar el bloqueo y reprogramar la ejecución |
| R12 | Resultados afectados por redirecciones durante auditorías | 2 | 2 | 4 | Medio | Verificar la página auditada y documentar advertencias |

Las valoraciones representan estimaciones cualitativas para priorizar las actividades, no probabilidades medidas estadísticamente.

## 7. Matriz RACI

La matriz RACI establece las responsabilidades de los integrantes del equipo.

- R: Responsable de ejecutar la actividad.
- A: Responsable final de aprobar o supervisar la actividad.
- C: Consultado durante la actividad.
- I: Informado sobre los resultados.

| Actividad | Integrante A | Integrante B | Coordinador |
|---|---|---|---|
| Revisión de requerimientos | R | C | A |
| Revisión estática | R | I | A |
| Matriz de trazabilidad | R | C | A |
| Ejecución de pruebas manuales asignadas | R | I | A |
| Elaboración del plan de pruebas | C | R | A |
| Elaboración de la matriz de riesgos | C | R | A |
| Estimación de esfuerzo | C | R | A |
| Evaluación de compatibilidad | I | R | A |
| Evaluación de rendimiento con Lighthouse | I | R | A |
| Evaluación de accesibilidad | I | R | A |
| Documentación de pruebas no funcionales | I | R | A |
| Corrección de automatización | C | I | R/A |
| Registro y consolidación de defectos | C | C | R/A |
| Actualización del Excel principal | C | C | R/A |
| Actualización del README | I | R | A |
| Integración y entrega final | C | C | R/A |

Esta distribución se basa en las responsabilidades acordadas por el equipo y queda sujeta a su validación final.

## 8. Estimación de tres puntos

Se utiliza la técnica PERT para estimar el esfuerzo requerido por las actividades del proyecto.

La fórmula utilizada es:

E = (O + 4M + P) / 6

Donde:

- O: Estimación optimista.
- M: Estimación más probable.
- P: Estimación pesimista.
- E: Esfuerzo esperado.

Todas las estimaciones se expresan en horas-persona.

| Actividad | O | M | P | E |
|---|---:|---:|---:|---:|
| Revisión de requerimientos | 1 | 2 | 3 | 2,00 |
| Revisión estática y trazabilidad | 1 | 2 | 4 | 2,17 |
| Elaboración del plan de pruebas | 1 | 2 | 3 | 2,00 |
| Diseño de casos de prueba | 2 | 4 | 6 | 4,00 |
| Desarrollo y corrección de automatización | 3 | 5 | 8 | 5,17 |
| Ejecución de pruebas manuales | 1 | 2 | 4 | 2,17 |
| Configuración del ambiente de pruebas | 0,5 | 1 | 3 | 1,25 |
| Pruebas de compatibilidad | 0,5 | 1 | 3 | 1,25 |
| Auditorías de rendimiento y accesibilidad | 0,5 | 1 | 2 | 1,08 |
| Documentación de defectos | 1 | 2 | 3 | 2,00 |
| Documentación de resultados | 1 | 2 | 3 | 2,00 |
| Integración y revisión final | 1 | 2 | 4 | 2,17 |
| **Total** | **12,5** | **26** | **46** | **27,08** |

El esfuerzo esperado total es de 27,08 horas-persona, calculado utilizando los valores sin redondear.

Estas estimaciones son referenciales y no representan un registro de las horas efectivamente trabajadas. El esfuerzo puede distribuirse entre los integrantes mediante actividades paralelas.

## 9. Criterios de entrada

Las pruebas podrán iniciarse cuando se cumplan las siguientes condiciones:

- Los requerimientos y criterios de aceptación estén disponibles.
- Los casos de prueba estén documentados.
- La aplicación Swag Labs esté disponible.
- Los datos y usuarios de prueba estén identificados.
- El repositorio del proyecto esté accesible.
- Java, Maven y Selenium estén configurados.
- Los navegadores y controladores necesarios estén disponibles.
- Exista conexión a Internet.
- Se disponga de una estructura para almacenar evidencias.
- Las responsabilidades del equipo estén definidas.

## 10. Criterios de salida

Se establecen los siguientes criterios propuestos para el cierre de las pruebas:

- Todos los casos planificados deben contar con un resultado o una justificación de bloqueo.
- Los requerimientos deben mantener trazabilidad con sus respectivos casos.
- Los defectos encontrados deben estar documentados y clasificados.
- Las pruebas automatizadas deben contar con reportes de ejecución.
- Las pruebas de compatibilidad deben registrar resultados por navegador.
- Las auditorías de rendimiento y accesibilidad deben contar con evidencias.
- Las limitaciones del ambiente deben estar documentadas.
- No deben permanecer defectos críticos sin evaluar.
- Los riesgos y defectos pendientes deben ser comunicados al equipo.
- La documentación y las evidencias deben estar disponibles en el repositorio.

El cierre de la ejecución no implica necesariamente la aprobación del sistema. Los incumplimientos y defectos pendientes deben reflejarse en el informe final.

## 11. Criterios de suspensión y reanudación

### 11.1 Suspensión

Las pruebas podrán suspenderse cuando:

- La aplicación no esté disponible.
- Existan errores de configuración que impidan iniciar las pruebas.
- Los controladores no sean compatibles con los navegadores.
- Se produzcan bloqueos que impidan continuar con los flujos requeridos.
- Las condiciones del ambiente invaliden los resultados.

### 11.2 Reanudación

Las pruebas podrán reanudarse cuando:

- La aplicación vuelva a estar disponible.
- Se corrijan los problemas de configuración.
- Se verifique el funcionamiento del navegador y su controlador.
- Se restablezcan las condiciones necesarias para obtener resultados confiables.

## 12. Gestión de defectos

Cada defecto identificado deberá documentarse con:

- Identificador único.
- Título y descripción.
- Requerimiento y caso relacionado.
- Ambiente de ejecución.
- Precondiciones.
- Pasos para reproducirlo.
- Resultado esperado.
- Resultado obtenido.
- Severidad y prioridad.
- Evidencia asociada.
- Estado del defecto.

### 12.1 Clasificación de severidad

| Severidad | Descripción |
|---|---|
| Crítica | Impide completar una funcionalidad esencial sin alternativa |
| Alta | Afecta considerablemente una funcionalidad principal |
| Media | Afecta parcialmente una funcionalidad |
| Baja | Problema menor, visual o de impacto reducido |

Los errores de infraestructura y automatización deben distinguirse de los defectos confirmados de la aplicación.

## 13. Métricas de evaluación

| Métrica | Fórmula |
|---|---|
| Cobertura por requerimiento | Requerimientos vinculados / Requerimientos definidos × 100 |
| Porcentaje de ejecución | Casos ejecutados / Casos planificados × 100 |
| Tasa de aprobación | Casos aprobados / Casos ejecutados × 100 |
| Tasa de errores | Casos con error / Casos ejecutados × 100 |
| Tasa de fallos | Casos fallidos / Casos ejecutados × 100 |
| Compatibilidad | Comparación de resultados entre navegadores |
| Rendimiento | Puntuación y métricas obtenidas mediante Lighthouse |
| Accesibilidad | Puntuación de las comprobaciones automatizadas de Lighthouse |

La cobertura por identificador de requerimiento no equivale necesariamente a una cobertura completa de sus criterios de aceptación.

## 14. Consideraciones de trazabilidad

La revisión realizada por el integrante A identificó cobertura por identificador en los 10 requerimientos, pero también detectó brechas específicas:

- RF-04/CA3: validación de la opción All Items.
- RF-07/CA3: comparación exacta de los productos seleccionados con el carrito.
- RF-09/CA1: validación del contenido del resumen de compra.

También se identificaron ambigüedades relacionadas con los usuarios especiales, el catálogo esperado, el redondeo de impuestos y el límite de rendimiento de TC06.

Estas observaciones deben considerarse durante la interpretación de los resultados y la evaluación final del cumplimiento.

## 15. Entregables

- Plan de pruebas.
- Matriz de riesgos.
- Matriz RACI.
- Estimación de tres puntos.
- Matriz de trazabilidad.
- Casos de prueba.
- Resultados de pruebas manuales y automatizadas.
- Registro de defectos.
- Reportes de compatibilidad.
- Reportes de rendimiento y accesibilidad.
- Capturas y registros de ejecución.
- README actualizado.
- Informe final consolidado.

## 16. Documentación relacionada

- [Revisión de trazabilidad](trazabilidad_requerimientos_casos_integrante_A.md)
- [Resultados de pruebas manuales](resultados_pruebas_manuales_integrante_A.md)
- [Resultados de pruebas no funcionales](pruebas_no_funcionales_integrante_B.md)
- [Evidencias de compatibilidad](../evidencias/integrante_B/compatibilidad/)
- [Evidencias de Lighthouse](../evidencias/integrante_B/lighthouse/)
- [README del proyecto](../README.md)