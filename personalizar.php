<?php 
require 'conexion.php';
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

// Obtener el ID del usuario
$usuario_id = $_SESSION['usuario_id'];

// Obtener el ID del plan del usuario
$query_plan = "SELECT id_plan FROM usuario WHERE ID = ?";
$stmt = $conexion->prepare($query_plan);
$stmt->bind_param("i", $usuario_id);
$stmt->execute();
$result_plan = $stmt->get_result();
$plan = $result_plan->fetch_assoc();
$id_plan = $plan['id_plan'];

// Obtener rutina_id y dieta_id del plan actual
$query_detalles_plan = "SELECT rutina_id, dieta_id FROM plan WHERE ID = ?";
$stmt = $conexion->prepare($query_detalles_plan);
$stmt->bind_param("i", $id_plan);
$stmt->execute();
$result_detalles = $stmt->get_result();
$detalles_plan = $result_detalles->fetch_assoc();
$rutina_id = $detalles_plan['rutina_id'];
$dieta_id = $detalles_plan['dieta_id'];

// Obtener rutinas (solo los 3 primeros)
$query_rutinas = "SELECT ID, nombre FROM rutinas LIMIT 3";
$result_rutinas = $conexion->query($query_rutinas);

// Obtener dietas (solo las 3 primeras)
$query_dietas = "SELECT ID, tipo FROM dietas LIMIT 3";
$result_dietas = $conexion->query($query_dietas);

// Procesar el formulario al ser enviado
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['rutina']) && isset($_POST['dieta'])) {
        $nueva_rutina_id = $_POST['rutina'];
        $nueva_dieta_id = $_POST['dieta'];

        // Buscar el plan correspondiente a la combinación seleccionada
        $query_plan_combinado = "SELECT ID FROM plan WHERE rutina_id = ? AND dieta_id = ?";
        $stmt = $conexion->prepare($query_plan_combinado);
        $stmt->bind_param("ii", $nueva_rutina_id, $nueva_dieta_id);
        $stmt->execute();
        $result_plan_combinado = $stmt->get_result();
        
        if ($result_plan_combinado->num_rows > 0) {
            $plan_combinado = $result_plan_combinado->fetch_assoc();
            $nuevo_id_plan = $plan_combinado['ID'];

            // Actualizar el plan del usuario con el nuevo plan seleccionado
            $update_plan_usuario = "UPDATE usuario SET id_plan = ? WHERE ID = ?";
            $stmt = $conexion->prepare($update_plan_usuario);
            $stmt->bind_param("ii", $nuevo_id_plan, $usuario_id);
            $stmt->execute();
        }
    }

    // Redirigir para evitar reenvío del formulario
    header("Location: personalizar.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/personalizar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Personalizar</title>
</head>
<body>
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

    <section>
        <div class="container">
            <div class="plan">
                <h1>Personaliza tu entrenamiento</h1>
                <h3>Cambia tu objetivo</h3>
                <form method="POST">
                    <div class="checksentrenamiento">
                        <?php 
                        // Verificar si hay rutinas disponibles
                        if ($result_rutinas->num_rows > 0):
                            while ($rutina = $result_rutinas->fetch_assoc()):
                        ?>
                                <label>
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
                        // Verificar si hay dietas disponibles
                        if ($result_dietas->num_rows > 0):
                            while ($dieta = $result_dietas->fetch_assoc()):
                        ?>
                                <label>
                                    <input class="radio" type="radio" name="dieta" value="<?php echo htmlspecialchars($dieta['ID']); ?>" <?php echo ($dieta['ID'] == $dieta_id) ? 'checked' : ''; ?> /> <?php echo htmlspecialchars($dieta['tipo']); ?>
                                </label>
                        <?php 
                            endwhile; 
                        else:
                        ?>
                            <p>No hay dietas disponibles.</p>
                        <?php endif; ?>
                    </div>

                    <button class="btn" type="submit">ACEPTAR</button>
                </form>
            </div>
        </div>
    </section>
    
</body>
</html>
