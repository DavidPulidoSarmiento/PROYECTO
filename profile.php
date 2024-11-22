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
$query = "SELECT * FROM usuario WHERE id = :id";
$stmt = $conexion->prepare($query);
$stmt->bindParam(':id', $usuario_id, PDO::PARAM_INT);
$stmt->execute();
$user = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$user) {
    echo "Usuario no encontrado";
    exit;
}

// Verificar si se recibió la solicitud de actualización
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Obtener y sanitizar los datos del formulario
    $nombre = trim($_POST['nombre']);
    $email = trim($_POST['email']);
    $fecha_de_nacimiento = trim($_POST['fecha_de_nacimiento']);
    $estatura = trim($_POST['estatura']);
    $peso = trim($_POST['peso']);
    $condicion_especial = trim($_POST['condicion_especial']);
    $genero = trim($_POST['genero']);

    // Inicializar un array para almacenar mensajes de error
    $errors = [];

    // Validaciones del lado del servidor

    // Validar nombre
    if (empty($nombre)) {
        $errors[] = "El campo de nombre es obligatorio.";
    } elseif (!preg_match("/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/", $nombre)) {
        $errors[] = "El nombre solo debe contener letras y espacios.";
    }

    // Validar email
    if (empty($email)) {
        $errors[] = "El campo de correo es obligatorio.";
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $errors[] = "Por favor ingresa un correo electrónico válido.";
    }

    // Validar fecha de nacimiento
    if (empty($fecha_de_nacimiento)) {
        $errors[] = "El campo de fecha de nacimiento es obligatorio.";
    } else {
        $birthDate = DateTime::createFromFormat('Y-m-d', $fecha_de_nacimiento);
        $today = new DateTime();
        if (!$birthDate || $birthDate >= $today) {
            $errors[] = "La fecha de nacimiento debe ser una fecha pasada válida.";
        }
    }

    // Validar estatura
    if (empty($estatura)) {
        $errors[] = "El campo de estatura es obligatorio.";
    } elseif (!preg_match("/^\d{1,3}(\.\d{1,2})?$/", $estatura)) {
        $errors[] = "La estatura debe ser un número válido (por ejemplo, 1.75).";
    }

    // Validar peso
    if (empty($peso)) {
        $errors[] = "El campo de peso es obligatorio.";
    } elseif (!preg_match("/^\d{1,3}(\.\d{1,2})?$/", $peso)) {
        $errors[] = "El peso debe ser un número válido (por ejemplo, 70.5).";
    }

    // Validar condición especial
    if (empty($condicion_especial)) {
        $errors[] = "El campo de condición especial es obligatorio.";
    } elseif (!preg_match("/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]*$/", $condicion_especial)) {
        $errors[] = "La condición especial solo debe contener letras y espacios.";
    }

    // Validar género
    $generos_validos = ['Femenino', 'Masculino', 'Otro'];
    if (empty($genero)) {
        $errors[] = "Por favor selecciona una opción de género.";
    } elseif (!in_array($genero, $generos_validos)) {
        $errors[] = "Género no válido seleccionado.";
    }

    // Verificar si hay errores
    if (!empty($errors)) {
        echo implode("\n", $errors);
        exit;
    }

    // Actualizar los datos en la base de datos
    $update_query = "UPDATE usuario 
                     SET nombre = :nombre, email = :email, fecha_de_nacimiento = :fecha_de_nacimiento, 
                         estatura = :estatura, peso = :peso, condicion_especial = :condicion_especial, 
                         genero = :genero 
                     WHERE id = :id";

    $stmt = $conexion->prepare($update_query);
    $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
    $stmt->bindParam(':email', $email, PDO::PARAM_STR);
    $stmt->bindParam(':fecha_de_nacimiento', $fecha_de_nacimiento, PDO::PARAM_STR);
    $stmt->bindParam(':estatura', $estatura, PDO::PARAM_STR);
    $stmt->bindParam(':peso', $peso, PDO::PARAM_STR);
    $stmt->bindParam(':condicion_especial', $condicion_especial, PDO::PARAM_STR);
    $stmt->bindParam(':genero', $genero, PDO::PARAM_STR);
    $stmt->bindParam(':id', $usuario_id, PDO::PARAM_INT);

    if ($stmt->execute()) {
        echo "Datos actualizados con éxito";
    } else {
        echo "Error al actualizar los datos.";
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
                <form id="userProfileForm" novalidate>
                    <div class="item-form">
                        <label for="name">Nombre</label>
                        <input type="text" id="name" name="nombre" value="<?php echo htmlspecialchars($user['nombre']); ?>" required>  
                    </div>
                    <div class="item-form">
                        <label for="email">Correo</label>
                        <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($user['email']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="date">Fecha de nacimiento</label>
                        <input type="date" id="date" name="fecha_de_nacimiento" value="<?php echo htmlspecialchars($user['fecha_de_nacimiento']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="stature">Estatura</label>
                        <input type="text" id="stature" name="estatura" value="<?php echo htmlspecialchars($user['estatura']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="weight">Peso</label>
                        <input type="text" id="weight" name="peso" value="<?php echo htmlspecialchars($user['peso']); ?>" required>
                    </div>
                    <div class="item-form">
                        <label for="special-condition">Condición especial</label>
                        <input type="text" id="special-condition" name="condicion_especial" value="<?php echo htmlspecialchars($user['condicion_especial']); ?>" required>
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
