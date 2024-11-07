<?php
// update_routine.php
header('Content-Type: application/json');
session_start();
require 'conexion.php';

// Verificar si la solicitud es AJAX y es POST
if ($_SERVER['REQUEST_METHOD'] !== 'POST' || 
    !isset($_SERVER['HTTP_X_REQUESTED_WITH']) || 
    strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) !== 'xmlhttprequest') {
    echo json_encode(['success' => false, 'message' => 'Solicitud inválida.']);
    exit();
}

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario_id'])) {
    echo json_encode(['success' => false, 'message' => 'No has iniciado sesión.']);
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

// Obtener el ID del plan del usuario
$sql_usuario = "SELECT id_plan FROM usuario WHERE ID = ?";
$stmt = $conexion->prepare($sql_usuario);
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$result = $stmt->get_result();
$user_plan = $result->fetch_assoc();

if (!$user_plan || !isset($user_plan['id_plan'])) {
    echo json_encode(['success' => false, 'message' => 'Datos de usuario no encontrados o incompletos.']);
    exit();
}

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

if (count($circuitos) == 0) {
    echo json_encode(['success' => false, 'message' => 'No hay circuitos asociados a tu plan.']);
    exit();
}

// Supongamos que solo hay un circuito por plan
$circuito_id = $circuitos[0]['ID'];

// Obtener ejercicios del circuito ordenados por ce.ID ASC
$sql_ejercicios = "SELECT ce.ejercicio_id, e.nombre, ce.series, e.visual
                   FROM circuitos_ejercicios ce
                   JOIN ejercicios e ON e.ID = ce.ejercicio_id
                   WHERE ce.circuito_id = ?
                   ORDER BY ce.ID ASC";
$stmt = $conexion->prepare($sql_ejercicios);
$stmt->bind_param("i", $circuito_id);
$stmt->execute();
$result = $stmt->get_result();
$ejercicios = [];
while ($row = $result->fetch_assoc()) {
    $ejercicios[] = $row;
}

if (count($ejercicios) == 0) {
    echo json_encode(['success' => false, 'message' => 'No hay ejercicios en este circuito.']);
    exit();
}

// Obtener el progreso del usuario
$sql_progress = "SELECT current_exercise_index, last_routine_date FROM user_routine_progress WHERE usuario_id = ?";
$stmt = $conexion->prepare($sql_progress);
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$result_progress = $stmt->get_result();

if ($result_progress->num_rows > 0) {
    $progress = $result_progress->fetch_assoc();
    $current_exercise_index = $progress['current_exercise_index'];
    $last_routine_date = $progress['last_routine_date'];
    
    $can_start_new_routine = true;
    if ($last_routine_date) {
        $last_routine_datetime = new DateTime($last_routine_date);
        $current_datetime = new DateTime();
        $interval = $current_datetime->diff($last_routine_datetime);
        $hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);
        
        if ($hours_passed < 24) {
            $can_start_new_routine = false;
        }
    }
    
    if ($can_start_new_routine) {
        // Reset progress
        $current_exercise_index = 0;
        $last_routine_date = null;
        $sql_reset = "UPDATE user_routine_progress SET current_exercise_index = 0, last_routine_date = NULL WHERE usuario_id = ?";
        $stmt_reset = $conexion->prepare($sql_reset);
        $stmt_reset->bind_param("i", $usuario_id);
        $stmt_reset->execute();
    }
} else {
    // Crear una entrada para el usuario
    $current_exercise_index = 0;
    $last_routine_date = null;
    $sql_insert_progress = "INSERT INTO user_routine_progress (usuario_id, current_exercise_index, last_routine_date) VALUES (?, 0, NULL)";
    $stmt_insert = $conexion->prepare($sql_insert_progress);
    $stmt_insert->bind_param("i", $usuario_id);
    $stmt_insert->execute();
}

// Incrementar el índice del ejercicio
$current_exercise_index++;

// Verificar si hay más ejercicios
if ($current_exercise_index >= count($ejercicios)) {
    // Rutina completada, actualizar la fecha de completación
    $current_datetime = new DateTime();
    $current_datetime_str = $current_datetime->format('Y-m-d H:i:s');
    
    $sql_complete_routine = "UPDATE user_routine_progress SET current_exercise_index = 0, last_routine_date = ? WHERE usuario_id = ?";
    $stmt_complete = $conexion->prepare($sql_complete_routine);
    $stmt_complete->bind_param("si", $current_datetime_str, $usuario_id);
    $stmt_complete->execute();
    
    // Indicar que la rutina ha sido completada y redirigir
    echo json_encode(['success' => true, 'redirect' => 'finrutina.html']);
    exit();
} else {
    // Actualizar el índice del ejercicio actual
    $sql_update_progress = "UPDATE user_routine_progress SET current_exercise_index = ? WHERE usuario_id = ?";
    $stmt_update = $conexion->prepare($sql_update_progress);
    $stmt_update->bind_param("ii", $current_exercise_index, $usuario_id);
    $stmt_update->execute();
    
    // Indicar que el ejercicio ha sido actualizado
    echo json_encode(['success' => true]);
    exit();
}
?>
