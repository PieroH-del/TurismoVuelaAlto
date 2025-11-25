# MANUAL DE USUARIO
## Sistema de Gestión de Turismo Vuela Alto

---

## Tabla de Contenidos
1. [Introducción](#introducción)
2. [Inicio de Sesión](#inicio-de-sesión)
3. [Módulo de Destinos](#módulo-de-destinos)
4. [Módulo de Actividades](#módulo-de-actividades)
5. [Preguntas Frecuentes](#preguntas-frecuentes)

---

## Introducción

Bienvenido al Sistema de Gestión de Turismo Vuela Alto. Este manual le guiará paso a paso en el uso de todas las funcionalidades del sistema.

### ¿Qué puede hacer con este sistema?
- Gestionar destinos turísticos
- Agregar y administrar actividades por destino
- Buscar y filtrar información
- Visualizar reportes de destinos con sus actividades

---

## Cómo Ejecutar el Proyecto

### Paso 1: Verificar Requisitos
Asegúrese de tener instalado:
- Java 17 o superior
- MySQL 8.0
- Maven (opcional, si desea compilar)

### Paso 2: Configurar la Base de Datos

1. Abra **MySQL Workbench** o su cliente MySQL preferido
2. Ejecute el siguiente comando para crear la base de datos:

```sql
CREATE DATABASE turismo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. (Opcional) Ejecute el script `schema.sql` incluido en el proyecto para cargar datos de ejemplo

### Paso 3: Configurar Credenciales

1. Navegue a `src/main/resources/application.properties`
2. Modifique las siguientes líneas con su usuario y contraseña de MySQL:

```properties
spring.datasource.username=root
spring.datasource.password=SU_PASSWORD_AQUI
```

### Paso 4: Ejecutar la Aplicación

**Desde IntelliJ IDEA:**
1. Abra el proyecto en IntelliJ
2. Localice la clase `TurismoVuelaAltoApplication.java`
3. Haga clic derecho → **Run 'TurismoVuelaAltoApplication'**
4. Espere a que aparezca el mensaje: `Started TurismoVuelaAltoApplication in X seconds`

**Desde Terminal:**
1. Abra una terminal en la carpeta del proyecto
2. Ejecute:
   ```bash
   mvn spring-boot:run
   ```

### Paso 5: Acceder al Sistema

1. Abra su navegador web (Chrome, Firefox, Edge)
2. Navegue a: **http://localhost:8080**
3. Verá la página de inicio del sistema

---

## Módulo de Destinos

### 1. Ver Lista de Destinos

**Objetivo:** Visualizar todos los destinos turísticos registrados.

**Pasos:**
1. En la página de inicio, haga clic en el botón **"Gestionar Destinos"**
2. Se mostrará una tabla con todos los destinos
3. Cada destino muestra:
   - Nombre
   - Descripción
   - Estado (Activo/Inactivo - representado con badge verde o rojo)
   - Acciones disponibles

**¿Qué puede hacer aquí?**
- Ver todos los destinos en formato tabla
- Buscar destinos por nombre
- Acceder a crear, editar o ver detalles

---

### 2. Crear Nuevo Destino

**Objetivo:** Agregar un nuevo destino turístico al sistema.

**Pasos:**
1. En la lista de destinos, haga clic en **"Nuevo Destino"** (botón verde superior)
2. Complete el formulario con la siguiente información:
   - **Nombre:** Ingrese el nombre del destino (Ejemplo: "Playa El Paraíso")
   - **Descripción:** Agregue una descripción detallada (Ejemplo: "Hermosa playa con arenas blancas...")
   - **Estado:** Seleccione 'A' para Activo o 'I' para Inactivo
3. Haga clic en **"Guardar"**
4. Si todo es correcto, verá un mensaje de éxito en verde
5. Será redirigido a la lista de destinos donde verá el nuevo registro

**Validaciones:**
- ❌ El nombre NO puede estar vacío
- ❌ El estado debe ser 'A' o 'I' únicamente

**Ejemplo de mensaje de error:**
- Si deja el nombre vacío: "El nombre del destino es obligatorio"

---

### 3. Editar Destino Existente

**Objetivo:** Modificar la información de un destino ya registrado.

**Pasos:**
1. En la lista de destinos, localice el destino que desea editar
2. Haga clic en el botón **"Editar"** (botón amarillo con ícono de lápiz)
3. Se abrirá el formulario con los datos actuales pre-cargados
4. Modifique los campos que desee actualizar
5. Haga clic en **"Actualizar"**
6. Verá un mensaje de confirmación: "Destino actualizado correctamente"

**Nota:** No puede cambiar el ID del destino, este es único y automático.

---

### 4. Ver Detalle de Destino

**Objetivo:** Visualizar toda la información de un destino incluyendo sus actividades asociadas.

**Pasos:**
1. En la lista de destinos, haga clic en **"Ver Detalle"** (botón azul con ícono de ojo)
2. Se mostrará una página con:
   - **Información del Destino:**
     - ID
     - Nombre
     - Descripción completa
     - Estado
   - **Lista de Actividades Asociadas:**
     - Tabla con todas las actividades de este destino
     - Nombre, precio, duración y estado de cada actividad
3. Desde aquí puede hacer clic en **"Editar Destino"** para modificarlo
4. Use el botón **"Volver"** para regresar a la lista

**¿Para qué sirve esta vista?**
- Ver un resumen completo del destino
- Verificar qué actividades están asociadas
- Tomar decisiones sobre ediciones

---

### 5. Buscar Destinos

**Objetivo:** Encontrar destinos específicos por nombre.

**Pasos:**
1. En la lista de destinos, localice el campo de búsqueda en la parte superior
2. Escriba el nombre o parte del nombre del destino (Ejemplo: "playa")
3. Haga clic en el botón **"Buscar"**
4. El sistema mostrará solo los destinos que coincidan con la búsqueda
5. Para ver todos nuevamente, haga clic en **"Gestionar Destinos"** en el menú

**Características:**
- ✅ La búsqueda NO distingue mayúsculas/minúsculas
- ✅ Puede buscar por palabras parciales
- ✅ Ejemplo: "pla" encontrará "Playa El Paraíso"

---

### 6. Inactivar Destino

**Objetivo:** Marcar un destino como inactivo sin eliminarlo de la base de datos.

**Pasos:**
1. En la lista de destinos, localice el destino a inactivar
2. Haga clic en el botón **"Inactivar"** (botón rojo con ícono de prohibido)
3. El sistema cambiará automáticamente el estado a 'I' (Inactivo)
4. Verá un mensaje: "Destino inactivado correctamente"
5. El badge del estado cambiará de verde (Activo) a rojo (Inactivo)

**Nota Importante:**
- ⚠️ Inactivar NO elimina el destino, solo cambia su estado
- ⚠️ Los destinos inactivos seguirán apareciendo en la lista
- ✅ Puede reactivar un destino editándolo y cambiando el estado a 'A'

---

## Módulo de Actividades

### 1. Ver Lista de Actividades

**Objetivo:** Visualizar todas las actividades turísticas registradas.

**Pasos:**
1. En la página de inicio, haga clic en **"Gestionar Actividades"**
2. Se mostrará una tabla con todas las actividades
3. Cada actividad muestra:
   - Nombre de la actividad
   - Destino al que pertenece
   - Precio (en formato moneda)
   - Duración (en horas)
   - Estado (Activo/Inactivo)
   - Acciones disponibles

---

### 2. Crear Nueva Actividad

**Objetivo:** Agregar una nueva actividad asociada a un destino.

**Pasos:**
1. En la lista de actividades, haga clic en **"Nueva Actividad"** (botón verde)
2. Complete el formulario:
   - **Destino:** Seleccione un destino del menú desplegable
     - ⚠️ IMPORTANTE: Debe haber al menos un destino creado previamente
   - **Nombre:** Ingrese el nombre de la actividad (Ejemplo: "Surf para principiantes")
   - **Precio:** Ingrese el precio (Ejemplo: 25.50)
   - **Duración:** Ingrese la duración en horas (Ejemplo: 2)
   - **Estado:** Seleccione 'A' (Activo) o 'I' (Inactivo)
3. Haga clic en **"Guardar"**
4. Si todo es correcto, verá el mensaje: "Actividad guardada correctamente"

**Validaciones:**
- ❌ Todos los campos son obligatorios
- ❌ El precio debe ser mayor a 0
- ❌ La duración debe ser mayor a 0
- ❌ Debe seleccionar un destino válido

**Ejemplos de mensajes de error:**
- "Debe seleccionar un destino válido"
- "El precio debe ser mayor a 0"
- "La duración debe ser mayor a 0"

---

### 3. Editar Actividad

**Objetivo:** Modificar los datos de una actividad existente.

**Pasos:**
1. En la lista de actividades, localice la actividad a editar
2. Haga clic en **"Editar"** (botón amarillo)
3. Se abrirá el formulario con los datos actuales
4. Modifique los campos necesarios:
   - Puede cambiar el destino asociado
   - Actualizar precio
   - Modificar duración
   - Cambiar estado
5. Haga clic en **"Actualizar"**
6. Verá el mensaje: "Actividad actualizada correctamente"

**Caso de uso común:**
- Actualizar precios de temporada
- Cambiar duración de actividades
- Reasignar actividad a otro destino

---

### 4. Inactivar Actividad

**Objetivo:** Marcar una actividad como inactiva.

**Pasos:**
1. En la lista de actividades, localice la actividad a inactivar
2. Haga clic en **"Inactivar"** (botón rojo)
3. El estado cambiará automáticamente a 'I'
4. Verá el mensaje: "Actividad inactivada correctamente"
5. El badge cambiará de verde a rojo

**¿Cuándo usar esta función?**
- Actividades de temporada que ya finalizaron
- Actividades suspendidas temporalmente
- Actividades que ya no se ofrecen pero desea mantener el registro

---

## Navegación General

### Menú Principal

El sistema cuenta con un menú de navegación superior con los siguientes enlaces:

- **Inicio:** Regresa a la página principal
- **Destinos:** Accede a la gestión de destinos
- **Actividades:** Accede a la gestión de actividades

### Botones Comunes

- 🟢 **Botón Verde:** Crear nuevo registro
- 🟡 **Botón Amarillo:** Editar registro existente
- 🔵 **Botón Azul:** Ver detalles/información
- 🔴 **Botón Rojo:** Inactivar registro
- ⚪ **Botón Gris:** Cancelar operación

---

## Mensajes del Sistema

### Mensajes de Éxito (Verde)
- "Destino guardado correctamente"
- "Destino actualizado correctamente"
- "Destino inactivado correctamente"
- "Actividad guardada correctamente"
- "Actividad actualizada correctamente"
- "Actividad inactivada correctamente"

### Mensajes de Error (Rojo)
Aparecen cuando:
- Campos obligatorios están vacíos
- Valores numéricos son incorrectos (≤ 0)
- Formato de estado es inválido
- No se seleccionó un destino para la actividad

---

## Preguntas Frecuentes

### ¿Puedo eliminar definitivamente un destino o actividad?
No. El sistema implementa "soft delete", lo que significa que solo se cambia el estado a 'I' (Inactivo). Esto permite mantener un historial completo.

### ¿Qué pasa si intento crear una actividad sin destinos?
El sistema no le permitirá guardar la actividad. Primero debe crear al menos un destino.

### ¿Puedo tener dos destinos con el mismo nombre?
Sí, el sistema no valida nombres duplicados en destinos.

### ¿Los precios pueden tener decimales?
Sí, puede ingresar precios con hasta 2 decimales (Ejemplo: 25.50, 100.99).

### ¿Cómo reactivo un destino inactivo?
Edite el destino y cambie manualmente el estado de 'I' a 'A'.

### ¿Se pierden las actividades si inactivo un destino?
No, las actividades asociadas permanecen en el sistema. Solo el destino cambia de estado.

### ¿Puedo cambiar una actividad de un destino a otro?
Sí, edite la actividad y seleccione un nuevo destino del menú desplegable.

---

## Consejos de Uso

1. **Siempre guarde datos reales:** Evite usar datos de prueba en producción
2. **Verifique antes de inactivar:** Revise el detalle del destino para ver sus actividades
3. **Use descripciones claras:** Ayuda a otros usuarios a entender el destino
4. **Mantenga precios actualizados:** Edite regularmente las actividades según temporada
5. **Use la búsqueda:** Es más rápido buscar por nombre que desplazarse por la lista completa

---

## Soporte Técnico

Si encuentra algún problema o tiene preguntas adicionales:
- Revise este manual
- Contacte al administrador del sistema
- Reporte errores con capturas de pantalla

---

**Última actualización:** Noviembre 2025
