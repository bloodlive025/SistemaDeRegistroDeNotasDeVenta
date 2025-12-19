# Roadmap de Mejoras y Sprints

Este documento detalla el plan de trabajo propuesto para evolucionar y profesionalizar el Sistema de Registro de Notas de Venta.

## 🚀 Sprint 1: Robustez y Calidad (Testing & Validation)
**Objetivo:** Asegurar que el sistema base sea estable, maneje errores correctamente y sea confiable.

1.  **Manejo Global de Errores:**
    *   Implementar `@ControllerAdvice` para capturar excepciones.
    *   Estandarizar respuestas de error (JSON con código, mensaje y detalles).
    *   Manejar `EntityNotFoundException`, `MethodArgumentNotValidException`, etc.
2.  **Validaciones de Negocio:**
    *   Validar stock suficiente antes de vender.
    *   Validar precios y cantidades positivas.
3.  **Pruebas Unitarias (JUnit + Mockito):**
    *   Tests para servicios y lógica de negocio.
4.  **Pruebas de Integración:**
    *   Tests de controladores usando `MockMvc`.

## 🛡️ Sprint 2: Seguridad y Roles
**Objetivo:** Reforzar la seguridad y gestionar permisos de acceso.

1.  **Roles de Usuario:**
    *   Agregar entidad `Rol` o campo `role` en `Usuario`.
    *   Roles sugeridos: `ADMIN`, `VENDEDOR`.
2.  **Autorización por Endpoint:**
    *   `ADMIN`: Acceso total (crear productos, eliminar ventas, gestionar usuarios).
    *   `VENDEDOR`: Registrar ventas, listar productos.
3.  **Mejoras en JWT:**
    *   Implementar Refresh Token.
    *   Manejo de expiración de tokens.

## 💼 Sprint 3: Lógica de Negocio Avanzada
**Objetivo:** Implementar funcionalidades críticas para la operación real del negocio.

1.  **Gestión de Inventario:**
    *   Descontar stock automáticamente al confirmar una venta.
    *   Validación de concurrencia (evitar vender el mismo producto dos veces simultáneamente).
2.  **Gestión de Ventas:**
    *   Anulación de notas de venta (revertir stock).
    *   Cálculo automático de impuestos (IGV/IVA).
3.  **Reportes y Consultas:**
    *   Endpoint de ventas por rango de fechas.
    *   Ranking de productos más vendidos.
    *   Total de ingresos diarios/mensuales.
4.  **Búsqueda Avanzada:**
    *   Filtros dinámicos para productos (nombre, precio, categoría).

## 🐳 Sprint 4: DevOps y Documentación
**Objetivo:** Facilitar el despliegue y mantenimiento del software.

1.  **Dockerización:**
    *   Crear `Dockerfile` para la aplicación Java.
    *   Crear `docker-compose.yml` para orquestar App + Base de Datos.
2.  **Documentación API:**
    *   Enriquecer Swagger/OpenAPI con descripciones detalladas y ejemplos.
3.  **CI/CD Básico:**
    *   Configurar GitHub Actions para compilación y pruebas automáticas.

## 🖥️ Sprint 5: Frontend (Cliente)
**Objetivo:** Proveer una interfaz gráfica para los usuarios finales.

1.  **Desarrollo de Cliente Web:**
    *   Tecnologías sugeridas: React, Angular o Vue.js.
    *   Pantallas: Login, Catálogo de Productos, Carrito de Compras/Punto de Venta, Historial de Ventas.
