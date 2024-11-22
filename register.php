<?php
// Configuración de la base de datos
require 'conexion.php'; // Asegúrate de que este archivo esté configurado correctamente

// Obtener los datos del formulario
$nombre = $_POST['nombre'];
$correo = $_POST['correo'];
$contraseña = password_hash($_POST['contraseña'], PASSWORD_DEFAULT); // Encriptar la contraseña
$fecha_nacimiento = $_POST['fecha_nacimiento'];
$estatura = $_POST['estatura'];
$peso = $_POST['peso'];
$condicion_especial = $_POST['condicion_especial'];
$genero = isset($_POST['genero']) ? implode(", ", $_POST['genero']) : '';

// Verificar si el correo ya está registrado
$query = $conexion->prepare("SELECT * FROM usuario WHERE email = :correo");
$query->bindParam(':correo', $correo);
$query->execute();

if ($query->rowCount() > 0) {
    // Si el correo ya está registrado, enviar un mensaje de error
    echo json_encode(["success" => false, "message" => "El correo electrónico ya está registrado."]);
} else {
    // Si el correo no está registrado, proceder con la inserción
    $sql = $conexion->prepare("INSERT INTO usuario (nombre, email, contraseña, fecha_de_nacimiento, estatura, peso, condicion_especial, genero) 
                               VALUES (:nombre, :correo, :contraseña, :fecha_nacimiento, :estatura, :peso, :condicion_especial, :genero)");

    $sql->bindParam(':nombre', $nombre);
    $sql->bindParam(':correo', $correo);
    $sql->bindParam(':contraseña', $contraseña);
    $sql->bindParam(':fecha_nacimiento', $fecha_nacimiento);
    $sql->bindParam(':estatura', $estatura);
    $sql->bindParam(':peso', $peso);
    $sql->bindParam(':condicion_especial', $condicion_especial);
    $sql->bindParam(':genero', $genero);

    if ($sql->execute()) {
        echo json_encode(["success" => true]);
    } else {
        echo json_encode(["success" => false, "message" => "Error: " . $sql->errorInfo()[2]]);
    }
}

// Cerrar la conexión
$conexion = null;
?>
