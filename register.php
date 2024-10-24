<?php
// Configuración de la base de datos
$host = "localhost"; // o la dirección de tu servidor
$usuario = "root"; // Cambia esto por tu usuario de MySQL
$contraseña = ""; // Cambia esto por tu contraseña de MySQL
$base_de_datos = "gigagains"; // Nombre de tu base de datos

// Crear conexión
$conn = new mysqli($host, $usuario, $contraseña, $base_de_datos);

// Verificar la conexión
if ($conn->connect_error) {
    die(json_encode(["success" => false, "message" => "Error de conexión: " . $conn->connect_error]));
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

if ($conn->query($sql) === TRUE) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "Error: " . $conn->error]);
}

// Cerrar la conexión
$conn->close();
?>
