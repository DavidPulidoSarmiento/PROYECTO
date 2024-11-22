<?php 
require 'conexion.php';
session_start();

// Asegúrate de que el usuario esté logueado
if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

try {
    // Obtener la fecha de registro del usuario
    $stmt = $conexion->prepare("SELECT fecha_de_registro FROM usuario WHERE ID = :id");
    $stmt->bindParam(':id', $usuario_id, PDO::PARAM_INT);
    $stmt->execute();
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($usuario) {
        $fecha_de_registro = new DateTime($usuario['fecha_de_registro']);
        $fecha_actual = new DateTime();

        // Calcular la racha de días
        $racha_dias = $fecha_actual->diff($fecha_de_registro)->days;

        // Obtener el ID del plan del usuario
        $stmt_plan = $conexion->prepare("SELECT id_plan FROM usuario WHERE ID = :id");
        $stmt_plan->bindParam(':id', $usuario_id, PDO::PARAM_INT);
        $stmt_plan->execute();
        $plan = $stmt_plan->fetch(PDO::FETCH_ASSOC);

        if ($plan) {
            // Determinar el circuito correspondiente basado en la racha de días
            $dia_circuito = $racha_dias % 5 + 1; // Ciclo de 1 a 5 (Dia 1 a Dia 5)

            // Obtener la rutina y los ejercicios para el circuito correspondiente
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
            $stmt_ejercicios->bindParam(':plan_id', $plan['id_plan'], PDO::PARAM_INT);
            $stmt_ejercicios->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT);
            $stmt_ejercicios->execute();
            $ejercicios = $stmt_ejercicios->fetchAll(PDO::FETCH_ASSOC);

            // Obtener el nombre del circuito
            $stmt_circuito = $conexion->prepare("SELECT nombre AS circuito_nombre FROM circuitos WHERE ID = :dia_circuito");
            $stmt_circuito->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT);
            $stmt_circuito->execute();
            $circuito = $stmt_circuito->fetch(PDO::FETCH_ASSOC);
        } else {
            echo "Error: Plan no encontrado.";
            exit();
        }
    } else {
        echo "Error: Usuario no encontrado.";
        exit();
    }
} catch (PDOException $e) {
    echo "Error en la base de datos: " . $e->getMessage();
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Home</title>
</head>
<body>
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
    
    <section class="sectionuno">
        <div class="container">
            <div class="sectionuno-uno">
                <h1>Bienvenido, llevas una racha de <?php echo $racha_dias; ?> días</h1>
                <h2>
                    <?php
                    if ($circuito) {
                        echo htmlspecialchars($circuito['circuito_nombre']) . ': ';
                    }
                    ?>
                </h2>
            </div>
            <div class="sectionuno-img">
                <?php
                foreach ($ejercicios as $ejercicio) {
                    echo '<div class="imageneswiwi">';
                    echo '<img src="' . htmlspecialchars($ejercicio['visual']) . '" class="img" alt="' . htmlspecialchars($ejercicio['ejercicio_nombre']) . '">';
                    echo '</div>';
                }
                ?>
            </div>
            <div>
                <button class="start-btn" type="button" onclick="location.href='calentamiento.html'">COMENZAR AHORA</button>
            </div>
        </div>
    </section>
    
    <form action="logout.php" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>

</body>
</html>
