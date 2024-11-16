<?php
session_start();
include('conexion.php');

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

// Obtener fecha de registro para calcular el circuito del día
$stmt = $conexion->prepare("SELECT fecha_de_registro, id_plan FROM usuario WHERE ID = ?");
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$resultado = $stmt->get_result();

if ($resultado->num_rows === 1) {
    $usuario = $resultado->fetch_assoc();
    $fecha_de_registro = new DateTime($usuario['fecha_de_registro']);
    $fecha_actual = new DateTime();
    $racha_dias = $fecha_actual->diff($fecha_de_registro)->days;
    $id_plan = $usuario['id_plan'];
    
    $dia_circuito = $racha_dias % 5 + 1; // Circuito basado en racha de días

    // Obtener ejercicios del circuito
    $stmt = $conexion->prepare("
        SELECT ce.ejercicio_id, e.nombre, ce.series, e.visual
        FROM circuitos c
        JOIN rutinas_circuitos rc ON c.ID = rc.circuito_id
        JOIN circuitos_ejercicios ce ON c.ID = ce.circuito_id
        JOIN ejercicios e ON ce.ejercicio_id = e.ID
        WHERE rc.rutina_id = ? AND c.ID = ?
    ");
    $stmt->bind_param("ii", $id_plan, $dia_circuito);
    $stmt->execute();
    $ejercicios = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);

    // Manejar el ejercicio actual
    if (!isset($_SESSION['ejercicio_actual'])) {
        $_SESSION['ejercicio_actual'] = 0;
    }
    $ejercicio_actual_idx = $_SESSION['ejercicio_actual'];

    if ($ejercicio_actual_idx < count($ejercicios)) {
        $ejercicio_actual = $ejercicios[$ejercicio_actual_idx];
    } else {
        // Redirigir al finalizar todos los ejercicios
        header("Location: finrutina.html");
        exit();
    }
} else {
    echo "Error: Usuario no encontrado.";
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTINA</title>
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/traing.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
</head>
<body>
    <header>
        <a href="home.php">SALIR</a>
    </header>
    <div class="container">
        <div class="titulo">
            <h1 class="title">Ejercicio</h1>
            <h1 class="title-ejercicio">#<span id="ejercicio-id"><?php echo htmlspecialchars($ejercicio_actual['ejercicio_id']); ?></span></h1>
        </div>
        <div class="contenido">
            <div class="ejercicio">
                <img id="ejercicio-visual" src="<?php echo htmlspecialchars($ejercicio_actual['visual']); ?>" alt="gif">
            </div>
            <div class="datos">
                <h1 id="ejercicio-nombre"><?php echo htmlspecialchars($ejercicio_actual['nombre']); ?></h1>
                <h1 class="cantidad">Series: <span id="ejercicio-series"><?php echo htmlspecialchars($ejercicio_actual['series']); ?></span></h1>
                <h1>Descanso 3min</h1>
            </div>
        </div>
        <div class="siguiente">
            <form method="post" action="rutina.php">
                <button class="btn" type="submit" name="siguiente">SIGUIENTE</button>
            </form>
        </div>
    </div>
</body>
</html>

<?php
// Avanzar al siguiente ejercicio al presionar "SIGUIENTE"
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['siguiente'])) {
    $_SESSION['ejercicio_actual']++;
    header("Location: rutina.php");
    exit();
}
?>
