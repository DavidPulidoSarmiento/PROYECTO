<?php
// Configuración de la base de datos
require 'conexion.php'; // Asegúrate de que este archivo esté configurado correctamente

// Verificar la conexión
if ($conexion->connect_error) {
    die(json_encode(["success" => false, "message" => "Error de conexión: " . $conexion->connect_error]));
}

// Obtener los datos del formulario
$nombre = $_POST['nombre'];
$correo = $_POST['correo'];
$contraseña = password_hash($_POST['contraseña'], PASSWORD_DEFAULT); // Encriptar la contraseña
$fecha_nacimiento = $_POST['fecha_nacimiento'];
$estatura = $_POST['estatura'];
$peso = $_POST['peso'];
$condicion_especial = $_POST['condicion_especial'];
$genero = isset($_POST['genero']) ? implode(", ", $_POST['genero']) : '';

// Insertar en la base de datos
$sql = "INSERT INTO usuario (nombre, email, contraseña, fecha_de_nacimiento, estatura, peso, condicion_especial, genero) VALUES ('$nombre', '$correo', '$contraseña', '$fecha_nacimiento', '$estatura', '$peso', '$condicion_especial', '$genero')";

if ($conexion->query($sql) === TRUE) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "Error: " . $conexion->error]);
}

// Cerrar la conexión
$conexion->close();
?>
