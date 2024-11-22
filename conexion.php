<?php
$host = 'localhost'; // Cambia si es necesario
$usuario = 'root'; // Tu usuario de la base de datos
$contraseña = ''; // Tu contraseña de la base de datos
$base_de_datos = 'gigagains'; // Tu base de datos

try {
    $conexion = new PDO("mysql:host=$host;dbname=$base_de_datos;charset=utf8", $usuario, $contraseña);
    // Configura PDO para lanzar excepciones en caso de error
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Conexión fallida: " . $e->getMessage());
}
?>
