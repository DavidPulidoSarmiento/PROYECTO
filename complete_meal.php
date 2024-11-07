<?php
// complete_meal.php
header('Content-Type: application/json');
session_start();
require 'conexion.php';

// Verificar si la solicitud es AJAX
if ($_SERVER['REQUEST_METHOD'] !== 'POST' || !isset($_SERVER['HTTP_X_REQUESTED_WITH']) || strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) !== 'xmlhttprequest') {
    echo json_encode(['success' => false, 'message' => 'Solicitud inválida.']);
    exit();
}

// Obtener el usuario logueado
if (!isset($_SESSION['usuario_id'])) {
    echo json_encode(['success' => false, 'message' => 'No has iniciado sesión.']);
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

// Obtener los datos enviados
$data = json_decode(file_get_contents('php://input'), true);

if (!isset($data['meal'])) {
    echo json_encode(['success' => false, 'message' => 'Comida no especificada.']);
    exit();
}

$meal = $data['meal'];

// Validar el tipo de comida
$valid_meals = ['desayuno', 'almuerzo', 'cena'];
if (!in_array($meal, $valid_meals)) {
    echo json_encode(['success' => false, 'message' => 'Tipo de comida inválido.']);
    exit();
}

// Obtener la última completación de la comida
$stmt = $conexion->prepare("SELECT last_completed FROM meal_completions WHERE usuario_id = ? AND meal = ?");
$stmt->bind_param("is", $usuario_id, $meal);
$stmt->execute();
$result = $stmt->get_result();
$last_completed = null;

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $last_completed = new DateTime($row['last_completed']);
} else {
    // Si no existe un registro, crear uno
    $stmt_insert = $conexion->prepare("INSERT INTO meal_completions (usuario_id, meal, last_completed) VALUES (?, ?, ?)");
    $current_time = (new DateTime())->format('Y-m-d H:i:s');
    $stmt_insert->bind_param("iss", $usuario_id, $meal, $current_time);
    if ($stmt_insert->execute()) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['success' => false, 'message' => 'Error al registrar la completación.']);
    }
    exit();
}

// Verificar si han pasado 24 horas desde la última completación
$current_time = new DateTime();
$interval = $current_time->diff($last_completed);
$hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);

if ($hours_passed < 24) {
    echo json_encode(['success' => false, 'message' => 'Debes esperar 24 horas para completar nuevamente esta comida.']);
    exit();
}

// Actualizar la fecha de completación
$stmt_update = $conexion->prepare("UPDATE meal_completions SET last_completed = ? WHERE usuario_id = ? AND meal = ?");
$current_time_str = $current_time->format('Y-m-d H:i:s');
$stmt_update->bind_param("sis", $current_time_str, $usuario_id, $meal);
if ($stmt_update->execute()) {
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['success' => false, 'message' => 'Error al actualizar la completación.']);
}
?>
