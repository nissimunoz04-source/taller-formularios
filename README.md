# Sistema de Registro de Jugadores
## Escuela de Fútbol Los Cerritos

Proyecto desarrollado con Java 21, Spring Boot, Thymeleaf,
Spring Security y base de datos H2.

## ¿Qué hace el sistema?

Permite registrar, consultar, editar y eliminar jugadores.

Cuenta con dos tipos de usuarios:
- ADMIN: puede registrar, consultar, editar y eliminar.
- ENTRENADOR: solamente puede consultar jugadores.

## Requisitos

- Java 21 instalado.
- PowerShell.
- Proyecto descargado en la computadora.

## Variables de entorno

Antes de iniciar el sistema, configurar estas variables:

- ADMIN_PASSWORD
- ENTRENADOR_PASSWORD

Estas variables almacenan las contraseñas de los usuarios.
No se deben escribir las contraseñas directamente en el código
ni publicarlas en GitHub.

## Cómo iniciar el sistema

1. Abrir el proyecto en Visual Studio Code.
2. Abrir una terminal PowerShell.
3. Configurar las variables de entorno:

   $env:ADMIN_PASSWORD = Read-Host "Clave del administrador"
   $env:ENTRENADOR_PASSWORD = Read-Host "Clave del entrenador"

4. Ejecutar:

   .\mvnw spring-boot:run

5. Abrir en el navegador:

   http://localhost:8080/login

## Cómo ingresar

Usuario administrador: admin

Usuario entrenador: entrenador

Cada usuario debe ingresar la contraseña configurada
para su cuenta.

## Seguridad

El sistema utiliza Spring Security para proteger las rutas.

El administrador puede realizar todas las operaciones CRUD.

El entrenador solamente puede consultar jugadores y no tiene
permiso para registrar, editar ni eliminar.

La carpeta datos/ contiene la base de datos local H2
y está excluida de Git mediante .gitignore.

No se deben publicar contraseñas ni datos personales
de los jugadores en GitHub.

## Mini-nota: Tres decisiones de seguridad

1. Las contraseñas se manejan con variables de entorno para evitar publicarlas en GitHub. En la Semana 14 aplicaré esta práctica al configurar la conexión con Neon y Render.
2. Utilicé roles ADMIN y ENTRENADOR para controlar quién puede modificar los registros. En la Semana 14 mantendré estos permisos al trabajar con la base de datos en la nube.
3. Excluí la carpeta datos/ mediante .gitignore para proteger la información local de los jugadores. En la Semana 14 también cuidaré que las credenciales de la base de datos no se publiquen.

## Preparación para el despliegue en Render — Semana 14

El proyecto se está preparando para su publicación en internet
utilizando Render y una base de datos PostgreSQL alojada en Neon.

### Base de datos

- Desarrollo local: H2.
- Producción: PostgreSQL mediante Neon.
- La conexión de producción utiliza DB_URL, DB_USER y DB_PASSWORD.
- Las credenciales se mantienen fuera del repositorio.

### Docker

Se incorporó un Dockerfile para construir y ejecutar
la aplicación con Java 21.

También se creó un archivo .dockerignore para excluir
archivos innecesarios y datos privados de la construcción.

### Control de versiones

El proyecto utiliza dos ramas:

- development: rama de trabajo para preparar y probar cambios.
- main: rama principal destinada a la publicación.

Los cambios se integran mediante Pull Requests en GitHub.

### Seguridad en producción

Las contraseñas de ADMIN y ENTRENADOR se configuran
mediante variables de entorno.

Los permisos de cada rol se mantienen al utilizar
la base de datos PostgreSQL.

Las credenciales de Neon y las contraseñas de acceso
no deben publicarse en GitHub.