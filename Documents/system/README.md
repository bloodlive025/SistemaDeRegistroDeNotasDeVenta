# Sistema de Registro de Notas de Venta

Este proyecto es una API RESTful desarrollada con Spring Boot para la gestión de ventas, productos y usuarios. Permite registrar notas de venta con múltiples productos, gestionar el inventario de productos y autenticar usuarios mediante JWT.

## 🚀 Tecnologías Utilizadas

*   **Java 17**
*   **Spring Boot 3.4.2**
    *   Spring Web
    *   Spring Data JPA
    *   Spring Security
*   **MySQL** (Base de datos)
*   **Flyway** (Migraciones de base de datos)
*   **JWT** (JSON Web Token para seguridad)
*   **Maven** (Gestor de dependencias)
*   **Swagger / OpenAPI** (Documentación de API)

## ⚙️ Configuración y Ejecución

### Prerrequisitos

1.  Tener instalado **Java 17**.
2.  Tener instalado **MySQL**.
3.  Tener instalado **Maven** (opcional, ya que se incluye el wrapper `mvnw`).

### Configuración de la Base de Datos

1.  Crea una base de datos en MySQL llamada `sistemaregistro`.
2.  Asegúrate de que las credenciales en `src/main/resources/application.properties` coincidan con tu configuración local:

```properties
spring.datasource.url=jdbc:mysql://localhost/sistemaregistro
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

### Ejecución

1.  Clona el repositorio.
2.  Navega a la carpeta del proyecto:
    ```bash
    cd Documents/system
    ```
3.  Ejecuta la aplicación usando Maven Wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
    (En Windows usa `mvnw.cmd spring-boot:run`)

Al iniciar, Flyway ejecutará automáticamente las migraciones SQL para crear las tablas necesarias (`usuarios`, `productos`, `notasdeventa`, `detallenotasdeventa`).

## 🔑 Autenticación y Uso

El sistema crea automáticamente un usuario administrador al iniciar si no existe (ver `SystemApplication.java`):
*   **Usuario:** `admin`
*   **Contraseña:** `123456`

### Endpoints Principales

Todos los endpoints (excepto `/login`) requieren el header `Authorization: Bearer <TOKEN>`.

#### Autenticación
*   `POST /login`: Iniciar sesión y obtener token JWT.

#### Productos
*   `GET /productos`: Listar productos (paginado).
*   `POST /productos`: Registrar un nuevo producto.

#### Notas de Venta
*   `POST /notadeventa`: Registrar una nueva venta (incluye cliente y lista de productos).

## 📂 Estructura del Proyecto

```
com.bloodlive.project.system
├── controller       # Controladores REST (Endpoints)
├── domain           # Entidades, Repositorios y DTOs (Records)
│   ├── detallesnotadeventa
│   ├── notadeventa
│   ├── producto
│   └── usuario
├── infra            # Configuración de infraestructura
│   └── security     # Configuración de seguridad y JWT
└── SystemApplication.java # Clase principal
```
