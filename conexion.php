<?php
// Configuración de los parámetros para la conexión a la base de datos

// Nombre del host (servidor) de la base de datos, generalmente es 'localhost' si está en el mismo servidor
$host = 'localhost'; // Cambia si es necesario

// Usuario de la base de datos. Este es el nombre de usuario que se usa para conectar a la base de datos
$usuario = 'DavidPulido_2696521'; // Tu usuario de la base de datos

// Contraseña del usuario de la base de datos. Este es el password asociado con el usuario para acceder a la base de datos
$contraseña = 'DavidPulido_2696521'; // Tu contraseña de la base de datos

// El nombre de la base de datos a la que te quieres conectar
$base_de_datos = 'gigagains'; // Tu base de datos

// Intentar establecer la conexión con la base de datos utilizando PDO (PHP Data Objects)
try {
    // Crear una nueva instancia de PDO para conectarse a la base de datos
    // Se establece la conexión con el servidor de la base de datos utilizando el nombre del host, nombre de la base de datos y los parámetros de usuario y contraseña
    // También se establece la codificación de caracteres como UTF-8 para evitar problemas con caracteres especiales
    $conexion = new PDO("mysql:host=$host;dbname=$base_de_datos;charset=utf8", $usuario, $contraseña);

    // Configurar PDO para que lance excepciones en caso de error en la conexión o ejecución de consultas
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    // Si ocurre un error al intentar conectarse, mostrar el mensaje de error
    // y detener la ejecución del script
    die("Conexión fallida: " . $e->getMessage());
}
?>
