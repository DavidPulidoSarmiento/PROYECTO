<?php
session_start();
require 'conexion.php'; // Asegúrate de que este archivo esté configurado correctamente

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $correo = trim($_POST['correo']);
    $contrasena = $_POST['contrasena'];

    try {
        // Consulta para verificar el usuario
        $stmt = $conexion->prepare("SELECT * FROM usuario WHERE email = :correo");
        $stmt->bindParam(':correo', $correo, PDO::PARAM_STR);
        $stmt->execute();

        // Verificar si el correo existe
        if ($stmt->rowCount() === 1) {
            $usuario = $stmt->fetch(PDO::FETCH_ASSOC);
            
            // Verificar la contraseña
            if (password_verify($contrasena, $usuario['contrasena'])) {
                // Contraseña correcta, iniciar sesión
                $_SESSION['usuario_id'] = $usuario['ID']; // Guarda el ID del usuario en la sesión
                header("Location: home.php"); // Redirige al usuario a la página principal
                exit();
            } else {
                // Contraseña incorrecta
                $error = "Contraseña incorrecta";
            }
        } else {
            // Correo no encontrado
            $error = "Correo no encontrado";
        }
    } catch (PDOException $e) {
        $error = "Error en el sistema: " . $e->getMessage();
    }
}

// Opcional: mostrar mensaje de error en caso de fallo
if (isset($error)) {
    echo "<script>alert('$error');</script>";
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/iniciar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="login-box">
            <h2>Iniciar sesión</h2>
            <form id="loginForm" action="" method="POST">
                <div class="textbox">
                    <input type="text" placeholder="correo" name="correo" id="correo" required>
                </div>
                <div class="textbox">
                    <input type="password" placeholder="contraseña" name="contrasena" id="contrasena" required>
                </div>
                <button type="submit" class="btn">Iniciar sesión</button>
                <a href="registrate.html" class="register-link">Regístrate</a>
            </form>
        </div>
    </div>
    <script src="assets/javascript/registerylogin.js"></script>
</body>
</html>
