<?php
$host = 'localhost'; // Cambia si es necesario
$usuario = 'root'; // Tu usuario de la base de datos
$contraseña = ''; // Tu contraseña de la base de datos
$base_de_datos = 'gigagains'; // Tu base de datos

// Crear la conexión
$conexion = new mysqli($host, $usuario, $contraseña, $base_de_datos);

// Verificar la conexión
if ($conexion->connect_error) {
    die("Conexión fallida: " . $conexion->connect_error);
}
?>
