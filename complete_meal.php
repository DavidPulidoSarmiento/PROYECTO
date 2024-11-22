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
$stmt = $conexion->prepare("SELECT last_completed FROM meal_completions WHERE usuario_id = :usuario_id AND meal = :meal");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmt->bindParam(':meal', $meal, PDO::PARAM_STR);
$stmt->execute();
$last_completed = null;

if ($stmt->rowCount() > 0) {
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    $last_completed = new DateTime($row['last_completed']);
} else {
    // Si no existe un registro, crear uno
    $stmt_insert = $conexion->prepare("INSERT INTO meal_completions (usuario_id, meal, last_completed) VALUES (:usuario_id, :meal, NOW())");
    $stmt_insert->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
    $stmt_insert->bindParam(':meal', $meal, PDO::PARAM_STR);
    $stmt_insert->execute();
    echo json_encode(['success' => true, 'message' => 'Comida marcada como completada.']);
    exit();
}

$current_time = new DateTime();
$interval = $current_time->diff($last_completed);
$hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);

if ($hours_passed >= 24) {
    // Actualizar la hora de la última completación
    $stmt_update = $conexion->prepare("UPDATE meal_completions SET last_completed = NOW() WHERE usuario_id = :usuario_id AND meal = :meal");
    $stmt_update->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
    $stmt_update->bindParam(':meal', $meal, PDO::PARAM_STR);
    $stmt_update->execute();
    echo json_encode(['success' => true, 'message' => 'Comida marcada como completada.']);
} else {
    echo json_encode(['success' => false, 'message' => 'No puedes completar esta comida aún.']);
}
?>
