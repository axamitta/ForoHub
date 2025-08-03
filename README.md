# 📚FOROHUB API REST
### 🏹 Description
**FOROHUB** es una API REST construida con **Spring Boot** 3 para gestionar foros de discusión. 
Permite a los usuarios crear, leer, actualizar y eliminar _tópicos_ (preguntas/discusiones). La aplicación implementa **autenticación y autorización** con JWT y control de acceso basado en roles para garantizar que solo los usuarios autorizados puedan realizar ciertas acciones.

# 🛠️ Tecnologías utilizadas
- **Java 17** - Lenguaje de programación robusto y moderno.
- **Spring Boot** - Framework para facilitar el desarrollo de aplicaciones web y APIs.
- **Spring Security** - Módulo para la seguridad, manejo de autenticación y autorización.
- **JWT (JSON Web Tokens)** - Para autenticación y manejo seguro de sesiones.
- **JPA/Hibernate** - ORM para facilitar la interacción con la base de datos.
- **MySQL** - Base de datos relacional para persistencia de datos.
- **Flyway** – Migraciones automáticas para el control de versiones de la base de datos.

# ✨ Funcionalidades principales
1. **Autenticación y autorización**
Inicio de sesión con credenciales (usuario y contraseña) que genera un **token JWT**.
⬇
### 📌 Pantalla de login⬇️



2. **Manejo de errores personalizados** para respuestas claras en caso de acceso no autorizado o credenciales inválidas.

3. **Gestión de tópicos CRUD**
Crear, consultar, modificar y eliminar tópicos en el foro.
Validación de datos de entrada con Bean Validation para asegurar integridad y evitar errores.
Protección de endpoints según roles: ADMIN, USUARIO y MODERADOR.

4. **Persistencia y modelos**
Uso de JPA para definir entidades y relaciones.
Roles asignados a usuarios para control granular de permisos.
Migraciones gestionadas con Flyway para control de versiones de base de datos.


## 🫀 Endpoints principales
| Método   | Endpoint           | Descripción                  | Authenticación | Rol requerido      |          
|----------|--------------------|------------------------------|----------------|--------------------|
| `POST`   | `/usuarios`        | Registro de nuevo usuario    | No             |         -          |
| `POST`   | `/login`           | Genera un JWT para sesión    | No             |         -          |
| `POST`   | `/cursos`          | Crea un nuevo curso          | Sí             |       ADMIN        |
| `POST`   | `/topicos`         | Crea un nuevo tópico         | Sí             |      USUARIO       |
| `GET`    | `/topicos`         | Obtiene todos los tópicos    | Sí             |       ADMIN        |           
| `GET`    | `/topicos/{id}`    | Obtiene un tópico específico | Sí             |         -          |
| `PUT`    | `/topicos/{id}`    | Actualiza un tópico por ID   | Sí             | USUARIO, MODERADOR |
| `DELETE` | `/topicos/{id}`    | Elimina un tópico por ID     | Sí             |       ADMIN        |


 # 📂 Estructura del proyecto:
**controller:** Controladores REST para endpoints HTTP.
**domain:** Modelos de negocio y lógica central.
**infra:** Infraestructura, seguridad, manejo de excepciones.
**repository:** Interfaces para acceso a datos (JPA repositories).
**security:** Configuraciones y filtros de seguridad JWT y roles.

# ⚙️ Configuración
Configura el archivo application.properties con las credenciales de conexión a la base de datos MySQL y la clave secreta para JWT (api.security.token.secret).
Ejecuta las migraciones Flyway para crear tablas y asignar roles iniciales.
