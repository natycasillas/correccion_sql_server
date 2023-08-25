# CorreccionPrueba2Bimestre
## Detalle de elaboración del programa
### 1. Desarrollo de la interfaz gráfica
|Creación de ventanas con Java Swing|
|-|
|![image](https://github.com/DavidPK8/CorreccionPrueba2Bimestre/assets/117743650/db825df7-b486-4d35-a3ca-6eedc59a4f0e)|
|Gracias a este se construyen los elementos gráficos que va a interactuar con el código, el cual permitirá la realización de consultas con la base de datos|
### 2. Creación de la Base de Datos en SQL Server Managament
| Base de Datos: Personas                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![img_2.png](img/img_2.png)                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| esto se realiza con el fin de poder realizar el CRUD de los datos en la tabla: **Persona**                                                                                                                                                                                                                                                                                                                                                                                           |
| **Nota:** es importante tener previamente creado un usuario con certificación SQL y en él generar la Base de Datos. Una forma es dándole al usuario en "server roles" el rol fijo de nivel de servidor: ***sysadmin*** para poder realizar cualquier actividad en el servidor y evitar la asignación de permisos específicos al usuario, no obstante, lo idóneo es que el administrador del sistema asigne una base de datos al usuario y le otorgue y deniegue permisos específicos |
| ![img_3.png](img/img_3.png)                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ![img.png](img/img4.png)                                                                                                                                                                                                                                                                                                                                                                                                                                                             <br/>|

### 3. Configuraciones Previas en el Motor de la Base de Datos
| Habilitar conexiones TCP/IP y "Named Pipes" al servidor SQL |
|------------------------------------------------------------|
|![img.png](img.png)|

| Usar el puerto correcto                                                                                                                                                                                                                                                                                                                                                                        |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![img_1.png](img_1.png)                                                                                                                                                                                                                                                                                                                                                                        |
| Lo usual es usar el puerto 1433, asignando a los puertos TCP y a los puertos dinámicos TCP el puerto 1433 de la respectiva dirección IP y luego reinicia el servidor. Sin embargo, Si no hay puertos asignados a IP1, IP2, etc se debe usar el que está en la opción "IPALL" que se encuentra haciendo scrolling más abajo de "Propiedades: TCP/IP"<br/>En este caso el puerto es: ***55580*** |

### 4. Añadir controlador JDBC para MS SQL Server en el proyecto
| JDBC Driver de Microsoft para SQL Server |
|-----------------------------------------|
| ![img_2.png](img_2.png)                 |
| Añadirlo a librerías                    |
|![img_3.png](img_3.png)                                         <br/>![img_4.png](img_4.png)|

### 5. Conexión a la base de Datos
| Generar la cadena de conexión                                                                                                                                                                                                                                 |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| En ella se indica el servidor, la instancia, el número de puerto, el nombre de la base de datos y para evitar problemas de conexión segura se debe añadir el sufijo ***encrypt=true; trustServerCertificate=true;***                                          |
| ![img_5.png](img_5.png)                                                                                                                                                                                                                                       |
| **Uso de la clase Connection**: crear un objeto de la misma, este junto al *DriverManager* nos permitirá controlar la conexión. Al método *.getConnection* se le enviarán como argumentos la cadena de conexión, el usuario y contraseña creados previamente. |
| ![img_6.png](img_6.png)                                                                                                                                                                                                                                       |