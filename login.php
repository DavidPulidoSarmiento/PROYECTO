<?php
// Iniciar la sesión para gestionar los datos del usuario a través de la sesión
session_start();

// Incluir el archivo de conexión a la base de datos
require 'conexion.php'; // Asegúrate de que este archivo esté configurado correctamente para la conexión a la base de datos

// Verificar si el formulario ha sido enviado a través de un POST (acción de login)
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Obtener el correo y la contraseña del formulario, eliminando espacios innecesarios en el correo
    $correo = trim($_POST['correo']);
    $contrasena = $_POST['contrasena'];

    try {
        // Preparar una consulta SQL para verificar si el correo ya está registrado en la base de datos
        $stmt = $conexion->prepare("SELECT * FROM usuario WHERE email = :correo");
        // Asociar el parámetro :correo con el valor recibido del formulario
        $stmt->bindParam(':correo', $correo, PDO::PARAM_STR);
        // Ejecutar la consulta
        $stmt->execute();

        // Verificar si el correo existe en la base de datos (si la consulta devuelve una fila)
        if ($stmt->rowCount() === 1) {
            // Si el correo existe, recuperar los datos del usuario (debe existir solo uno)
            $usuario = $stmt->fetch(PDO::FETCH_ASSOC);
            
            // Verificar si la contraseña proporcionada coincide con la contraseña almacenada en la base de datos
            if (password_verify($contrasena, $usuario['contrasena'])) {
                // Si la contraseña es correcta, iniciar sesión guardando el ID del usuario en la sesión
                $_SESSION['usuario_id'] = $usuario['ID']; // Guardamos el ID del usuario en la sesión
                // Redirigir al usuario a la página principal (home.php)
                header("Location: home.php");
                exit(); // Detener la ejecución para evitar que el código se siga ejecutando después de la redirección
            } else {
                // Si la contraseña es incorrecta, mostrar mensaje de error
                $error = "Contraseña incorrecta";
            }
        } else {
            // Si el correo no existe en la base de datos, mostrar mensaje de error
            $error = "Correo no encontrado";
        }
    } catch (PDOException $e) {
        // Si ocurre un error con la base de datos, mostrar mensaje de error
        $error = "Error en el sistema: " . $e->getMessage();
    }
}

// Si hay un error (por ejemplo, correo o contraseña incorrecta), mostrar un mensaje de alerta en el navegador
if (isset($error)) {
    echo "<script>alert('$error');</script>";
}
?>

<!-- Página HTML para el formulario de login -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Enlazar archivos CSS para los estilos de la página -->
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/iniciar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Login</title>
</head>
<body>
    <!-- Contenedor principal de la página -->
    <div class="container">
        <div class="login-box">
            <h2>Iniciar sesión</h2>
            <!-- Formulario de inicio de sesión -->
            <form id="loginForm" novalidate action="" method="POST">
                <div class="textbox">
                    <!-- Campo para el correo electrónico del usuario -->
                    <input type="text" placeholder="correo" name="correo" id="correo" required>
                </div>
                <div class="textbox">
                    <!-- Campo para la contraseña del usuario -->
                    <input type="password" placeholder="contraseña" name="contrasena" id="contrasena" required>
                </div>
                <!-- Botón para enviar el formulario -->
                <button type="submit" class="btn">Iniciar sesión</button>
                <!-- Enlace para que el usuario se registre si aún no tiene una cuenta -->
                <a href="registrate.html" class="register-link">Regístrate</a>
            </form>
        </div>
    </div>
    <!-- Enlace al archivo JavaScript para validaciones o interacciones adicionales -->
    <script src="assets/javascript/registerylogin.js"></script>
</body>
</html>
