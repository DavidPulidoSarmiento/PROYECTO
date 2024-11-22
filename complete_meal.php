<?php
// complete_meal.php

// Establece el tipo de contenido de la respuesta como JSON
header('Content-Type: application/json');

// Inicia la sesión de usuario para poder acceder a la información de sesión
session_start();

// Incluye el archivo que maneja la conexión a la base de datos
require 'conexion.php';

// Verificar si la solicitud es un pedido AJAX (petición hecha con JavaScript)
if ($_SERVER['REQUEST_METHOD'] !== 'POST' || 
    !isset($_SERVER['HTTP_X_REQUESTED_WITH']) || 
    strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) !== 'xmlhttprequest') {
    // Si no es una solicitud AJAX, responde con un mensaje de error
    echo json_encode(['success' => false, 'message' => 'Solicitud inválida.']);
    exit(); // Termina la ejecución del script
}

// Verifica si el usuario ha iniciado sesión
if (!isset($_SESSION['usuario_id'])) {
    // Si no hay un ID de usuario en la sesión, indica que no ha iniciado sesión
    echo json_encode(['success' => false, 'message' => 'No has iniciado sesión.']);
    exit(); // Termina la ejecución del script
}

// Asigna el ID del usuario logueado a la variable $usuario_id
$usuario_id = $_SESSION['usuario_id'];

// Obtiene los datos enviados en la solicitud (en formato JSON)
$data = json_decode(file_get_contents('php://input'), true);

// Verifica si el parámetro 'meal' ha sido enviado en la solicitud
if (!isset($data['meal'])) {
    // Si no se especifica el tipo de comida, responde con un mensaje de error
    echo json_encode(['success' => false, 'message' => 'Comida no especificada.']);
    exit(); // Termina la ejecución del script
}

// Asigna el valor de 'meal' (comida) a la variable $meal
$meal = $data['meal'];

// Lista de tipos de comida válidos
$valid_meals = ['desayuno', 'almuerzo', 'cena'];

// Verifica si el valor de 'meal' es uno de los tipos válidos
if (!in_array($meal, $valid_meals)) {
    // Si el tipo de comida no es válido, responde con un mensaje de error
    echo json_encode(['success' => false, 'message' => 'Tipo de comida inválido.']);
    exit(); // Termina la ejecución del script
}

// Consulta la base de datos para obtener la última vez que el usuario completó la comida solicitada
$stmt = $conexion->prepare("SELECT last_completed FROM meal_completions WHERE usuario_id = :usuario_id AND meal = :meal");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmt->bindParam(':meal', $meal, PDO::PARAM_STR);
$stmt->execute(); // Ejecuta la consulta SQL

// Variable para almacenar la fecha de la última comida completada
$last_completed = null;

// Si el usuario tiene un registro de la comida solicitada
if ($stmt->rowCount() > 0) {
    // Obtiene el registro de la base de datos y lo guarda como un objeto DateTime
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    $last_completed = new DateTime($row['last_completed']);
} else {
    // Si no existe un registro de la comida, crea uno nuevo en la base de datos
    $stmt_insert = $conexion->prepare("INSERT INTO meal_completions (usuario_id, meal, last_completed) VALUES (:usuario_id, :meal, NOW())");
    $stmt_insert->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
    $stmt_insert->bindParam(':meal', $meal, PDO::PARAM_STR);
    $stmt_insert->execute(); // Ejecuta la inserción en la base de datos

    // Responde con un mensaje indicando que la comida ha sido marcada como completada
    echo json_encode(['success' => true, 'message' => 'Comida marcada como completada.']);
    exit(); // Termina la ejecución del script
}

// Si existe un registro, calcula el tiempo transcurrido desde la última comida completada
$current_time = new DateTime(); // Hora actual
$interval = $current_time->diff($last_completed); // Calcula la diferencia entre la hora actual y la última comida completada

// Convierte la diferencia de tiempo a horas
$hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);

// Si han pasado al menos 24 horas desde la última comida completada
if ($hours_passed >= 24) {
    // Actualiza el registro de la comida en la base de datos con la nueva hora de completado
    $stmt_update = $conexion->prepare("UPDATE meal_completions SET last_completed = NOW() WHERE usuario_id = :usuario_id AND meal = :meal");
    $stmt_update->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
    $stmt_update->bindParam(':meal', $meal, PDO::PARAM_STR);
    $stmt_update->execute(); // Ejecuta la actualización en la base de datos

    // Responde con un mensaje indicando que la comida ha sido marcada como completada
    echo json_encode(['success' => true, 'message' => 'Comida marcada como completada.']);
} else {
    // Si no han pasado 24 horas, responde con un mensaje de error
    echo json_encode(['success' => false, 'message' => 'No puedes completar esta comida aún.']);
}

?>
