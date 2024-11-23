<?php
// Iniciar la sesión para poder acceder a la información del usuario logueado
session_start();

// Incluir el archivo de conexión con la base de datos (conexion.php) que utiliza PDO
include('conexion.php');

// Verificar si el usuario está logueado, si no está, redirigirlo a la página de login
if (!isset($_SESSION['usuario_id'])) {
  header("Location: login.php"); // Redirige al usuario a la página de login si no está autenticado
  exit(); // Detener la ejecución del código después de la redirección
}

// Obtener el ID del usuario desde la sesión, que es necesario para recuperar su información
$usuario_id = $_SESSION['usuario_id'];

try {
  // Obtener la fecha de registro del usuario y el ID del plan asociado a su cuenta
  $stmt = $conexion->prepare("SELECT fecha_de_registro, id_plan FROM usuario WHERE ID = :usuario_id");
  $stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT); // Vincula el parámetro usuario_id a la consulta
  $stmt->execute(); // Ejecuta la consulta para obtener los datos del usuario
  $usuario = $stmt->fetch(PDO::FETCH_ASSOC); // Recupera los datos del usuario en forma de un arreglo asociativo

  // Verificar si el usuario existe
  if ($usuario) {
    // Si el usuario existe, obtener la fecha de registro y calcular el número de días desde esa fecha
    $fecha_de_registro = new DateTime($usuario['fecha_de_registro']); // Convertir la fecha de registro a un objeto DateTime
    $fecha_actual = new DateTime(); // Obtener la fecha y hora actual
    $racha_dias = $fecha_actual->diff($fecha_de_registro)->days; // Calcular la diferencia en días entre la fecha actual y la fecha de registro

    // Obtener el ID del plan que tiene el usuario
    $id_plan = $usuario['id_plan'];

    // Calcular el "día del circuito" basado en la racha de días del usuario
    // La fórmula calcula el día del circuito que le toca al usuario (se repite cada 5 días)
    $dia_circuito = $racha_dias % 5 + 1; // Si la racha de días es, por ejemplo, 7, $dia_circuito será 3 (porque 7 % 5 = 2, y sumamos 1)

    // Obtener los ejercicios que corresponden al circuito del día calculado
    $stmt = $conexion->prepare("
            SELECT ce.ejercicio_id, e.nombre, ce.series, e.visual
            FROM circuitos c
            JOIN rutinas_circuitos rc ON c.ID = rc.circuito_id
            JOIN circuitos_ejercicios ce ON c.ID = ce.circuito_id
            JOIN ejercicios e ON ce.ejercicio_id = e.ID
            WHERE rc.rutina_id = :id_plan AND c.ID = :dia_circuito
        ");
    $stmt->bindParam(':id_plan', $id_plan, PDO::PARAM_INT); // Vincular el ID del plan del usuario
    $stmt->bindParam(':dia_circuito', $dia_circuito, PDO::PARAM_INT); // Vincular el día del circuito (1-5)
    $stmt->execute(); // Ejecutar la consulta para obtener los ejercicios del circuito
    $ejercicios = $stmt->fetchAll(PDO::FETCH_ASSOC); // Recuperar todos los ejercicios del circuito en un arreglo

    // Manejar el ejercicio actual
    // Si no se ha establecido un ejercicio actual en la sesión, iniciar en 0
    if (!isset($_SESSION['ejercicio_actual'])) {
      $_SESSION['ejercicio_actual'] = 0; // Empieza con el primer ejercicio (índice 0)
    }

    // Obtener el índice del ejercicio actual
    $ejercicio_actual_idx = $_SESSION['ejercicio_actual'];

    // Verificar si el índice del ejercicio actual es válido (no se ha completado toda la rutina)
    if ($ejercicio_actual_idx < count($ejercicios)) {
      // Si hay ejercicios restantes, obtener el ejercicio correspondiente al índice actual
      $ejercicio_actual = $ejercicios[$ejercicio_actual_idx];
    } else {
      // Si ya no hay ejercicios, redirigir al usuario a una página de "fin de rutina"
      header("Location: finrutina.html");
      exit(); // Detener la ejecución después de la redirección
    }
  } else {
    // Si no se encuentra al usuario, mostrar un mensaje de error y detener la ejecución
    echo "Error: Usuario no encontrado.";
    exit(); // Detener la ejecución del código
  }
} catch (PDOException $e) {
  // Si ocurre un error en la consulta o en la base de datos, capturarlo y mostrar el mensaje de error
  echo "Error: " . $e->getMessage();
  exit(); // Detener la ejecución si ocurre un error
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
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap" />
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
}
exit();
?>