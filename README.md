# **Microservicio de Gestión de Usuarios con Spring Boot**

**Microservicio de Gestión de Usuarios con Spring Boot** es un microservicio que se encarga de gestionar el ciclo de vida completo de los usuarios en una aplicación. Este servicio permite **crear, autenticar y autorizar** usuarios de forma segura y eficiente. Además, gestiona la asignación de roles y permisos para controlar el acceso a recursos específicos.

El microservicio expone endpoints RESTful para realizar las siguientes operaciones:

- **Registro de nuevos usuarios**: Crear una nueva cuenta de usuario.
- **Autenticación de usuarios**: Validación de credenciales y generación de tokens de autenticación (JWT).
- **Gestión de roles y permisos**: Asignar roles y permisos a los usuarios para controlar su acceso a diferentes partes del sistema.

## **Funcionalidades actuales**:

1. **Servicio de Creación de Usuarios**:
   - Registra un nuevo usuario en el sistema.
   - Realiza validaciones de datos (por ejemplo, que el correo electrónico no esté registrado previamente).
   - Encripta la contraseña con **BCrypt** y almacena la información en la base de datos.

2. **Servicio de Autenticación de Usuarios**:
   - Verifica las credenciales de los usuarios (nombre de usuario y contraseña).
   - Si las credenciales son correctas, genera un **JWT (JSON Web Token)** que permitirá al usuario acceder a recursos protegidos.

3. **Servicio de Autorización de Usuarios**:
   - Verifica el **JWT** del usuario para validar su identidad y autorización para acceder a recursos específicos.
   - Controla el acceso a partes del sistema según los roles y permisos del usuario.

## **Funcionalidades pendientes**:

- **Renovación de tokens de acceso**: Se planea implementar un mecanismo para renovar tokens de acceso y proporcionar una experiencia de sesión más prolongada.
- **Gestión de roles y permisos**: Actualmente, los roles y permisos están planificados para ser gestionados de forma más dinámica, lo que permitirá asignar y modificar permisos a los usuarios según sea necesario.
- **Actualización de usuarios**: La funcionalidad para actualizar los datos de los usuarios (como correo electrónico, nombre o contraseña) aún está pendiente de implementación.
- **Eliminación de usuarios**: La funcionalidad para eliminar usuarios del sistema, incluyendo la eliminación de roles y permisos asociados, está pendiente.

## **Características principales**:

- **Autenticación de usuarios** utilizando **JWT (JSON Web Tokens)**.
- **Gestión de roles y permisos** basada en el principio de **mínimos privilegios** (en desarrollo).
- **Creación de usuarios**.
- **Contraseñas encriptadas** con **BCrypt** para mejorar la seguridad.
- Integración sencilla con otros microservicios mediante tokens de acceso.
- **Endpoints RESTful** para una integración fácil con otras aplicaciones o servicios.

## **Tecnologías utilizadas**:

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**: Para la autenticación y autorización de usuarios.
- **Spring Data JDBC**: Para la conexión con la base de datos de manera sencilla y eficiente.
- **PostgreSQL**: Motor de base de datos relacional utilizado para almacenar información de usuarios, roles y permisos.
- **JWT (JSON Web Token)**: Para gestionar tokens de autenticación de manera segura.
- **BCrypt**: Para la encriptación de contraseñas.
- **Vavr**: Para un enfoque funcional en la gestión de errores y estructuras de datos.
- **JUnit y Mockito**: Para pruebas unitarias y pruebas de integración.

## **Diseño de la base de datos**:

La base de datos está diseñada con un modelo relacional utilizando **PostgreSQL**. Las principales tablas incluyen:

- **Usuarios**: Almacena la información básica del usuario, como nombre, correo electrónico y contraseña encriptada.
- **Roles**: Define los diferentes roles de usuario (por ejemplo, "ADMIN", "USER").
- **Permisos**: Gestiona los permisos asociados a cada rol.
- **Usuarios_Roles**: Relaciona los usuarios con los roles que tienen asignados.

La estructura de la base de datos sigue principios de normalización para garantizar la eficiencia y escalabilidad.

## **Funciones y Triggers**:

El sistema de base de datos de este microservicio emplea **funciones** y **triggers** para garantizar la integridad y validez de los datos. A continuación se describen las funciones y triggers implementados:

### Funciones:
1. **check_address_before_insert**: Verifica que el usuario tenga al menos una dirección asociada antes de insertar un nuevo registro en la tabla `address`.
2. **validate_email_format**: Valida el formato del correo electrónico antes de que se guarde en la base de datos.
3. **check_email_uniqueness**: Asegura que el correo electrónico sea único en la base de datos.
4. **validate_email**: Combina las validaciones del correo electrónico (formato y unicidad) antes de insertar un nuevo usuario en la tabla `person`.
5. **check_phone_before_insert**: Verifica que el usuario tenga al menos un número de teléfono antes de insertar un nuevo registro en la tabla `phones`.
6. **validate_phone_format**: Valida el formato del número de teléfono antes de que se guarde en la base de datos.
7. **validate_phone_uniqueness**: Asegura que el número de teléfono sea único en la base de datos.
8. **validate_phone**: Combina las validaciones del teléfono (formato y unicidad) antes de insertar un nuevo teléfono en la tabla `phones`.
9. **update_timestamp_fnc**: Actualiza la columna `updated_at` de las tablas cuando un registro es modificado.

### Triggers:
- **update_address_update_at**: Actualiza el campo `updated_at` cada vez que un registro en la tabla `address` es actualizado.
- **validate_email_trigger**: Valida el correo electrónico después de la inserción de un nuevo registro en la tabla `person`.
- **phone_presence_trigger**: Verifica la presencia de al menos un número de teléfono después de la inserción de un nuevo usuario.
- **validate_phone_trigger**: Valida el formato y la unicidad del teléfono después de la inserción de un nuevo teléfono en la tabla `phones`.
- **update_timestamp_trigger**: Actualiza la columna `updated_at` en las tablas `person`, `phones`, `address` y `clients` antes de la inserción o actualización de un registro.

## **Licencia de Demostración (Trial License)**

Este software está destinado únicamente para fines de **demostración** y **evaluación**. Está prohibido modificar, redistribuir o utilizar este software para fines comerciales sin la adquisición de una licencia adecuada.

### Restricciones:
1. **Uso no comercial**: El software solo puede ser utilizado con fines de evaluación personal o profesional.
2. **Prohibición de modificación y redistribución**: No se permite modificar el código fuente ni redistribuirlo, ya sea en forma original o modificada.
3. **Duración limitada**: El uso de este software está limitado a [número de días o periodo de prueba].
4. **Sin garantías**: Este software se proporciona "tal cual", sin garantías de ningún tipo, ya sea expresa o implícita.

Si deseas obtener una **licencia comercial completa** para modificar y redistribuir este software, por favor contacta con el autor para discutir las opciones de compra.

## **Contacto**

Para más información o consultas sobre este proyecto, puedes contactarme a través de:

- **Correo electrónico**: [joseherrera@tuta.io](mailto:joseherrera@tuta.io)

Si estás interesado en obtener una **licencia comercial completa** o discutir sobre el uso del software, no dudes en enviarme un mensaje.

