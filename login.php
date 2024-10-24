<?php
session_start();
require 'conexion.php'; // Asegúrate de que este archivo esté configurado correctamente

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $correo = trim($_POST['correo']);
    $contraseña = $_POST['contraseña'];

    // Consulta para verificar el usuario
    $stmt = $conexion->prepare("SELECT * FROM usuario WHERE email = ?");
    $stmt->bind_param("s", $correo);
    $stmt->execute();
    $resultado = $stmt->get_result();

    // Verificar si el correo existe
    if ($resultado->num_rows === 1) {
        $usuario = $resultado->fetch_assoc();

        // Verificar la contraseña
        if (password_verify($contraseña, $usuario['contraseña'])) {
            // Contraseña correcta, iniciar sesión
            $_SESSION['usuario_id'] = $usuario['id']; // Guarda el ID del usuario en la sesión
            $_SESSION['usuario_correo'] = $usuario['email']; // Guarda el correo si es necesario
            header("Location: home.html"); // Redirige al usuario a la página principal
            exit();
        } else {
            // Contraseña incorrecta
            $error = "Contraseña incorrecta";
        }
    } else {
        // Correo no encontrado
        $error = "Correo no encontrado";
    }

    $stmt->close();
}

// Opcional: mostrar mensaje de error en caso de fallo
if (isset($error)) {
    echo "<script>alert('$error');</script>";
}
?>
