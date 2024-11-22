<?php
session_start();
include('conexion.php'); // Incluye la conexión con PDO

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

try {
    // Obtener fecha de registro para calcular el circuito del día
    $stmt = $conexion->prepare("SELECT fecha_de_registro, id_plan FROM usuario WHERE ID = :usuario_id");
    $stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
    $stmt->execute();
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($usuario) {
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
            WHERE rc.rutina_id = :id_plan AND c.ID = :dia_circuito
        ");
        $stmt->bindParam(':id_plan', $id_plan, PDO::PARAM_INT);
        $stmt->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT);
        $stmt->execute();
        $ejercicios = $stmt->fetchAll(PDO::FETCH_ASSOC);

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
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
    exit();
}
?>
