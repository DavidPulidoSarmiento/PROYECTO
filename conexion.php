<?php
$host = 'localhost'; // Cambia si es necesario
$usuario = 'DavidPulido_2696521'; // Tu usuario de la base de datos
$contraseña = 'DavidPulido'; // Tu contraseña de la base de datos
$base_de_datos = 'gigagains'; // Tu base de datos

// Crear la conexión
$conexion = new mysqli($host, $usuario, $contraseña, $base_de_datos);

// Verificar la conexión
if ($conexion->connect_error) {
    die("Conexión fallida: " . $conexion->connect_error);
}
?>
