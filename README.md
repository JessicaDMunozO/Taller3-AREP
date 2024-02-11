# Taller 3 - AREP
Para este taller se continuo con la construcción de un servidor web en Java con una funcionalidad similar a la del microframework web de Spark. El servidor permite el registro 
de servicios get con funciones lambda y permite la configuración de servicios tipo post. Además, se continúo con la funcionalidad trabajada en el taller anterior que entrega 
archivos estáticos como html, css, javascript e imagen. Por otro lado, se implementaron funciones para configurar el directorio de donde se leerán los archivos estáticos,
para cambiar el tipo de respuesta a *application/json* y, para leer los parámetros del query. 

## Empezando
Las siguientes instrucciones permiten que obtenga una copia del proyecto en funcionamiento.

### Prerrequisitos
1. Debe tener Maven y JDK para compilar y ejecutar el proyecto.
2. Verificar que el puerto 35000 esté disponible para que el servidor web lo pueda usar sin inconvenientes.
3. Tener conexión a internet.

### Instalación
Ahora bien, para obtener el proyecto y ejecutarlo debe ser descargado en formato zip o puede ser clonado desde el repositorio de GitHub. Con el proyecto en su máquina, 
debe acceder al directorio que contiene el proyecto. Luego, debe descargar las dependencias del proyecto, para esto ejecute el comando `mvn install`. 
Después, para compilar ejecute el comando `mvn package` y por último ejecute el comando `java -cp .\target\classes\ edu.escuelaing.arem.ASE.app.MyWebServices`.

## Despliegue
Con el proyecto corriendo debe abrir en un navegador la siguiente dirección: http://localhost:35000/movies.html allí podrá observar el html completo, 
que fue traido desde el disco local. Veremos los demás recursos y la forma de acceder a ellos en las pruebas que se mostrarán más adelante.

## Diseño
Se continúa con el servidor HTTP que escucha por el puert 35000 del taller anterior, pero se realizaron diferentes extensiones para ampliar su funcionalidad. 

En el caso de que la URI de una solicitud contenga una consulta de búsqueda de película, ahora se incluye la verificación del tipo de contenido que tendrá la respuesta.
Por defecto el tipo de contenido es *text/html* si no se proporciona el parametro *responseType*, pero si se envía *json* como valor del parámetro, entonces el tipo de respuesta 
cambia a *application/json*.

En caso de que no esté haciendo la búsqueda de una película, se verifica si el path contiene la cadena `/action`, lo que indica que es un servicio definido
por el usuario. De modo que, el servidor busca en un mapa que contiene dichos servicios y maneja la solicitud para la ruta especificada. Las rutas definidas para los servicios get 
son: `/arep`, `/arsw` y `/ieti`. Si al solicitar alguno de estos servicios se añade una consulta que incluya un parámetro, entonces dentro del manejo de la solicitud se imprime
adicionalmente el parámetro recibido de la query.

Ahora, si el path contiene la cadena `/files`, se cambia el directorio de donde se leen los archivos estáticos, pero si no se cumplen ninguna de estas condiciones se 
toma el directorio inicial para leer los archivos estáticos de `target/classes/public`.

También puede manejar solicitudes de tipo post, pero esta funcionalidad aún se encuentra en proceso.

## Evaluación

## Construido con
Maven - Gestión de dependencias

## Versiones
Git - Control de versiones

## Autor
Jessica Daniela Muñoz Ossa
