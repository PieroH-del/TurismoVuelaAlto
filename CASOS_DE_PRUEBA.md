# CASOS DE PRUEBA DEL SISTEMA
## Sistema de Gestión de Turismo Vuela Alto

---

## Información General

| Campo | Detalle |
|-------|---------|
| **Proyecto:** | Sistema de Gestión de Turismo Vuela Alto |
| **Versión:** | 1.0.0 |
| **Fecha de Pruebas:** | Noviembre 2025 |
| **Responsable:** | [TU NOMBRE] |
| **Tecnología:** | Spring Boot 4.0, MySQL 8, Thymeleaf |

---

## Módulo 1: Gestión de Destinos

### CP-D-01: Crear Nuevo Destino con Datos Válidos

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-01 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Crear destino |
| **Prioridad:** | Alta |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.1 - Ingresar destinos nuevos

**Precondiciones:**
- El sistema está en ejecución
- Se accede a http://localhost:8080/destinos
- Se hace clic en "Nuevo Destino"

**Datos de Entrada:**
- Nombre: "Playa El Paraíso"
- Descripción: "Hermosa playa con arenas blancas y aguas cristalinas"
- Estado: "A" (Activo)

**Pasos de Ejecución:**
1. Acceder a /destinos/nuevo
2. Completar campo "Nombre" con "Playa El Paraíso"
3. Completar campo "Descripción" con la descripción proporcionada
4. Seleccionar estado "A"
5. Hacer clic en botón "Guardar"

**Resultado Esperado:**
- ✅ El destino se guarda en la base de datos
- ✅ Se muestra mensaje: "Destino guardado correctamente"
- ✅ Se redirige a /destinos mostrando el nuevo destino en la lista
- ✅ El estado aparece con badge verde (Activo)

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**
_[Anotar aquí cualquier observación durante la prueba]_

**Capturas:**
_[Adjuntar captura de pantalla del destino creado]_

---

### CP-D-02: Validación de Campo Nombre Vacío

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-02 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Validación de datos |
| **Prioridad:** | Alta |
| **Tipo:** | Validación |

**Requisito Asociado:** RNF10.V1 - Campos obligatorios

**Precondiciones:**
- Estar en formulario de nuevo destino (/destinos/nuevo)

**Datos de Entrada:**
- Nombre: "" (vacío)
- Descripción: "Cualquier descripción"
- Estado: "A"

**Pasos de Ejecución:**
1. Dejar el campo "Nombre" vacío
2. Completar descripción
3. Seleccionar estado "A"
4. Hacer clic en "Guardar"

**Resultado Esperado:**
- ✅ El sistema NO guarda el destino
- ✅ Se muestra mensaje de error: "El nombre del destino es obligatorio"
- ✅ El mensaje aparece en color rojo debajo del campo nombre
- ✅ El formulario permanece en pantalla con los datos ingresados

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-D-03: Editar Destino Existente

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-03 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Actualizar destino |
| **Prioridad:** | Alta |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.4 - Modificar destinos existentes

**Precondiciones:**
- Existe al menos un destino en la base de datos
- ID del destino: 1
- Nombre actual: "Playa El Paraíso"

**Datos de Entrada:**
- Nombre: "Playa El Paraíso Premium"
- Descripción: "Descripción actualizada con más detalles"
- Estado: "A"

**Pasos de Ejecución:**
1. Acceder a /destinos
2. Localizar el destino con ID 1
3. Hacer clic en botón "Editar" (amarillo)
4. Modificar el nombre a "Playa El Paraíso Premium"
5. Actualizar la descripción
6. Hacer clic en "Actualizar"

**Resultado Esperado:**
- ✅ El destino se actualiza en la base de datos
- ✅ Se muestra mensaje: "Destino actualizado correctamente"
- ✅ En la lista aparece el nombre actualizado
- ✅ Los datos se reflejan correctamente en el detalle

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-D-04: Inactivar Destino

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-04 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Inactivar destino (soft delete) |
| **Prioridad:** | Media |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.9 - Inactivar destinos

**Precondiciones:**
- Existe un destino activo con ID 1
- Estado actual: "A"

**Datos de Entrada:**
- ID del destino: 1

**Pasos de Ejecución:**
1. Acceder a /destinos
2. Localizar destino con ID 1
3. Verificar que el badge sea verde (Activo)
4. Hacer clic en botón "Inactivar" (rojo)

**Resultado Esperado:**
- ✅ El estado cambia de "A" a "I" en la base de datos
- ✅ Se muestra mensaje: "Destino inactivado correctamente"
- ✅ El badge cambia de verde a rojo
- ✅ El destino sigue apareciendo en la lista (no se elimina)

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-D-05: Buscar Destino por Nombre

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-05 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Búsqueda de destinos |
| **Prioridad:** | Media |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.2 - Búsqueda de destinos

**Precondiciones:**
- Existen múltiples destinos en la base de datos
- Existe un destino con nombre que contiene "playa"

**Datos de Entrada:**
- Término de búsqueda: "playa"

**Pasos de Ejecución:**
1. Acceder a /destinos
2. Ingresar "playa" en el campo de búsqueda
3. Hacer clic en botón "Buscar"

**Resultado Esperado:**
- ✅ Se muestran solo destinos que contienen "playa" en el nombre
- ✅ La búsqueda no distingue mayúsculas/minúsculas
- ✅ Si no hay resultados, se muestra mensaje apropiado

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-D-06: Ver Detalle de Destino con Actividades

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-D-06 |
| **Módulo:** | Destinos |
| **Funcionalidad:** | Visualizar detalle completo |
| **Prioridad:** | Media |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.10 - Consultar destinos con actividades

**Precondiciones:**
- Existe un destino con ID 1
- El destino tiene al menos 2 actividades asociadas

**Datos de Entrada:**
- ID del destino: 1

**Pasos de Ejecución:**
1. Acceder a /destinos
2. Hacer clic en "Ver Detalle" del destino ID 1

**Resultado Esperado:**
- ✅ Se muestra información completa del destino
- ✅ Se listan todas las actividades asociadas
- ✅ Cada actividad muestra: nombre, precio, duración, estado
- ✅ Hay botón para editar el destino
- ✅ Hay botón para volver a la lista

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

## Módulo 2: Gestión de Actividades

### CP-A-01: Crear Nueva Actividad con Datos Válidos

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-01 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Crear actividad |
| **Prioridad:** | Alta |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.6 - Ingresar actividades

**Precondiciones:**
- Existe al menos un destino activo (ID 1)
- Se accede a /actividades/nuevo

**Datos de Entrada:**
- Destino: Seleccionar ID 1
- Nombre: "Buceo en arrecife"
- Precio: 45.50
- Duración: 3
- Estado: "A"

**Pasos de Ejecución:**
1. Acceder a /actividades/nuevo
2. Seleccionar destino del dropdown
3. Ingresar nombre "Buceo en arrecife"
4. Ingresar precio 45.50
5. Ingresar duración 3 horas
6. Seleccionar estado "A"
7. Hacer clic en "Guardar"

**Resultado Esperado:**
- ✅ La actividad se guarda correctamente
- ✅ Se muestra mensaje: "Actividad guardada correctamente"
- ✅ Se redirige a /actividades mostrando la nueva actividad
- ✅ El precio se muestra formateado correctamente

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-A-02: Validación de Precio Negativo

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-02 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Validación de precio |
| **Prioridad:** | Alta |
| **Tipo:** | Validación |

**Requisito Asociado:** RNF10.V2 - Validación de precio positivo

**Precondiciones:**
- Estar en formulario de nueva actividad

**Datos de Entrada:**
- Destino: ID 1
- Nombre: "Actividad de prueba"
- Precio: -10.00
- Duración: 2
- Estado: "A"

**Pasos de Ejecución:**
1. Completar todos los campos
2. Ingresar precio negativo (-10.00)
3. Hacer clic en "Guardar"

**Resultado Esperado:**
- ✅ El sistema NO guarda la actividad
- ✅ Se muestra mensaje: "El precio debe ser mayor a 0"
- ✅ El mensaje aparece en rojo debajo del campo precio

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-A-03: Validación de Duración Cero

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-03 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Validación de duración |
| **Prioridad:** | Alta |
| **Tipo:** | Validación |

**Requisito Asociado:** RNF10.V3 - Validación de duración positiva

**Precondiciones:**
- Estar en formulario de nueva actividad

**Datos de Entrada:**
- Destino: ID 1
- Nombre: "Actividad de prueba"
- Precio: 25.00
- Duración: 0
- Estado: "A"

**Pasos de Ejecución:**
1. Completar todos los campos
2. Ingresar duración 0
3. Hacer clic en "Guardar"

**Resultado Esperado:**
- ✅ El sistema NO guarda la actividad
- ✅ Se muestra mensaje: "La duración debe ser mayor a 0"

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-A-04: Editar Actividad Existente

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-04 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Actualizar actividad |
| **Prioridad:** | Alta |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.7 - Modificar actividades

**Precondiciones:**
- Existe actividad con ID 1
- Precio actual: 45.50

**Datos de Entrada:**
- Nuevo precio: 50.00

**Pasos de Ejecución:**
1. Acceder a /actividades
2. Hacer clic en "Editar" de la actividad ID 1
3. Cambiar precio a 50.00
4. Hacer clic en "Actualizar"

**Resultado Esperado:**
- ✅ La actividad se actualiza correctamente
- ✅ Se muestra mensaje: "Actividad actualizada correctamente"
- ✅ El nuevo precio se refleja en la lista

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-A-05: Validación de Destino Obligatorio

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-05 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Validación de relación |
| **Prioridad:** | Alta |
| **Tipo:** | Validación |

**Requisito Asociado:** RNF10.V4 - Selección de destino obligatoria

**Precondiciones:**
- Estar en formulario de nueva actividad

**Datos de Entrada:**
- Destino: (sin seleccionar)
- Nombre: "Actividad sin destino"
- Precio: 25.00
- Duración: 2
- Estado: "A"

**Pasos de Ejecución:**
1. NO seleccionar destino del dropdown
2. Completar los demás campos
3. Hacer clic en "Guardar"

**Resultado Esperado:**
- ✅ El sistema NO guarda la actividad
- ✅ Se muestra mensaje: "Debe seleccionar un destino válido"

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

### CP-A-06: Inactivar Actividad

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-A-06 |
| **Módulo:** | Actividades |
| **Funcionalidad:** | Inactivar actividad |
| **Prioridad:** | Media |
| **Tipo:** | Funcional |

**Requisito Asociado:** RF10.8 - Inactivar actividades

**Precondiciones:**
- Existe actividad activa con ID 1

**Datos de Entrada:**
- ID actividad: 1

**Pasos de Ejecución:**
1. Acceder a /actividades
2. Hacer clic en "Inactivar" de actividad ID 1

**Resultado Esperado:**
- ✅ El estado cambia de "A" a "I"
- ✅ Se muestra mensaje: "Actividad inactivada correctamente"
- ✅ El badge cambia de verde a rojo

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

**Observaciones:**

**Capturas:**

---

## Módulo 3: Integración y Navegación

### CP-I-01: Navegación desde Home a Destinos

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-I-01 |
| **Módulo:** | Navegación |
| **Funcionalidad:** | Enlaces de navegación |
| **Prioridad:** | Media |
| **Tipo:** | Funcional |

**Pasos de Ejecución:**
1. Acceder a http://localhost:8080
2. Hacer clic en "Gestionar Destinos"

**Resultado Esperado:**
- ✅ Se navega a /destinos
- ✅ Se muestra la lista de destinos

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

---

### CP-I-02: Responsive Design en Móvil

| Atributo | Detalle |
|----------|---------|
| **ID del Caso:** | CP-I-02 |
| **Módulo:** | UI/UX |
| **Funcionalidad:** | Diseño responsive |
| **Prioridad:** | Baja |
| **Tipo:** | No funcional |

**Pasos de Ejecución:**
1. Acceder al sistema desde un dispositivo móvil o usar DevTools (F12)
2. Cambiar vista a móvil (375x667px - iPhone SE)
3. Navegar por las diferentes páginas

**Resultado Esperado:**
- ✅ Las tablas se adaptan correctamente
- ✅ Los botones son clickeables en móvil
- ✅ No hay scroll horizontal
- ✅ El menú se adapta (hamburguesa en móvil)

**Resultado Obtenido:**
- [ ] Exitoso
- [ ] Fallido
- [ ] Pendiente

---

## Resumen de Resultados

### Tabla Resumen

| Módulo | Total Casos | Exitosos | Fallidos | Pendientes | % Éxito |
|--------|-------------|----------|----------|------------|---------|
| Destinos | 6 | - | - | - | - |
| Actividades | 6 | - | - | - | - |
| Integración | 2 | - | - | - | - |
| **TOTAL** | **14** | **-** | **-** | **-** | **-%** |

### Severidad de Defectos Encontrados

| Severidad | Cantidad | Descripción |
|-----------|----------|-------------|
| Crítica | - | Bloquea funcionalidad principal |
| Alta | - | Afecta funcionalidad importante |
| Media | - | Afecta usabilidad |
| Baja | - | Mejoras estéticas |

---

## Conclusiones

**Estado General del Sistema:**
_[Completar después de ejecutar las pruebas]_

**Funcionalidades Verificadas:**
- [ ] CRUD completo de Destinos
- [ ] CRUD completo de Actividades
- [ ] Validaciones de datos
- [ ] Relaciones entre entidades
- [ ] Navegación y UI/UX

**Recomendaciones:**
_[Anotar mejoras sugeridas después de las pruebas]_

---

## Notas Adicionales

- **Ambiente de Pruebas:** Desarrollo local
- **Base de Datos:** MySQL 8.0 - turismo_db
- **Navegador:** Chrome/Firefox
- **Resolución de Pantalla:** 1920x1080

---

**Preparado por:** [TU NOMBRE]  
**Fecha:** Noviembre 2025  
**Versión del Documento:** 1.0
