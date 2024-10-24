<?php
session_start();
// Aquí debes incluir la conexión a tu base de datos
include('conexion.php');
include_once 'config.php';


// Obtener el ID del usuario desde la sesión
$userId = $_SESSION['user_id']; // Asegúrate de que este ID se haya guardado al iniciar sesión

// Recuperar los datos del usuario desde la base de datos
$query = "SELECT * FROM usuarios WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $userId);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $user = $result->fetch_assoc();
} else {
    // Manejar el caso donde no se encuentra el usuario
    echo "Usuario no encontrado";
    exit;
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/profile.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Perfil de Usuario</title>
</head>
<body>
    <header>
        <div class="encabezado container">
            <div class="logo">
                <img src="assets/image/logoGiGa.svg" alt="GigaGains">
                <p>GigaGains</p>
            </div>
            <div class="enmedio">
                <a href="home.html" class="enmedio-btn">Inicio</a>
                <a href="personalizar.html" class="enmedio-btn">Personalizar</a>
                <a href="dieta.html" class="enmedio-btn">Dieta</a>
            </div>
            <a href="profile.php" class="profile-btn"><img src="assets/image/profile.svg" width="90" height="70"></a>
        </div>
    </header>
    <section>
        <div class="container">
            <div class="user-form">
                <h2>Perfil de usuario</h2>
                <form id="userProfileForm">
                    <div class="item-form">
                        <label for="name">Nombre</label>
                        <input type="text" id="name" value="<?php echo htmlspecialchars($user['nombre']); ?>" required>  
                    </div>
                    <div class="item-form">
                        <label for="email">Correo</label>
                        <input type="email" id="email" value="<?php echo htmlspecialchars($user['correo']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" placeholder="contraseña" required>
                    </div>
                    <div class="item-form">
                        <label for="password-confirm">Confirmar contraseña</label>
                        <input type="password" id="password-confirm" placeholder="confirmar contraseña" required>
                    </div>
                    <div class="item-form">
                        <label for="date">Fecha de nacimiento</label>
                        <input type="date" id="date" value="<?php echo htmlspecialchars($user['fecha_nacimiento']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="stature">Estatura</label>
                        <input type="text" id="stature" value="<?php echo htmlspecialchars($user['estatura']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="weight">Peso</label>
                        <input type="text" id="weight" value="<?php echo htmlspecialchars($user['peso']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="special-condition">Condición especial</label>
                        <input type="text" id="special-condition" value="<?php echo htmlspecialchars($user['condicion_especial']); ?>" required>
                    </div>
                    <div class="genero">
                        <div class="conttige">
                            <label for="genero">Género</label>
                        </div>
                        <div class="contitge">
                            <label>
                                <input class="checkboxx" type="checkbox" id="Femenino" name="genero" value="Femenino" <?php echo ($user['genero'] === 'Femenino') ? 'checked' : ''; ?> /> Femenino
                            </label>
                            <label>
                                <input class="checkboxx" type="checkbox" id="Masculino" name="genero" value="Masculino" <?php echo ($user['genero'] === 'Masculino') ? 'checked' : ''; ?> /> Masculino
                            </label>
                            <label>
                                <input class="checkboxx" type="checkbox" id="Otro" name="genero" value="Otro" <?php echo ($user['genero'] === 'Otro') ? 'checked' : ''; ?> /> Otro
                            </label>                
                        </div>
                    </div>
                    <div class="buttons">
                        <button type="submit">Actualizar</button>
                        <button type="button" onclick="window.location.href='home.html'">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script src="assets/js/app.js"></script>
</body>
</html>
