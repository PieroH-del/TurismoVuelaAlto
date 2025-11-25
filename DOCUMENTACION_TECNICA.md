# SISTEMA DE GESTIÓN DE TURISMO VUELA ALTO

---

## PORTADA

**Nombre del Proyecto:** Sistema de Gestión de Turismo Vuela Alto  
**Curso:** Programación Web Avanzada  
**Estudiante:** [TU NOMBRE COMPLETO]  
**Fecha:** Noviembre 2025  
**Institución:** [TU INSTITUCIÓN]

---

## 1. INTRODUCCIÓN

El Sistema de Gestión de Turismo Vuela Alto es una aplicación web desarrollada para administrar destinos turísticos y sus actividades asociadas. La aplicación permite realizar operaciones CRUD (Crear, Leer, Actualizar, Desactivar) sobre destinos y actividades turísticas, proporcionando una interfaz amigable para la gestión de información turística.

El sistema implementa la arquitectura MVC (Modelo-Vista-Controlador) utilizando Spring Boot como framework principal, JPA/Hibernate para la persistencia de datos, y Thymeleaf para la capa de presentación.

---

## 2. OBJETIVOS

### 2.1 Objetivo General
Desarrollar un sistema web de gestión turística que permita administrar destinos y actividades de manera eficiente, implementando las mejores prácticas de desarrollo con Spring Boot y arquitectura MVC.

### 2.2 Objetivos Específicos
1. Implementar un sistema CRUD completo para la gestión de destinos turísticos con sus respectivas actividades.
2. Aplicar validaciones de datos tanto en el lado del servidor como en el cliente para garantizar la integridad de la información.
3. Desarrollar una interfaz de usuario intuitiva y responsive utilizando Bootstrap 5 y Thymeleaf.

---

## 3. TECNOLOGÍAS UTILIZADAS

### 3.1 Backend
- **Spring Boot 4.0.0** - Framework principal para desarrollo de aplicaciones Java
- **Spring Web** - Para desarrollo de aplicaciones web MVC
- **Spring Data JPA** - Capa de persistencia con JPA/Hibernate
- **Hibernate 7.1.8** - ORM (Object-Relational Mapping)
- **Bean Validation (Jakarta Validation 3.1.1)** - Validación de datos

### 3.2 Frontend
- **Thymeleaf 3.1.3** - Motor de plantillas para vistas HTML
- **Bootstrap 5.3.0** - Framework CSS para diseño responsive
- **HTML5** - Estructura de las páginas web
- **CSS3** - Estilos personalizados

### 3.3 Base de Datos
- **MySQL 8** - Sistema de gestión de base de datos relacional
- **MySQL Connector/J** - Driver JDBC para conexión con MySQL

### 3.4 Herramientas de Desarrollo
- **Maven** - Gestión de dependencias y construcción del proyecto
- **Java 17** - Versión de Java utilizada
- **IntelliJ IDEA** - IDE de desarrollo
- **Git** - Control de versiones
- **GitHub** - Repositorio remoto

---

## 4. MODELO DE DATOS

### 4.1 Diagrama Entidad-Relación

```
┌─────────────────────────┐         ┌─────────────────────────┐
│      DESTINO            │         │      ACTIVIDAD          │
├─────────────────────────┤         ├─────────────────────────┤
│ id_destino (PK)         │         │ id_actividad (PK)       │
│ nombre                  │         │ nombre                  │
│ descripcion             │         │ precio                  │
│ estado                  │         │ duracion                │
└─────────────────────────┘         │ estado                  │
             │                       │ id_destino (FK)         │
             │                       └─────────────────────────┘
             │                                    │
             └────────────────1:N─────────────────┘
```

### 4.2 Explicación de Tablas

#### Tabla: `destino`
| Campo        | Tipo         | Descripción                                    |
|--------------|--------------|------------------------------------------------|
| id_destino   | BIGINT (PK)  | Identificador único del destino (auto-increment) |
| nombre       | VARCHAR(100) | Nombre del destino turístico (obligatorio)     |
| descripcion  | VARCHAR(500) | Descripción detallada del destino              |
| estado       | CHAR(1)      | Estado: 'A' (Activo) o 'I' (Inactivo)         |

#### Tabla: `actividad`
| Campo          | Tipo          | Descripción                                      |
|----------------|---------------|--------------------------------------------------|
| id_actividad   | BIGINT (PK)   | Identificador único de la actividad (auto-increment) |
| nombre         | VARCHAR(100)  | Nombre de la actividad (obligatorio)             |
| precio         | DECIMAL(10,2) | Precio de la actividad (mayor a 0)              |
| duracion       | INT           | Duración en horas (mayor a 0)                   |
| estado         | CHAR(1)       | Estado: 'A' (Activo) o 'I' (Inactivo)          |
| id_destino     | BIGINT (FK)   | Referencia al destino (obligatorio)             |

**Relación:** Un destino puede tener muchas actividades (1:N). Cada actividad pertenece a un único destino.

---

## 5. ESPECIFICACIÓN TÉCNICA

### 5.1 Estructura de Paquetes

```
com.example.TurismoVuelaAlto
├── controller/          # Controladores MVC
│   ├── HomeController.java
│   ├── DestinoController.java
│   └── ActividadController.java
├── entity/              # Entidades JPA
│   ├── DestinoEntity.java
│   └── ActividadEntity.java
├── repository/          # Repositorios de datos
│   ├── DestinoRepository.java
│   └── ActividadRepository.java
├── service/             # Capa de servicios
│   ├── DestinoService.java
│   ├── ActividadService.java
│   └── impl/
│       ├── DestinoServiceImpl.java
│       └── ActividadServiceImpl.java
└── TurismoVuelaAltoApplication.java  # Clase principal
```

### 5.2 Entidades JPA

#### DestinoEntity
- **Anotaciones JPA:** `@Entity`, `@Table(name="destino")`
- **Clave primaria:** `@Id`, `@GeneratedValue(strategy = IDENTITY)`
- **Relaciones:** `@OneToMany` con ActividadEntity (bidireccional)
- **Validaciones:**
  - `@NotBlank` en nombreDestino
  - `@Pattern(regexp="[AI]")` en estadoDestino

#### ActividadEntity
- **Anotaciones JPA:** `@Entity`, `@Table(name="actividad")`
- **Clave primaria:** `@Id`, `@GeneratedValue(strategy = IDENTITY)`
- **Relaciones:** `@ManyToOne` con DestinoEntity (fetch LAZY)
- **Validaciones:**
  - `@NotBlank` en nombreActividad
  - `@NotNull` y `@Min(1)` en precioActividad
  - `@NotNull` y `@Min(1)` en duracionActividad
  - `@Pattern(regexp="[AI]")` en estadoActividad
  - `@NotNull` en destino (FK)

### 5.3 Repositorios

#### DestinoRepository extends JpaRepository<DestinoEntity, Long>
**Métodos personalizados:**
- `List<DestinoEntity> findByEstadoDestino(String estado)` - Busca destinos por estado
- `List<DestinoEntity> findByNombreDestinoContainingIgnoreCase(String nombre)` - Búsqueda por nombre
- `Optional<DestinoEntity> findByIdWithActividades(Long id)` - Busca con actividades (JOIN FETCH)
- `boolean existsByNombreDestino(String nombre)` - Verifica existencia

#### ActividadRepository extends JpaRepository<ActividadEntity, Long>
**Métodos personalizados:**
- `List<ActividadEntity> findByDestino_IdDestino(Long idDestino)` - Actividades por destino
- `List<ActividadEntity> findByDestino_IdDestinoAndEstadoActividad(Long id, String estado)` - Filtro combinado
- `List<ActividadEntity> findByEstadoActividad(String estado)` - Filtro por estado

### 5.4 Servicios

#### DestinoService / DestinoServiceImpl
**Métodos implementados:**
- `guardar(DestinoEntity destino)` - Guarda nuevo destino con estado "A"
- `listarTodos()` - Lista todos los destinos
- `listarActivos()` - Lista solo destinos activos
- `buscarPorId(Long id)` - Busca por ID
- `actualizar(DestinoEntity destino)` - Actualiza destino existente
- `inactivar(Long id)` - Cambia estado a "I" (soft delete)
- `buscarPorNombre(String nombre)` - Búsqueda por nombre
- `obtenerConActividades(Long id)` - Obtiene destino con sus actividades

#### ActividadService / ActividadServiceImpl
**Métodos implementados:**
- `guardar(ActividadEntity actividad)` - Guarda nueva actividad con estado "A"
- `listarTodas()` - Lista todas las actividades
- `listarActivas()` - Lista solo actividades activas
- `listarPorDestino(Long idDestino)` - Actividades de un destino
- `listarActivasPorDestino(Long idDestino)` - Actividades activas de un destino
- `buscarPorId(Long id)` - Busca por ID
- `actualizar(ActividadEntity actividad)` - Actualiza actividad
- `inactivar(Long id)` - Cambia estado a "I"

### 5.5 Controladores

#### HomeController
- **Ruta base:** `/`
- **Endpoint:** `GET /` → `index.html` (página de inicio)

#### DestinoController
- **Ruta base:** `/destinos`
- **Endpoints:**
  - `GET /` → Lista todos los destinos
  - `GET /nuevo` → Formulario de nuevo destino
  - `POST /guardar` → Guarda nuevo destino (con validación `@Valid`)
  - `GET /editar/{id}` → Formulario de edición
  - `POST /actualizar` → Actualiza destino (con validación)
  - `GET /inactivar/{id}` → Inactiva destino (soft delete)
  - `GET /detalle/{id}` → Muestra destino con actividades
  - `GET /buscar` → Búsqueda por nombre

#### ActividadController
- **Ruta base:** `/actividades`
- **Endpoints:**
  - `GET /` → Lista todas las actividades
  - `GET /nuevo` → Formulario de nueva actividad
  - `POST /guardar` → Guarda nueva actividad (con validación)
  - `GET /editar/{id}` → Formulario de edición
  - `POST /actualizar` → Actualiza actividad
  - `GET /inactivar/{id}` → Inactiva actividad

### 5.6 Validaciones Implementadas

#### Validaciones de Servidor (Bean Validation)
- **Campos obligatorios:** `@NotBlank` en nombres
- **Valores numéricos:** `@NotNull`, `@Min(1)` en precio y duración
- **Patrones:** `@Pattern(regexp="[AI]")` para estados
- **Relaciones:** `@NotNull` en foreign keys

#### Validaciones de Cliente (HTML5)
- `required` en campos obligatorios
- `min="1"` en campos numéricos
- `pattern="[AI]"` en campos de estado
- Mensajes de error personalizados en español

#### Manejo de Errores
- `BindingResult` para capturar errores de validación
- `RedirectAttributes` para mensajes flash
- Mensajes de éxito/error en las vistas

---

## 6. INTERFACES DE USUARIO

### 6.1 Vistas Implementadas

#### 1. index.html (Página de Inicio)
- **Descripción:** Página principal con enlaces a módulos
- **Características:**
  - Diseño con tarjetas Bootstrap
  - Enlaces a gestión de destinos y actividades
  - Diseño responsive

#### 2. destinos/lista.html (Lista de Destinos)
- **Descripción:** Tabla con todos los destinos
- **Características:**
  - Tabla responsive con Bootstrap
  - Búsqueda por nombre
  - Botones: Nuevo, Editar, Ver Detalle, Inactivar
  - Indicador visual de estado (badge)
  - Mensajes de éxito/error

#### 3. destinos/form.html (Formulario de Destino)
- **Descripción:** Formulario para crear/editar destino
- **Características:**
  - Campos: nombre, descripción, estado
  - Validación en tiempo real
  - Mensajes de error debajo de cada campo
  - Botones: Guardar, Cancelar

#### 4. destinos/detalle.html (Detalle de Destino)
- **Descripción:** Información completa del destino con actividades
- **Características:**
  - Datos del destino en tarjeta
  - Tabla de actividades asociadas
  - Enlaces para editar
  - Botón volver

#### 5. actividades/lista.html (Lista de Actividades)
- **Descripción:** Tabla con todas las actividades
- **Características:**
  - Muestra destino asociado
  - Precio formateado
  - Duración en horas
  - Estado con badge
  - Acciones: Editar, Inactivar

#### 6. actividades/form.html (Formulario de Actividad)
- **Descripción:** Formulario para crear/editar actividad
- **Características:**
  - Selector de destino (dropdown)
  - Campos: nombre, precio, duración, estado
  - Validaciones numéricas
  - Mensajes de error personalizados

### 6.2 Características de UI/UX
- **Framework CSS:** Bootstrap 5.3.0
- **Diseño:** Responsive (mobile-first)
- **Colores:** Esquema Bootstrap (primary, success, danger, warning)
- **Navegación:** Navbar con enlaces a módulos
- **Feedback:** Alertas para operaciones exitosas/fallidas
- **Iconos:** Uso de clases Bootstrap para botones

---

## 7. CONFIGURACIÓN

### 7.1 application.properties

```properties
# Configuración de la base de datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/turismo_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuración de Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Puerto del servidor
server.port=8080

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## 8. CONCLUSIONES

El Sistema de Gestión de Turismo Vuela Alto cumple con todos los requisitos funcionales establecidos (RF10.1 a RF10.10) y no funcionales (RNF10.V1 a RNF10.V4), implementando:

- ✅ CRUD completo para Destinos y Actividades
- ✅ Validaciones de datos en servidor y cliente
- ✅ Arquitectura MVC con Spring Boot
- ✅ Interfaz responsive con Bootstrap 5
- ✅ Persistencia con JPA/Hibernate
- ✅ Soft delete (inactivación en lugar de eliminación física)
- ✅ Relaciones bidireccionales entre entidades
- ✅ Manejo de errores y mensajes al usuario

El sistema es escalable, mantenible y sigue las mejores prácticas de desarrollo web con Spring Boot.
