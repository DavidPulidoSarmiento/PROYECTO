<?php 
require 'conexion.php';
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

// Obtener el ID del plan del usuario
$usuario_id = $_SESSION['usuario_id'];
$sql_usuario = "SELECT id_plan FROM usuario WHERE ID = ?";
$stmt = $conexion->prepare($sql_usuario);
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$result = $stmt->get_result();
$user_plan = $result->fetch_assoc();
$id_plan = $user_plan['id_plan'];

// Obtener los circuitos asociados al plan
$sql_circuitos = "SELECT c.ID, c.nombre FROM circuitos c
                  JOIN rutinas_circuitos rc ON c.ID = rc.circuito_id
                  JOIN plan p ON p.rutina_id = rc.rutina_id
                  WHERE p.ID = ?";
$stmt = $conexion->prepare($sql_circuitos);
$stmt->bind_param("i", $id_plan);
$stmt->execute();
$result = $stmt->get_result();
$circuitos = [];
while ($row = $result->fetch_assoc()) {
    $circuitos[] = $row;
}

// Mostrar el circuito y los ejercicios del día
$circuito_id = $circuitos[0]['ID'];  // Suponiendo que solo existe un circuito para este plan

$sql_ejercicios = "SELECT ce.ejercicio_id, e.nombre, ce.series, e.visual
                   FROM circuitos_ejercicios ce
                   JOIN ejercicios e ON e.ID = ce.ejercicio_id
                   WHERE ce.circuito_id = ?";
$stmt = $conexion->prepare($sql_ejercicios);
$stmt->bind_param("i", $circuito_id);
$stmt->execute();
$result = $stmt->get_result();
$ejercicios = [];
while ($row = $result->fetch_assoc()) {
    $ejercicios[] = $row;
}

// Ejercicio actual (puedes ajustarlo según la lógica que necesites)
$ejercicio_actual = $ejercicios[0]; // Para pruebas
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/traing.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>RUTINA</title>
</head>
<body>
    <header>
        <a href="home.php">SALIR</a>
    </header>
    <div class="container">
        <div class="titulo">
            <h1 class="title">Ejercicio</h1><h1 class="title-ejercicio">#<?php echo $ejercicio_actual['ejercicio_id']; ?></h1>
        </div>
        <div class="contenido">
            <div class="ejercicio">
                <img src="<?php echo $ejercicio_actual['visual']; ?>" alt="gif">
            </div>
            <div class="datos">
                <h1><?php echo $ejercicio_actual['nombre']; ?></h1>
                <h1 class="cantidad"><?php echo $ejercicio_actual['series']; ?></h1>
                <!-- El botón 'Comenzar' ya no se usa, porque eliminamos el temporizador -->
                <h1>Descanso 3min</h1> <!-- Mensaje de descanso fijo -->
            </div>
        </div>
        <div class="siguiente">
            <a href="finrutina.html" id="siguiente">SIGUIENTE</a>
        </div>
    </div>

    <!-- Pasamos los ejercicios a JS para que se carguen dinámicamente -->
    <script>
        const ejercicios = <?php echo json_encode($ejercicios); ?>;  // Convertimos los ejercicios en un objeto JavaScript
        let ejercicioActualIndex = 0;  // Índice del ejercicio actual
    </script>
    <script src="assets/js/rutina.js"></script>
</body>
</html>
