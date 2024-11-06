<?php
session_start();
include('conexion.php');

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario_id'])) {
    echo "No has iniciado sesión.";
    exit;
}

// Obtener el ID del usuario
$usuario_id = $_SESSION['usuario_id'];

// Recuperar los datos del usuario desde la base de datos
$query = "SELECT * FROM usuario WHERE id = ?";
$stmt = $conexion->prepare($query);
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $user = $result->fetch_assoc();
} else {
    echo "Usuario no encontrado";
    exit;
}

// Verificar si se recibió la solicitud de actualización
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Obtener los datos del formulario
    $nombre = $_POST['nombre'];
    $email = $_POST['email'];
    $fecha_de_nacimiento = $_POST['fecha_de_nacimiento'];
    $estatura = $_POST['estatura'];
    $peso = $_POST['peso'];
    $condicion_especial = $_POST['condicion_especial'];
    $genero = $_POST['genero'];

    // Actualizar los datos en la base de datos
    $update_query = "UPDATE usuario SET nombre = ?, email = ?, fecha_de_nacimiento = ?, estatura = ?, peso = ?, condicion_especial = ?, genero = ? WHERE id = ?";
    $stmt = $conexion->prepare($update_query);
    $stmt->bind_param("sssssssi", $nombre, $email, $fecha_de_nacimiento, $estatura, $peso, $condicion_especial, $genero, $usuario_id);
    if ($stmt->execute()) {
        echo "Datos actualizados con éxito";
    } else {
        echo "Error al actualizar los datos";
    }
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
                <a href="home.php" class="enmedio-btn">Inicio</a>
                <a href="personalizar.php" class="enmedio-btn">Personalizar</a>
                <a href="dieta.php" class="enmedio-btn">Dieta</a>
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
                        <input type="email" id="email" value="<?php echo htmlspecialchars($user['email']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="date">Fecha de nacimiento</label>
                        <input type="date" id="date" value="<?php echo htmlspecialchars($user['fecha_de_nacimiento']); ?>" required>
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
                                <input class="checkboxx" type="radio" id="Femenino" name="genero" value="Femenino" <?php echo ($user['genero'] === 'Femenino') ? 'checked' : ''; ?> /> Femenino
                            </label>
                            <label>
                                <input class="checkboxx" type="radio" id="Masculino" name="genero" value="Masculino" <?php echo ($user['genero'] === 'Masculino') ? 'checked' : ''; ?> /> Masculino
                            </label>
                            <label>
                                <input class="checkboxx" type="radio" id="Otro" name="genero" value="Otro" <?php echo ($user['genero'] === 'Otro') ? 'checked' : ''; ?> /> Otro
                            </label>                
                        </div>
                    </div>
                    <div class="buttons">
                        <button type="submit">Actualizar</button>
                        <button type="button" onclick="window.location.href='profile.php'">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>

    <script src="assets/javascript/profile.js"></script>
</body>
</html>
