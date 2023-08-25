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
|![img.png](img/img36.png)|

| Usar el puerto correcto                                                                                                                                                                                                                                                                                                                                                                        |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![img_1.png](img/img_1.png)                                                                                                                                                                                                                                                                                                                                                                        |
| Lo usual es usar el puerto 1433, asignando a los puertos TCP y a los puertos dinámicos TCP el puerto 1433 de la respectiva dirección IP y luego reinicia el servidor. Sin embargo, Si no hay puertos asignados a IP1, IP2, etc se debe usar el que está en la opción "IPALL" que se encuentra haciendo scrolling más abajo de "Propiedades: TCP/IP"<br/>En este caso el puerto es: ***55580*** |

### 4. Añadir controlador JDBC para MS SQL Server en el proyecto
| JDBC Driver de Microsoft para SQL Server |
|-----------------------------------------|
| ![img_2.png](img/img_34.png)                 |
| Añadirlo a librerías                    |
|![img_3.png](img/img_35.png)                                         <br/>![img_4.png](img/img_4.png)|

### 5. Conexión a la base de Datos
| Generar la cadena de conexión                                                                                                                                                                                                                                 |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Comprobación de la conexión**                                                                                                                                                                                                                               |
|![img.png](img/img37.png)|
| En ella se indica el servidor, la instancia, el número de puerto, el nombre de la base de datos y para evitar problemas de conexión segura se debe añadir el sufijo ***encrypt=true; trustServerCertificate=true;***                                          |
| ![img_5.png](img/img_5.png)                                                                                                                                                                                                                                   |
| **Uso de la clase Connection**: crear un objeto de la misma, este junto al *DriverManager* nos permitirá controlar la conexión. Al método *.getConnection* se le enviarán como argumentos la cadena de conexión, el usuario y contraseña creados previamente. |
| ![img_6.png](img/img_6.png)                                                                                                                                                                                                                                   |

### 6. Inserción de Datos
| Añadir un evento al botón Ingresar                                                                                                              |
|-------------------------------------------------------------------------------------------------------------------------------------------------|
| Mediante un objeto de la clase PreparedStatement se ejecutará el query enviado como argumento del método preparedStatement del objeto conexión. |
| ![img_7.png](img/img_7.png)                                                                                                                         |
| **Prueba de la inserción de un registro en la tabla Persona**                                                                                   |
|  ![img_8.png](img/img_8.png)                                                                                                                                               <br/>![img_9.png](img/img_9.png)|

### 7. Borrar Datos
| Añadir un evento al botón BorrarRegistro                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------------------|
| Mediante un objeto de la clase Statement se ejecutará el query enviado como argumento del método executeUpdate del objeto statement. |
| ![img_10.png](img/img_10.png)                                                                                                            |
| **Prueba de eliminación de un Registro por medio del código**                                                                        |
| Tabla Persona antes del *Delete*                                                                                                     |
| ![img_11.png](img/img_11.png)                                                                                                            |
| Registro Eliminado                                                                                                                   |
| ![img_13.png](img/img_13.png)<br/>![img_14.png](img/img_14.png)                                                                                                            |                                                                                                            

### 8. Actualizar Registros
| Añadir un evento al Botón Actualizar                                                                                                            |
|-------------------------------------------------------------------------------------------------------------------------------------------------|
| Mediante un objeto de la clase PreparedStatement se ejecutará el query enviado como argumento del método preparedStatement del objeto conexión. |
| ![img_15.png](img/img_15.png)                                                                                                                       |
| Tabla Persona antes del *Update*                                                                                                                |
| ![img_16.png](img/img_16.png)                                                                                                                       |
| Registro Actualizado en función al código (pk de la Tabla Personas)                                                                             |
| ![img_17.png](img/img_17.png)                                                                                                                       |
| El registro cuyo código es 126 seteará los valores de cédula, nombre, Fecha Nacimiento y Signo                                                  |
| ![img_18.png](img/img_18.png)                                                                                                                       |
| Si se intenta actualizar un registro por medio de un ID que no existe, se imprimirá en consola un mensaje                                       |
| ![img_19.png](img/img_19.png)                                                                                                                       |
| Lo anterior, se debe a que preparedStatement.executeQuery retorna un entero que ayudará a determinar si el registro se actualizó o no           |
|![img_20.png](img/img_20.png)|

### 9. Búsquedas (Select)
| Búsqueda por Código                                      |
|----------------------------------------------------------|
| Tabla Persona actualizada                                |
| ![img_24.png](img/img_24.png)                                |
| Resultado de la búsqueda                                 |
| ![img_27.png](img/img_27.png)<br/>![img_28.png](img/img_28.png)  |

| Búsqueda por Nombre       |
|---------------------------|
| Antes                     |
| ![img_29.png](img/img_29.png) |
| Después                   |
|![img_30.png](img/img_30.png)|

| Búsqueda por Signo                                                                                                                                                                            |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Tabla Persona Actualizada                                                                                                                                                                     |
| ![img_31.png](img/img_31.png)                                                                                                                                                                     |
| Se realizará una búsqueda por el signo: *Cáncer*<br/>La primera incidencia que encuentre se mostrará en la interfaz, el resto registros que cumplan con la condición se mostrarán en consola. |
| ![img_32.png](img/img_32.png)<br/>En el signo *Acuario* hay más de un registro con esa condición:![img_33.png](img/img_33.png)                                                                        |

### 10. Diseño del Form
| Diseño Final                                                                                                           |
|------------------------------------------------------------------------------------------------------------------------|
| ![imagen](https://github.com/DavidPK8/CorreccionPrueba2Bimestre/assets/117743650/497a5993-a0dc-4b63-8bfb-5e190c8af093) |
