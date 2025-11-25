# GUÍA DE INSTALACIÓN
## Sistema de Gestión de Turismo Vuela Alto

---

## Índice
1. [Requisitos del Sistema](#requisitos-del-sistema)
2. [Instalación de Dependencias](#instalación-de-dependencias)
3. [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
4. [Configuración del Proyecto](#configuración-del-proyecto)
5. [Ejecución de la Aplicación](#ejecución-de-la-aplicación)
6. [Solución de Problemas](#solución-de-problemas)

---

## Requisitos del Sistema

### Software Necesario

| Software | Versión Mínima | Versión Recomendada | Propósito |
|----------|----------------|---------------------|-----------|
| JDK (Java Development Kit) | 17 | 17 o superior | Ejecutar aplicación Spring Boot |
| Maven | 3.6.0 | 3.8.0 o superior | Gestión de dependencias |
| MySQL Server | 8.0 | 8.0 o superior | Base de datos |
| IDE (Opcional) | - | IntelliJ IDEA / Eclipse | Desarrollo y ejecución |
| Navegador Web | - | Chrome, Firefox, Edge | Acceso a la aplicación |

### Especificaciones de Hardware

- **RAM:** Mínimo 4GB (Recomendado 8GB)
- **Espacio en Disco:** 500MB libres
- **Procesador:** Cualquier procesador moderno de 64 bits

---

## Instalación de Dependencias

### 1. Instalar Java Development Kit (JDK)

#### Windows:
1. Descargue JDK 17 desde [Oracle](https://www.oracle.com/java/technologies/downloads/) o [OpenJDK](https://adoptium.net/)
2. Ejecute el instalador `.exe`
3. Siga el asistente de instalación
4. Configure la variable de entorno `JAVA_HOME`:
   - Abra **Panel de Control** → **Sistema** → **Configuración avanzada del sistema**
   - Clic en **Variables de entorno**
   - En "Variables del sistema", clic en **Nueva**
   - Nombre: `JAVA_HOME`
   - Valor: `C:\Program Files\Java\jdk-17` (ajuste según su ruta)
   - Agregue `%JAVA_HOME%\bin` al `PATH`

5. Verifique la instalación:
   ```powershell
   java -version
   ```
   Debería ver algo como: `java version "17.0.x"`

#### macOS:
```bash
brew install openjdk@17
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

---

### 2. Instalar Maven

#### Windows:
1. Descargue Maven desde [maven.apache.org](https://maven.apache.org/download.cgi)
2. Extraiga el archivo ZIP a `C:\Program Files\Apache\maven`
3. Configure variables de entorno:
   - Variable `MAVEN_HOME`: `C:\Program Files\Apache\maven`
   - Agregue `%MAVEN_HOME%\bin` al `PATH`

4. Verifique:
   ```powershell
   mvn -version
   ```

#### macOS:
```bash
brew install maven
```

#### Linux:
```bash
sudo apt install maven
```

---

### 3. Instalar MySQL Server

#### Windows:
1. Descargue MySQL Installer desde [mysql.com](https://dev.mysql.com/downloads/installer/)
2. Ejecute el instalador
3. Seleccione "Developer Default"
4. Configure:
   - Usuario root: `root`
   - Contraseña: (anote su contraseña)
   - Puerto: `3306` (predeterminado)
5. Complete la instalación

#### macOS:
```bash
brew install mysql
brew services start mysql
mysql_secure_installation
```

#### Linux:
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql_secure_installation
```

---

## Configuración de la Base de Datos

### Paso 1: Crear la Base de Datos

1. Abra **MySQL Workbench** o conéctese por terminal:
   ```bash
   mysql -u root -p
   ```

2. Ejecute el siguiente script SQL:

```sql
-- Crear base de datos
CREATE DATABASE turismo_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE turismo_db;

-- Crear tabla destino
CREATE TABLE destino (
    id_destino BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    estado CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT chk_estado_destino CHECK (estado IN ('A', 'I'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla actividad
CREATE TABLE actividad (
    id_actividad BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    duracion INT NOT NULL,
    estado CHAR(1) NOT NULL DEFAULT 'A',
    id_destino BIGINT NOT NULL,
    CONSTRAINT chk_estado_actividad CHECK (estado IN ('A', 'I')),
    CONSTRAINT chk_precio_positivo CHECK (precio > 0),
    CONSTRAINT chk_duracion_positiva CHECK (duracion > 0),
    CONSTRAINT fk_actividad_destino 
        FOREIGN KEY (id_destino) 
        REFERENCES destino(id_destino) 
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices
CREATE INDEX idx_destino_estado ON destino(estado);
CREATE INDEX idx_destino_nombre ON destino(nombre);
CREATE INDEX idx_actividad_destino ON actividad(id_destino);
CREATE INDEX idx_actividad_estado ON actividad(estado);
```

### Paso 2: Cargar Datos de Ejemplo (Opcional)

```sql
-- Insertar destinos de ejemplo
INSERT INTO destino (nombre, descripcion, estado) VALUES
('Playa El Paraíso', 'Hermosa playa con arenas blancas y aguas cristalinas perfecta para familias', 'A'),
('Montaña Azul', 'Impresionante montaña ideal para senderismo y aventura', 'A'),
('Valle del Sol', 'Tranquilo valle con hermosos paisajes y clima cálido todo el año', 'A'),
('Bosque Encantado', 'Reserva natural con biodiversidad única y senderos ecológicos', 'A'),
('Ciudad Colonial', 'Centro histórico con arquitectura colonial y museos', 'A');

-- Insertar actividades de ejemplo
INSERT INTO actividad (nombre, precio, duracion, estado, id_destino) VALUES
('Buceo en arrecife', 45.00, 3, 'A', 1),
('Clases de surf', 35.00, 2, 'A', 1),
('Paseo en kayak', 25.00, 2, 'A', 1),
('Escalada básica', 50.00, 4, 'A', 2),
('Senderismo guiado', 30.00, 5, 'A', 2),
('Camping nocturno', 60.00, 12, 'A', 2),
('Tour en bicicleta', 20.00, 3, 'A', 3),
('Degustación gastronómica', 40.00, 2, 'A', 3),
('Observación de aves', 25.00, 3, 'A', 4),
('Fotografía de naturaleza', 35.00, 4, 'A', 4),
('Tour guiado histórico', 15.00, 2, 'A', 5),
('Visita a museos', 12.00, 3, 'A', 5),
('Taller de artesanía', 20.00, 2, 'A', 5);

-- Verificar datos
SELECT * FROM destino;
SELECT * FROM actividad;
```

### Paso 3: Crear Usuario de Base de Datos (Opcional, más seguro)

```sql
-- Crear usuario específico para la aplicación
CREATE USER 'turismo_user'@'localhost' IDENTIFIED BY 'TurismoPass2024!';

-- Otorgar permisos
GRANT ALL PRIVILEGES ON turismo_db.* TO 'turismo_user'@'localhost';

-- Aplicar cambios
FLUSH PRIVILEGES;
```

---

## Configuración del Proyecto

### Paso 1: Obtener el Código Fuente

**Opción A - Clonar desde GitHub:**
```bash
git clone https://github.com/TU_USUARIO/TurismoVuelaAlto.git
cd TurismoVuelaAlto
```

**Opción B - Descargar ZIP:**
1. Descargue el archivo ZIP del proyecto
2. Extraiga a una carpeta (Ejemplo: `C:\Proyectos\TurismoVuelaAlto`)
3. Abra terminal en esa ubicación

---

### Paso 2: Configurar `application.properties`

Navegue a `src/main/resources/application.properties` y configure:

```properties
# ==============================================
# CONFIGURACIÓN DE BASE DE DATOS
# ==============================================
spring.datasource.url=jdbc:mysql://localhost:3306/turismo_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SU_PASSWORD_MYSQL_AQUI
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ==============================================
# CONFIGURACIÓN DE JPA/HIBERNATE
# ==============================================
# update: actualiza schema automáticamente
# validate: solo valida que coincida
# create: recrea tablas cada vez (¡CUIDADO! borra datos)
spring.jpa.hibernate.ddl-auto=update

# Mostrar SQL en consola (útil para debugging)
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Dialecto de MySQL para Hibernate 7
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# ==============================================
# CONFIGURACIÓN DE THYMELEAF
# ==============================================
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8

# ==============================================
# CONFIGURACIÓN DEL SERVIDOR
# ==============================================
server.port=8080
server.servlet.context-path=/

# ==============================================
# CONFIGURACIÓN DE LOGGING
# ==============================================
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.com.example.TurismoVuelaAlto=DEBUG
```

**Importante:** Cambie `SU_PASSWORD_MYSQL_AQUI` por su contraseña real de MySQL.

---

### Paso 3: Verificar Dependencias Maven

El archivo `pom.xml` ya incluye todas las dependencias necesarias:

```xml
<!-- Spring Boot Starter Parent -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.0</version>
</parent>

<!-- Dependencias principales -->
<dependencies>
    <!-- Spring Web MVC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
    <!-- Bean Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Spring Boot DevTools (opcional, para desarrollo) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    
    <!-- Tests -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

### Paso 4: Compilar el Proyecto

Abra terminal en la raíz del proyecto y ejecute:

```bash
mvn clean install
```

**¿Qué hace este comando?**
- `clean`: Limpia compilaciones anteriores
- `install`: Descarga dependencias, compila código, ejecuta tests, genera JAR

**Salida esperada:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

**Si hay errores:**
- Verifique la conexión a Internet (para descargar dependencias)
- Verifique que JAVA_HOME apunte a JDK 17
- Revise que el archivo `pom.xml` no esté corrupto

---

## Ejecución de la Aplicación

### Método 1: Ejecutar con Maven

```bash
mvn spring-boot:run
```

**Salida esperada:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v4.0.0)

... TurismoVuelaAltoApplication : Started TurismoVuelaAltoApplication in 3.456 seconds
```

---

### Método 2: Ejecutar con IntelliJ IDEA

1. Abra el proyecto en IntelliJ IDEA
2. Espere a que Maven descargue dependencias
3. Localice `TurismoVuelaAltoApplication.java`
4. Haga clic derecho → **Run 'TurismoVuelaAltoApplication'**
5. Vea la consola para confirmar que inició

---

### Método 3: Ejecutar JAR directamente

```bash
# Primero compilar
mvn clean package -DskipTests

# Luego ejecutar
java -jar target/TurismoVuelaAlto-0.0.1-SNAPSHOT.jar
```

---

### Acceder a la Aplicación

1. Abra su navegador web
2. Navegue a: **http://localhost:8080**
3. Debería ver la página de inicio del sistema

---

## Solución de Problemas

### Error: "Cannot find symbol - method setXXX"
**Causa:** Problema con Lombok o getters/setters faltantes  
**Solución:** Las entidades ya tienen getters/setters manuales, no debería ocurrir

### Error: "Access denied for user 'root'@'localhost'"
**Causa:** Contraseña de MySQL incorrecta  
**Solución:**
1. Verifique su contraseña de MySQL
2. Actualice `application.properties`
3. Reinicie la aplicación

### Error: "Unknown database 'turismo_db'"
**Causa:** Base de datos no creada  
**Solución:**
```sql
CREATE DATABASE turismo_db;
```

### Error: "Port 8080 already in use"
**Causa:** Otro servicio usa el puerto 8080  
**Solución:**
1. **Opción A:** Cambie el puerto en `application.properties`:
   ```properties
   server.port=8081
   ```
2. **Opción B:** Detenga el servicio que usa el puerto 8080

### Error: "Driver class 'com.mysql.cj.jdbc.Driver' not found"
**Causa:** Dependencia MySQL no descargada  
**Solución:**
```bash
mvn clean install -U
```

### La aplicación inicia pero no muestra datos
**Causa:** Base de datos vacía  
**Solución:** Ejecute el script SQL de datos de ejemplo

### Error: "Table 'turismo_db.destino' doesn't exist"
**Causa:** Tablas no creadas  
**Solución:**
1. Verifique `spring.jpa.hibernate.ddl-auto=update` en `application.properties`
2. O ejecute manualmente el script SQL de creación de tablas

---

## Verificación de Instalación Exitosa

✅ **Checklist de Verificación:**

- [ ] JDK 17 instalado y configurado
- [ ] Maven instalado y funcionando
- [ ] MySQL Server corriendo
- [ ] Base de datos `turismo_db` creada
- [ ] Tablas `destino` y `actividad` creadas
- [ ] Proyecto compilado sin errores (`mvn clean install`)
- [ ] Aplicación iniciada correctamente
- [ ] Página de inicio accesible en http://localhost:8080
- [ ] Puede navegar a /destinos y /actividades
- [ ] Puede crear un destino de prueba
- [ ] Puede crear una actividad asociada

---

## Comandos Útiles

```bash
# Limpiar y compilar
mvn clean compile

# Compilar y ejecutar tests
mvn clean test

# Compilar y generar JAR
mvn clean package

# Ejecutar sin tests
mvn spring-boot:run -DskipTests

# Ver dependencias
mvn dependency:tree

# Actualizar dependencias
mvn clean install -U
```

---

## Contacto y Soporte

Si encuentra problemas durante la instalación:
1. Revise esta guía paso a paso
2. Verifique los logs en la consola
3. Consulte la documentación de Spring Boot
4. Contacte al desarrollador

---

**Última actualización:** Noviembre 2025
