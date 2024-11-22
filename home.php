<?php 
// Incluir el archivo de conexión a la base de datos
require 'conexion.php';
// Iniciar la sesión para poder acceder a la información del usuario
session_start();

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario_id'])) {
    // Si no está logueado, redirigirlo a la página de login
    header("Location: login.php");
    exit(); // Detener la ejecución si no está logueado
}

// Obtener el ID del usuario que ha iniciado sesión
$usuario_id = $_SESSION['usuario_id'];

try {
    // Consultar la fecha de registro del usuario en la base de datos
    $stmt = $conexion->prepare("SELECT fecha_de_registro FROM usuario WHERE ID = :id");
    $stmt->bindParam(':id', $usuario_id, PDO::PARAM_INT); // Asociar el parámetro del usuario
    $stmt->execute(); // Ejecutar la consulta
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC); // Obtener los datos del usuario

    // Verificar que se ha encontrado el usuario
    if ($usuario) {
        // Convertir la fecha de registro del usuario a un objeto DateTime
        $fecha_de_registro = new DateTime($usuario['fecha_de_registro']);
        // Obtener la fecha y hora actuales
        $fecha_actual = new DateTime();

        // Calcular la "racha" de días que lleva el usuario desde su registro
        $racha_dias = $fecha_actual->diff($fecha_de_registro)->days;

        // Consultar el ID del plan de entrenamiento del usuario
        $stmt_plan = $conexion->prepare("SELECT id_plan FROM usuario WHERE ID = :id");
        $stmt_plan->bindParam(':id', $usuario_id, PDO::PARAM_INT); // Asociar el parámetro del usuario
        $stmt_plan->execute(); // Ejecutar la consulta
        $plan = $stmt_plan->fetch(PDO::FETCH_ASSOC); // Obtener el plan de entrenamiento

        // Verificar que se ha encontrado un plan de entrenamiento
        if ($plan) {
            // Calcular el día del circuito en función de la racha de días (ciclo de 5 días)
            $dia_circuito = $racha_dias % 5 + 1; // Ciclo de 1 a 5 (Día 1 a Día 5)

            // Consultar los ejercicios del circuito correspondiente a ese día
            $stmt_ejercicios = $conexion->prepare("
                SELECT r.nombre AS rutina_nombre, ce.series, e.nombre AS ejercicio_nombre, e.visual 
                FROM rutinas r
                JOIN rutinas_circuitos rc ON r.ID = rc.rutina_id
                JOIN circuitos c ON rc.circuito_id = c.ID
                JOIN circuitos_ejercicios ce ON c.ID = ce.circuito_id
                JOIN ejercicios e ON ce.ejercicio_id = e.ID
                WHERE r.ID = :plan_id AND c.ID = :dia_circuito 
                LIMIT 4
            ");
            $stmt_ejercicios->bindParam(':plan_id', $plan['id_plan'], PDO::PARAM_INT); // Asociar el ID del plan
            $stmt_ejercicios->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT); // Asociar el día del circuito
            $stmt_ejercicios->execute(); // Ejecutar la consulta
            $ejercicios = $stmt_ejercicios->fetchAll(PDO::FETCH_ASSOC); // Obtener los ejercicios

            // Consultar el nombre del circuito correspondiente a ese día
            $stmt_circuito = $conexion->prepare("SELECT nombre AS circuito_nombre FROM circuitos WHERE ID = :dia_circuito");
            $stmt_circuito->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT); // Asociar el día del circuito
            $stmt_circuito->execute(); // Ejecutar la consulta
            $circuito = $stmt_circuito->fetch(PDO::FETCH_ASSOC); // Obtener el nombre del circuito
        } else {
            // Si no se encuentra el plan de entrenamiento, mostrar un error
            echo "Error: Plan no encontrado.";
            exit();
        }
    } else {
        // Si no se encuentra el usuario, mostrar un error
        echo "Error: Usuario no encontrado.";
        exit();
    }
} catch (PDOException $e) {
    // Capturar cualquier error relacionado con la base de datos
    echo "Error en la base de datos: " . $e->getMessage();
    exit();
}
?>

<!-- HTML para la página -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Enlazar los archivos CSS para los estilos de la página -->
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Home</title>
</head>
<body>
    <!-- Encabezado de la página -->
    <header>
        <div class="encabezado container">
            <div class="logo">
                <img src="assets/image/logoGiGa.svg" alt="GigaGains">
                <p>GigaGains</p>
            </div>
            <div class="enmedio">
                <a href="home.php" class="enmediose-btn">Inicio</a>
                <a href="personalizar.php" class="enmedio-btn">Personalizar</a>
                <a href="dieta.php" class="enmedio-btn">Dieta</a>
            </div>
            <a href="profile.php" class="profile-btn">
                <img src="assets/image/profile.svg" width="90" height="70" alt="Perfil">
            </a>
        </div>
    </header>
    
    <!-- Sección principal donde se muestra la información del usuario -->
    <section class="sectionuno">
        <div class="container">
            <div class="sectionuno-uno">
                <!-- Mostrar la racha de días que lleva el usuario -->
                <h1>Bienvenido, llevas una racha de <?php echo $racha_dias; ?> días</h1>
                <h2>
                    <?php
                    // Si se ha encontrado un circuito, mostrar el nombre del circuito
                    if ($circuito) {
                        echo htmlspecialchars($circuito['circuito_nombre']) . ': ';
                    }
                    ?>
                </h2>
            </div>
            <div class="sectionuno-img">
                <?php
                // Mostrar las imágenes de los ejercicios correspondientes al circuito
                foreach ($ejercicios as $ejercicio) {
                    echo '<div class="imageneswiwi">';
                    echo '<img src="' . htmlspecialchars($ejercicio['visual']) . '" class="img" alt="' . htmlspecialchars($ejercicio['ejercicio_nombre']) . '">';
                    echo '</div>';
                }
                ?>
            </div>
            <div>
                <!-- Botón para iniciar la rutina de calentamiento -->
                <button class="start-btn" type="button" onclick="location.href='calentamiento.html'">COMENZAR AHORA</button>
            </div>
        </div>
    </section>
    
    <!-- Formulario para cerrar sesión -->
    <form action="logout.php" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>

</body>
</html>
