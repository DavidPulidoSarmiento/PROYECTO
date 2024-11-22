<?php 
// Iniciar la sesión para poder manejar los datos del usuario en la sesión
require 'conexion.php'; // Incluir el archivo de conexión a la base de datos
session_start(); // Iniciar la sesión PHP

// Verificar si el usuario ya está logueado
if (!isset($_SESSION['usuario_id'])) {
    // Si no está logueado, redirigirlo a la página de inicio de sesión
    header("Location: login.php");
    exit(); // Detener la ejecución del script después de la redirección
}

// Obtener el ID del usuario desde la sesión (esto se hace cuando el usuario está logueado)
$usuario_id = $_SESSION['usuario_id'];

// Consultar el ID del plan asociado al usuario actual
$query_plan = "SELECT id_plan FROM usuario WHERE ID = :usuario_id";
$stmt = $conexion->prepare($query_plan);
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT); // Vincular el ID de usuario en la consulta
$stmt->execute(); // Ejecutar la consulta
$plan = $stmt->fetch(PDO::FETCH_ASSOC); // Obtener el resultado como un arreglo asociativo
$id_plan = $plan['id_plan']; // Guardar el ID del plan actual del usuario

// Consultar la rutina y la dieta asociadas al plan del usuario
$query_detalles_plan = "SELECT rutina_id, dieta_id FROM plan WHERE ID = :id_plan";
$stmt = $conexion->prepare($query_detalles_plan);
$stmt->bindParam(':id_plan', $id_plan, PDO::PARAM_INT); // Vincular el ID del plan
$stmt->execute(); // Ejecutar la consulta
$detalles_plan = $stmt->fetch(PDO::FETCH_ASSOC); // Obtener el resultado
$rutina_id = $detalles_plan['rutina_id']; // ID de la rutina asociada al plan
$dieta_id = $detalles_plan['dieta_id']; // ID de la dieta asociada al plan

// Consultar las primeras 3 rutinas disponibles en la base de datos
$query_rutinas = "SELECT ID, nombre FROM rutinas LIMIT 3";
$stmt_rutinas = $conexion->query($query_rutinas); // Ejecutar la consulta sin parámetros, ya que no es necesario

// Consultar las primeras 3 dietas disponibles en la base de datos
$query_dietas = "SELECT ID, tipo FROM dietas LIMIT 3";
$stmt_dietas = $conexion->query($query_dietas); // Ejecutar la consulta para las dietas

// Procesar el formulario cuando se envía
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Verificar que tanto la rutina como la dieta han sido seleccionadas en el formulario
    if (isset($_POST['rutina']) && isset($_POST['dieta'])) {
        // Obtener los nuevos valores seleccionados por el usuario
        $nueva_rutina_id = $_POST['rutina'];
        $nueva_dieta_id = $_POST['dieta'];

        // Consultar si ya existe un plan con la combinación seleccionada de rutina y dieta
        $query_plan_combinado = "SELECT ID FROM plan WHERE rutina_id = :nueva_rutina_id AND dieta_id = :nueva_dieta_id";
        $stmt = $conexion->prepare($query_plan_combinado);
        $stmt->bindParam(':nueva_rutina_id', $nueva_rutina_id, PDO::PARAM_INT); // Vincular el ID de la nueva rutina
        $stmt->bindParam(':nueva_dieta_id', $nueva_dieta_id, PDO::PARAM_INT); // Vincular el ID de la nueva dieta
        $stmt->execute(); // Ejecutar la consulta
        
        // Si se encuentra un plan con esta combinación de rutina y dieta
        if ($stmt->rowCount() > 0) {
            // Obtener el ID del plan combinado
            $plan_combinado = $stmt->fetch(PDO::FETCH_ASSOC);
            $nuevo_id_plan = $plan_combinado['ID'];

            // Actualizar el plan del usuario con el nuevo plan seleccionado
            $update_plan_usuario = "UPDATE usuario SET id_plan = :nuevo_id_plan WHERE ID = :usuario_id";
            $stmt = $conexion->prepare($update_plan_usuario);
            $stmt->bindParam(':nuevo_id_plan', $nuevo_id_plan, PDO::PARAM_INT); // Vincular el nuevo ID del plan
            $stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT); // Vincular el ID del usuario
            $stmt->execute(); // Ejecutar la actualización
        }
    }

    // Después de procesar el formulario, redirigir a la misma página para evitar el reenvío del formulario
    header("Location: personalizar.php");
    exit(); // Detener la ejecución después de la redirección
}
?>

<!-- Página HTML con el formulario para personalizar el plan del usuario -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Enlace a los archivos CSS para los estilos -->
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/personalizar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Personalizar</title>
</head>
<body>
    <!-- Header de la página, con el logo y enlaces de navegación -->
    <header>
        <div class="encabezado container">
            <div class="logo">
                <img src="assets/image/logoGiGa.svg" alt="GigaGains">
                <p>GigaGains</p>
            </div>
            <div class="enmedio">
                <a href="home.php" class="enmedio-btn">Inicio</a>
                <a href="personalizar.php" class="enmediose-btn">Personalizar</a>
                <a href="dieta.php" class="enmedio-btn">Dieta</a>
            </div>
            <a href="profile.php" class="profile-btn"><img src="assets/image/profile.svg" width="90" height="70"></a>
        </div>
    </header>

    <!-- Sección principal de la página -->
    <section>
        <div class="container">
            <div class="plan">
                <h1>Personaliza tu entrenamiento</h1>
                <h3>Cambia tu objetivo</h3>
                <!-- Formulario que permite al usuario seleccionar una nueva rutina y dieta -->
                <form method="POST">
                    <div class="checksentrenamiento">
                        <?php 
                        // Mostrar las rutinas disponibles (las primeras 3)
                        if ($stmt_rutinas->rowCount() > 0):
                            while ($rutina = $stmt_rutinas->fetch(PDO::FETCH_ASSOC)):
                        ?>
                                <label>
                                    <!-- Radio button para cada rutina -->
                                    <input class="radio" type="radio" name="rutina" value="<?php echo htmlspecialchars($rutina['ID']); ?>" <?php echo ($rutina['ID'] == $rutina_id) ? 'checked' : ''; ?> /> <?php echo htmlspecialchars($rutina['nombre']); ?>
                                </label>
                        <?php 
                            endwhile; 
                        else:
                        ?>
                            <p>No hay rutinas disponibles.</p>
                        <?php endif; ?>
                    </div>

                    <h1>Personaliza tu dieta</h1>
                    <h3>Cambia tu objetivo</h3>
                    <div class="checksentrenamiento">
                        <?php 
                        // Mostrar las dietas disponibles (las primeras 3)
                        if ($stmt_dietas->rowCount() > 0):
                            while ($dieta = $stmt_dietas->fetch(PDO::FETCH_ASSOC)):
                        ?>
                                <label>
                                    <!-- Radio button para cada dieta -->
                                    <input class="radio" type="radio" name="dieta" value="<?php echo htmlspecialchars($dieta['ID']); ?>" <?php echo ($dieta['ID'] == $dieta_id) ? 'checked' : ''; ?> /> <?php echo htmlspecialchars($dieta['tipo']); ?>
                                </label>
                        <?php 
                            endwhile; 
                        else:
                        ?>
                            <p>No hay dietas disponibles.</p>
                        <?php endif; ?>
                    </div>

                    <!-- Botón para enviar el formulario y confirmar la selección -->
                    <button class="btn" type="submit">ACEPTAR</button>
                </form>
            </div>
        </div>
    </section>
    
</body>
</html>
