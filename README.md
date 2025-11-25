# 🌍 Sistema de Gestión de Turismo Vuela Alto

Sistema web de gestión turística desarrollado con **Spring Boot 4.0** que permite administrar destinos turísticos y sus actividades asociadas mediante operaciones CRUD completas.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.3-green)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.0-purple)

---

## 📋 Descripción

Turismo Vuela Alto es una aplicación web que implementa un sistema completo de gestión para agencias de turismo, permitiendo:

- ✅ Gestión de destinos turísticos (crear, editar, listar, inactivar)
- ✅ Gestión de actividades por destino (con precio y duración)
- ✅ Búsqueda y filtrado de información
- ✅ Validaciones de datos robustas
- ✅ Interfaz responsive con Bootstrap 5
- ✅ Arquitectura MVC con Spring Boot

---

## 🚀 Tecnologías Utilizadas

### Backend
- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Hibernate 7.1.8** - ORM
- **Bean Validation** - Validaciones
- **MySQL Connector/J** - Driver de base de datos

### Frontend
- **Thymeleaf 3.1.3** - Motor de plantillas
- **Bootstrap 5.3.0** - Framework CSS
- **HTML5 / CSS3** - Estructura y estilos

### Herramientas
- **Maven** - Gestión de dependencias
- **MySQL 8** - Base de datos
- **Java 17** - Versión de Java

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **JDK 17 o superior**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **MySQL 8.0+**
   - Usuario: `root`
   - Contraseña: (configurar en `application.properties`)

4. **Git** (para clonar el repositorio)

---

## 🔧 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/TurismoVuelaAlto.git
cd TurismoVuelaAlto
```

### 2. Configurar la base de datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE turismo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar credenciales

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/turismo_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_MYSQL
```

### 4. Compilar el proyecto

```bash
mvn clean install
```

### 5. Ejecutar la aplicación

**Opción A - Con Maven:**
```bash
mvn spring-boot:run
```

**Opción B - Con Java:**
```bash
java -jar target/TurismoVuelaAlto-0.0.1-SNAPSHOT.jar
```

### 6. Acceder a la aplicación

Abrir navegador en: **http://localhost:8080**

---

## 📊 Estructura del Proyecto

```
TurismoVuelaAlto/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/TurismoVuelaAlto/
│   │   │       ├── controller/          # Controladores MVC
│   │   │       │   ├── HomeController.java
│   │   │       │   ├── DestinoController.java
│   │   │       │   └── ActividadController.java
│   │   │       ├── entity/              # Entidades JPA
│   │   │       │   ├── DestinoEntity.java
│   │   │       │   └── ActividadEntity.java
│   │   │       ├── repository/          # Repositorios
│   │   │       │   ├── DestinoRepository.java
│   │   │       │   └── ActividadRepository.java
│   │   │       ├── service/             # Servicios
│   │   │       │   ├── DestinoService.java
│   │   │       │   ├── ActividadService.java
│   │   │       │   └── impl/
│   │   │       │       ├── DestinoServiceImpl.java
│   │   │       │       └── ActividadServiceImpl.java
│   │   │       └── TurismoVuelaAltoApplication.java
│   │   └── resources/
│   │       ├── application.properties   # Configuración
│   │       ├── templates/               # Vistas Thymeleaf
│   │       │   ├── index.html
│   │       │   ├── destinos/
│   │       │   │   ├── lista.html
│   │       │   │   ├── form.html
│   │       │   │   └── detalle.html
│   │       │   └── actividades/
│   │       │       ├── lista.html
│   │       │       └── form.html
│   │       └── static/                  # Recursos estáticos
│   └── test/                            # Tests
├── pom.xml                              # Dependencias Maven
└── README.md
```

---

## 🗃️ Modelo de Datos

### Tabla: `destino`
| Campo       | Tipo         | Descripción                    |
|-------------|--------------|--------------------------------|
| id_destino  | BIGINT (PK)  | ID único auto-incremental      |
| nombre      | VARCHAR(100) | Nombre del destino (requerido) |
| descripcion | VARCHAR(500) | Descripción detallada          |
| estado      | CHAR(1)      | 'A' (Activo) o 'I' (Inactivo) |

### Tabla: `actividad`
| Campo        | Tipo          | Descripción                      |
|--------------|---------------|----------------------------------|
| id_actividad | BIGINT (PK)   | ID único auto-incremental        |
| nombre       | VARCHAR(100)  | Nombre de la actividad (requerido) |
| precio       | DECIMAL(10,2) | Precio de la actividad (> 0)    |
| duracion     | INT           | Duración en horas (> 0)         |
| estado       | CHAR(1)       | 'A' (Activo) o 'I' (Inactivo)  |
| id_destino   | BIGINT (FK)   | Referencia al destino           |

**Relación:** Un destino puede tener muchas actividades (1:N)

---

## 🎯 Funcionalidades Principales

### Módulo de Destinos
- **Listar destinos** - Visualizar todos los destinos en tabla
- **Crear destino** - Agregar nuevo destino con validaciones
- **Editar destino** - Modificar información existente
- **Ver detalle** - Mostrar destino con todas sus actividades
- **Inactivar destino** - Soft delete (cambio de estado)
- **Buscar destinos** - Búsqueda por nombre

### Módulo de Actividades
- **Listar actividades** - Tabla con todas las actividades
- **Crear actividad** - Agregar actividad asociada a un destino
- **Editar actividad** - Modificar precio, duración, etc.
- **Inactivar actividad** - Soft delete

### Validaciones Implementadas
- ✅ Campos obligatorios (nombre)
- ✅ Valores numéricos positivos (precio > 0, duración > 0)
- ✅ Formato de estado (solo 'A' o 'I')
- ✅ Relación obligatoria (actividad debe tener destino)

---

## 🖥️ Capturas de Pantalla

### Página de Inicio
![Home](docs/screenshots/home.png)

### Lista de Destinos
![Lista Destinos](docs/screenshots/destinos-lista.png)

### Formulario de Actividad
![Form Actividad](docs/screenshots/actividades-form.png)

### Detalle de Destino con Actividades
![Detalle](docs/screenshots/destino-detalle.png)

---

## 🧪 Ejecutar Tests

```bash
mvn test
```

---

## 📝 Endpoints Principales

### Destinos
- `GET /destinos` - Lista de destinos
- `GET /destinos/nuevo` - Formulario nuevo destino
- `POST /destinos/guardar` - Guardar destino
- `GET /destinos/editar/{id}` - Formulario edición
- `POST /destinos/actualizar` - Actualizar destino
- `GET /destinos/inactivar/{id}` - Inactivar destino
- `GET /destinos/detalle/{id}` - Ver detalle
- `GET /destinos/buscar?nombre=X` - Buscar por nombre

### Actividades
- `GET /actividades` - Lista de actividades
- `GET /actividades/nuevo` - Formulario nueva actividad
- `POST /actividades/guardar` - Guardar actividad
- `GET /actividades/editar/{id}` - Formulario edición
- `POST /actividades/actualizar` - Actualizar actividad
- `GET /actividades/inactivar/{id}` - Inactivar actividad

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el proyecto
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es de uso académico para la asignatura de Servicios de desarrollo web

---

## ✍️ Autor

**Piero Leon**
- GitHub: PieroH-del (https://github.com/PieroH-del)


