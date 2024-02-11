# Taller 3 - AREP
Para este taller se continuó con la construcción de un servidor web en Java con una funcionalidad similar a la del microframework web de Spark. El servidor permite el registro de servicios get con funciones lambda y permite la configuración de servicios tipo post. Además, se continúo con la funcionalidad trabajada en el taller anterior que entrega archivos estáticos como html, css, javascript e imagen. Por otro lado, se implementaron funciones para configurar el directorio de donde se leerán los archivos estáticos, para cambiar el tipo de respuesta a *application/json* y, para leer los parámetros del query. 

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
Se continúa con el servidor HTTP que escucha por el puerto 35000 del taller anterior, pero se realizaron diferentes extensiones para ampliar su funcionalidad. 

En el caso de que la URI de una solicitud contenga una consulta de búsqueda de película, ahora se incluye la verificación del tipo de contenido que tendrá la respuesta.
Por defecto el tipo de contenido es *text/html* si no se proporciona el parametro *responseType*, pero si se envía *json* como valor del parámetro, entonces el tipo de respuesta cambia a *application/json*.

En caso de que no esté haciendo la búsqueda de una película, se verifica si el path contiene la cadena `/action`, lo que indica que es un servicio definido
por el usuario. De modo que, el servidor busca en un mapa que contiene dichos servicios y maneja la solicitud para la ruta especificada por medio de la interfaz *WebService*. Las rutas definidas para los servicios get son: `/arep`, `/arsw` e `/ieti` y dichos servicios web son definidos en la clase *MyWebServices* con su respectivo comportamiento. Si al solicitar alguno de estos servicios se añade una consulta que incluya un parámetro, entonces dentro del manejo de la solicitud se imprime adicionalmente el parámetro recibido de la query.

Ahora, si el path contiene la cadena `/files`, se cambia el directorio de donde se leen los archivos estáticos al directorio creado de *files*, pero si no se cumplen ninguna de estas condiciones se toma el directorio inicial para leer los archivos estáticos de `target/classes/public`.

También puede manejar solicitudes de tipo post, pero esta funcionalidad aún se encuentra en proceso.

## Evaluación
Ahora bien en caso de que el usuario quiera desarrollar una aplicación en el servidor, deberá definir la ruta y la acción que quiere ejecutar por medio de funciones lambda, y debe incluirlo en la clase *MyWebServices*. Para este ejemplo se definieron 3 rutas para los servicios get `/arep`, `/arsw` e `/ieti` con su respectivo comportamiento a realizar.

En caso de que quiera ver el servicio web asociado a la ruta `/arep` deberá ingresar la siguiente dirección en su navegador http://localhost:35000/action/arep

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/53e4cc36-f72f-4d15-8180-208e404d64ad)

Y si ingresa una consulta en la ruta, se mostrará cuál fue el parámetro ingresado, por ejemplo, con la dirección http://localhost:35000/action/arep?con=prueba se muestra lo siguiente:

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/adef9927-4cea-444b-8f3f-4ec0872dd2a6)

Se tiene un funcionamiento similar para las rutas de `/arsw` e `/ieti`. Con la dirección http://localhost:35000/action/arsw se observa:

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/0b242e91-a15e-4285-be21-e2d492f5f660)

Y al ingresar un parámetro http://localhost:35000/action/arsw?param=funciona

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/d03c70cd-b355-4780-bb3d-62cba8bbcec8)

Por último, si se ingresa la dirección http://localhost:35000/action/ieti

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/6c014cdd-e9db-4eae-b5a6-4ef6207d0e10)

Y su se ingresa un parámetro http://localhost:35000/action/ieti?query=hola

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/8768603c-60e4-4a65-af8a-5263707f9c73)

### Pruebas otras funcionalidades en Windows
Con lo anterior se probó el funcionamiento de los servicios web de tipo GET y se evidenció la funcionalidad de la lectura de parámetros del query. 

Ahora bien, los archivos estáticos traidos del directorio `public` siguen funcionando. Al ingresar la dirección http://localhost:35000/movies.html se muestra la página html que tiene css, script e imagen.

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/80b2dadd-bc77-4288-8f1d-7c86991993ba)

Y si se realiza la búsqueda de una película y se le da clic en el botón *Submit* se observa lo siguiente:

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/db59105b-734e-430e-a977-d2e1ba42b9dc)

Para solicitar un script http://localhost:35000/moviesScript.js

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/7a162fa1-39fc-4e6d-a8d0-51b471f8bc48)

Para solicitar el css http://localhost:35000/styles.css

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/5b2a8db6-9cc5-425c-b9ee-beccf59ff6ca)

Y para solicitar la imagen PNG http://localhost:35000/movie.png

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/02012f89-c205-4405-9f66-4d168f771a50)

Pero se pueden leer archivos estáticos del directorio `files` que fue creado. Para esto se ingresa la dirección http://localhost:35000/files/voleibol.html

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/ad1d8585-bce3-4f39-87b6-9d8859aceeec)

para consultar el css http://localhost:35000/files/styles.css

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/ed3fe567-8462-4cb4-81a5-621bbfc6cc0b)

Y para consultar la imagen PNG http://localhost:35000/files/voleibol.png

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/cee11f02-2d18-4d56-b7df-4ed04dcc0b5e)

En caso de ingresar una dirección inválida

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/1a33522d-ab57-48f3-9782-646136180c7f)

Por último, el tipo de respuesta, si el servidor se corre con el siguiente parámetro *json*, la respuesta de la búsqueda de las películas será en formato *application/json*

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/e1f9b8f0-fca9-45d7-84a9-b60a6039273f)

Aquí se puede observar que `Content-Type` es de tipo *application/json*

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/fe2c3359-15bc-45fc-a8a7-58f104581eb8)

Pero si el servidor se corre si especificar dicho parámetro, por defecto será en formato *text/html*

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/19c5e391-1499-49d5-a169-f771172b82cb)

Aquí se puede observar que `Content-Type` es de tipo *text/html*

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/a920cdee-2084-41bc-aef5-8fb4948abce8)

### Pruebas Linux
Estas pruebas se realizaron en una máquina virtual de Ubuntu si escribimos la dirección http:/[IP_servidor]/:35000/movies.html

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/260c2d8f-c5f8-46d5-9726-768db4c9417e)

ejemplo de búsqueda de una película

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/1678758a-232c-4654-8543-81f6c3b2d97e)

Archivo estático desde el otro directorio

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/012b71e1-540e-4b9c-b64b-af7a75db023b)

Ejemplo servicio web

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/30be504b-d257-4c63-9ae7-309a43349f27)

Si ingresa un parámetro

![image](https://github.com/JessicaDMunozO/Taller3-AREP/assets/123814482/c61ef181-2ba4-4eeb-8b7e-6dc77653814e)

## Construido con
Maven - Gestión de dependencias

## Versiones
Git - Control de versiones

## Autor
Jessica Daniela Muñoz Ossa
