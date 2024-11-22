<?php
// Configuración de la base de datos
require 'conexion.php'; // Incluir el archivo de conexión a la base de datos. Asegúrate de que esté correctamente configurado.


// Obtener los datos del formulario que el usuario ha enviado a través del método POST
$nombre = $_POST['nombre']; // Nombre del usuario
$correo = $_POST['correo']; // Correo electrónico del usuario
$contrasena = password_hash($_POST['contrasena'], PASSWORD_DEFAULT); // Encriptar la contraseña usando la función password_hash para seguridad
$fecha_nacimiento = $_POST['fecha_nacimiento']; // Fecha de nacimiento del usuario
$estatura = $_POST['estatura']; // Estatura del usuario
$peso = $_POST['peso']; // Peso del usuario
$condicion_especial = $_POST['condicion_especial']; // Condición especial del usuario (si tiene alguna)
$genero = isset($_POST['genero']) ? implode(", ", $_POST['genero']) : ''; // El género del usuario (puede ser múltiple, por eso se usa implode para unir las opciones seleccionadas)

// Verificar si el correo electrónico ya está registrado en la base de datos
$query = $conexion->prepare("SELECT * FROM usuario WHERE email = :correo"); // Preparar la consulta para verificar si el correo ya existe
$query->bindParam(':correo', $correo); // Vincular el parámetro de correo a la consulta
$query->execute(); // Ejecutar la consulta

// Comprobar si el correo ya está registrado
if ($query->rowCount() > 0) {
    // Si el correo ya está registrado, devolver un mensaje de error en formato JSON
    echo json_encode(["success" => false, "message" => "El correo electrónico ya está registrado."]);
} else {
    // Si el correo no está registrado, proceder con la inserción de los nuevos datos en la base de datos
    $sql = $conexion->prepare("INSERT INTO usuario (nombre, email, contrasena, fecha_de_nacimiento, estatura, peso, condicion_especial, genero) 
                               VALUES (:nombre, :correo, :contrasena, :fecha_nacimiento, :estatura, :peso, :condicion_especial, :genero)");

    // Vincular los parámetros de la consulta con los valores obtenidos del formulario
    $sql->bindParam(':nombre', $nombre); // Vincular el nombre
    $sql->bindParam(':correo', $correo); // Vincular el correo electrónico
    $sql->bindParam(':contrasena', $contrasena); // Vincular la contraseña (ya encriptada)
    $sql->bindParam(':fecha_nacimiento', $fecha_nacimiento); // Vincular la fecha de nacimiento
    $sql->bindParam(':estatura', $estatura); // Vincular la estatura
    $sql->bindParam(':peso', $peso); // Vincular el peso
    $sql->bindParam(':condicion_especial', $condicion_especial); // Vincular la condición especial
    $sql->bindParam(':genero', $genero); // Vincular el género (puede ser múltiple)

    // Intentar ejecutar la consulta para insertar los datos en la base de datos
    if ($sql->execute()) {
        // Si la inserción es exitosa, devolver un mensaje de éxito en formato JSON
        echo json_encode(["success" => true]);
    } else {
        // Si ocurre un error al insertar, devolver un mensaje de error con el detalle
        echo json_encode(["success" => false, "message" => "Error: " . $sql->errorInfo()[2]]);
    }
}

// Cerrar la conexión a la base de datos
$conexion = null;
?>
