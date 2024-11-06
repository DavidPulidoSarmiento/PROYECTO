<?php 
require 'conexion.php';
session_start();

// Asegúrate de que el usuario esté logueado
if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

// Obtener la fecha de registro del usuario
$stmt = $conexion->prepare("SELECT fecha_de_registro FROM usuario WHERE ID = ?");
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$resultado = $stmt->get_result();

if ($resultado->num_rows === 1) {
    $usuario = $resultado->fetch_assoc();
    $fecha_de_registro = new DateTime($usuario['fecha_de_registro']);
    $fecha_actual = new DateTime();

    // Calcular la racha de días
    $racha_dias = $fecha_actual->diff($fecha_de_registro)->days;

    // Obtener el ID del plan del usuario
    $stmt = $conexion->prepare("SELECT id_plan FROM usuario WHERE ID = ?");
    $stmt->bind_param("i", $usuario_id);
    $stmt->execute();
    $resultado_plan = $stmt->get_result();
    $plan = $resultado_plan->fetch_assoc();

    // Determinar el circuito correspondiente basado en la racha de días
    $dia_circuito = $racha_dias % 5 + 1; // Ciclo de 1 a 5 (Dia 1 a Dia 5)

    // Obtener la rutina y los ejercicios para el circuito correspondiente
    $stmt = $conexion->prepare("
        SELECT r.nombre AS rutina_nombre, ce.series, e.nombre AS ejercicio_nombre, e.visual 
        FROM rutinas r
        JOIN rutinas_circuitos rc ON r.ID = rc.rutina_id
        JOIN circuitos c ON rc.circuito_id = c.ID
        JOIN circuitos_ejercicios ce ON c.ID = ce.circuito_id
        JOIN ejercicios e ON ce.ejercicio_id = e.ID
        WHERE r.ID = ? AND c.ID = ? 
        LIMIT 4
    ");
    $stmt->bind_param("ii", $plan['id_plan'], $dia_circuito);
    $stmt->execute();
    $resultado_ejercicios = $stmt->get_result();
} else {
    // Manejar el caso en que no se encuentra el usuario
    echo "Error: Usuario no encontrado.";
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
    // Obtener el nombre del circuito
    $stmt = $conexion->prepare("SELECT c.nombre AS circuito_nombre 
                                 FROM circuitos c 
                                 WHERE c.ID = ?");
    $stmt->bind_param("i", $dia_circuito);
    $stmt->execute();
    $resultado_circuito = $stmt->get_result();
    
    if ($resultado_circuito->num_rows === 1) {
        $circuito = $resultado_circuito->fetch_assoc();
        echo $circuito['circuito_nombre'] . ': ';
    }

    
?>
            </h2>
            </div>
            <div class="sectionuno-img">
                <?php
                // Reseteamos el cursor del resultado a la primera fila
                $resultado_ejercicios->data_seek(0);
                $counter = 0;
                while ($ejercicio = $resultado_ejercicios->fetch_assoc()) {
                    if ($counter < 4) { // Limitar a 4 ejercicios
                        echo '<div class="imageneswiwi">';
                        echo '<img src="' . htmlspecialchars($ejercicio['visual']) . '" class="img" alt="' . htmlspecialchars($ejercicio['ejercicio_nombre']) . '">';
                        echo '</div>';
                    }
                    $counter++;
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
