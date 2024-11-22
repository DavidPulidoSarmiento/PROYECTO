<?php 
// Incluir el archivo de conexión a la base de datos
require 'conexion.php';

// Iniciar la sesión para poder acceder a la información del usuario
session_start();

// Verificar si el usuario ha iniciado sesión. Si no, redirigirlo al login
if (!isset($_SESSION['usuario_id'])) {
    // Redirigir al usuario a la página de inicio de sesión si no está logueado
    header("Location: login.php");
    exit(); // Detener la ejecución del script
}

// Obtener el ID del usuario logueado
$usuario_id = $_SESSION['usuario_id'];

// Consultar los datos del usuario, específicamente su peso y el ID de su plan de dieta
$stmt = $conexion->prepare("SELECT peso, id_plan FROM usuario WHERE ID = :usuario_id");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT); // Vinculamos el parámetro para evitar inyecciones SQL
$stmt->execute(); // Ejecutamos la consulta
$usuario = $stmt->fetch(PDO::FETCH_ASSOC); // Obtenemos los datos como un arreglo asociativo

// Verificar que los datos del usuario se han obtenido correctamente
if (!$usuario || !isset($usuario['peso']) || !isset($usuario['id_plan'])) {
    // Si faltan datos importantes como el peso o el plan de dieta, mostramos un error
    echo "Datos de usuario no encontrados o incompletos.";
    exit(); // Detenemos la ejecución
}

// Obtener el ID del plan de dieta del usuario
$tipo_dieta = $usuario['id_plan'];

// Obtener los valores nutricionales asociados al tipo de dieta del usuario (proteínas, carbohidratos y calorías)
$stmt = $conexion->prepare("SELECT proteinas, carbohidratos, calorias FROM dietas WHERE ID = :id_plan");
$stmt->bindParam(':id_plan', $tipo_dieta, PDO::PARAM_INT); // Vinculamos el ID del plan de dieta
$stmt->execute(); // Ejecutamos la consulta
$dieta = $stmt->fetch(PDO::FETCH_ASSOC); // Obtenemos los datos de la dieta

// Verificar que los datos de la dieta se han encontrado correctamente
if (!$dieta) {
    // Si no se encuentra la dieta, mostramos un mensaje de error
    echo "Dieta no encontrada.";
    exit(); // Detenemos la ejecución
}

// Asignamos los valores nutricionales de la dieta a variables
$proteinas_por_kg = $dieta['proteinas']; // Proteínas por kilogramo de peso corporal
$carbohidratos_por_kg = $dieta['carbohidratos']; // Carbohidratos por kilogramo de peso corporal
$calorias_por_kg = $dieta['calorias']; // Calorías por kilogramo de peso corporal

// Obtener el peso del usuario
$peso_usuario = $usuario['peso']; // Peso del usuario en kilogramos

// Calcular las cantidades totales de nutrientes (proteínas, carbohidratos y calorías) basadas en el peso del usuario
$proteinas_totales = $proteinas_por_kg * $peso_usuario;
$carbohidratos_totales = $carbohidratos_por_kg * $peso_usuario;
$calorias_totales = $calorias_por_kg * $peso_usuario;

// Distribuir las cantidades de nutrientes por cada comida (desayuno, almuerzo y cena)
// Los porcentajes pueden ser ajustados según el plan de dieta
$proteinas_desayuno = $proteinas_totales * 0.40; // 40% de las proteínas totales para el desayuno
$carbohidratos_desayuno = $carbohidratos_totales * 0.40; // 40% de los carbohidratos totales para el desayuno
$calorias_desayuno = $calorias_totales * 0.40; // 40% de las calorías totales para el desayuno

$proteinas_almuerzo = $proteinas_totales * 0.35; // 35% de las proteínas totales para el almuerzo
$carbohidratos_almuerzo = $carbohidratos_totales * 0.35; // 35% de los carbohidratos totales para el almuerzo
$calorias_almuerzo = $calorias_totales * 0.35; // 35% de las calorías totales para el almuerzo

$proteinas_cena = $proteinas_totales * 0.25; // 25% de las proteínas totales para la cena
$carbohidratos_cena = $carbohidratos_totales * 0.25; // 25% de los carbohidratos totales para la cena
$calorias_cena = $calorias_totales * 0.25; // 25% de las calorías totales para la cena

// Obtener las últimas fechas de comida completada por el usuario (desayuno, almuerzo y cena)
$stmt = $conexion->prepare("SELECT meal, last_completed FROM meal_completions WHERE usuario_id = :usuario_id");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT); // Vinculamos el ID del usuario
$stmt->execute(); // Ejecutamos la consulta
$result_completions = $stmt->fetchAll(PDO::FETCH_ASSOC); // Obtenemos todas las completaciones de comidas

// Inicializamos un arreglo para almacenar las últimas completaciones de comidas
$completions = [
    'desayuno' => null,
    'almuerzo' => null,
    'cena' => null
];

// Llenamos el arreglo con los datos de las últimas completaciones
foreach ($result_completions as $row) {
    $completions[$row['meal']] = $row['last_completed']; // Asignamos la última fecha de completación por comida
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dieta</title>
    <!-- Enlaces a archivos CSS para el diseño de la página -->
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/dieta.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
</head>
<body>
    <!-- Encabezado de la página -->
    <header>
        <div class="encabezado container">
            <div class="logo">
                <img src="assets/image/logoGiGa.svg" alt="GigaGains">
                <p>GigaGains</p>
            </div>
            <div class="enmedio">
                <a href="home.php" class="enmedio-btn">Inicio</a>
                <a href="personalizar.php" class="enmedio-btn">Personalizar</a>
                <a href="dieta.php" class="enmediose-btn">Dieta</a>
            </div>
            <a href="profile.php" class="profile-btn"><img src="assets/image/profile.svg" width="90" height="70"></a>
        </div>
    </header>

    <section>
        <div class="container">
            <div class="divcomidas">
                <h1>Tus comidas pendientes son:</h1>

                <!-- Comida #1: Desayuno -->
                <div class="comida">
                    <h3>Comida #1 (Desayuno)</h3>
                    <div class="valores-nutricionales">
                        <div class="valores">
                            <p>Proteínas</p>
                            <p><?php echo number_format($proteinas_desayuno, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Carbohidratos</p>
                            <p><?php echo number_format($carbohidratos_desayuno, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Calorías totales</p>
                            <p><?php echo number_format($calorias_desayuno, 0); ?> kcal</p>
                        </div> 

                        <!-- Botón para marcar el desayuno como completado -->
                        <?php
                            $meal = 'desayuno'; // Nombre de la comida
                            $last_completed = $completions[$meal]; // Obtener la última vez que se completó esta comida
                            $can_complete = true; // Inicialmente se puede completar
                            $button_text = 'COMPLETAR'; // Texto del botón
                            $button_class = 'btn'; // Clase CSS del botón
                            $current_time = new DateTime(); // Hora actual
                            
                            // Verificar si la comida ya ha sido completada en las últimas 24 horas
                            if ($last_completed) {
                                $last_completed_time = new DateTime($last_completed); // Convertir la fecha de la última completación
                                $interval = $current_time->diff($last_completed_time); // Calcular la diferencia de tiempo
                                $hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60); // Convertir la diferencia a horas

                                // Si han pasado menos de 24 horas desde la última vez que se completó la comida, no se puede marcar como completada
                                if ($hours_passed < 24) {
                                    $can_complete = false; // No se puede completar
                                    $button_text = 'COMPLETADO'; // Cambiar el texto del botón
                                    $button_class .= ' completed'; // Añadir clase de estilo para mostrar que está completado
                                }
                            }
                        ?>
                        <!-- Mostrar el botón, deshabilitado si no se puede completar -->
                        <button class="<?php echo $button_class; ?>" <?php echo !$can_complete ? 'disabled' : ''; ?> onclick="completeMeal('<?php echo $meal; ?>', this)">
                            <?php echo $button_text; ?>
                        </button>
                    </div>
                </div>

                <!-- Comida #2: Almuerzo -->
                <div class="comida">
                    <h3>Comida #2 (Almuerzo)</h3>
                    <div class="valores-nutricionales">
                        <div class="valores">
                            <p>Proteínas</p>
                            <p><?php echo number_format($proteinas_almuerzo, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Carbohidratos</p>
                            <p><?php echo number_format($carbohidratos_almuerzo, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Calorías totales</p>
                            <p><?php echo number_format($calorias_almuerzo, 0); ?> kcal</p>
                        </div>

                        <!-- Botón para marcar el almuerzo como completado -->
                        <?php
                            $meal = 'almuerzo';
                            $last_completed = $completions[$meal];
                            $can_complete = true;
                            $button_text = 'COMPLETAR';
                            $button_class = 'btn';
                            
                            if ($last_completed) {
                                $last_completed_time = new DateTime($last_completed);
                                $interval = $current_time->diff($last_completed_time);
                                $hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);
                                
                                if ($hours_passed < 24) {
                                    $can_complete = false;
                                    $button_text = 'COMPLETADO';
                                    $button_class .= ' completed';
                                }
                            }
                        ?>
                        <button class="<?php echo $button_class; ?>" <?php echo !$can_complete ? 'disabled' : ''; ?> onclick="completeMeal('<?php echo $meal; ?>', this)">
                            <?php echo $button_text; ?>
                        </button>
                    </div>
                </div>

                <!-- Comida #3: Cena -->
                <div class="comida">
                    <h3>Comida #3 (Cena)</h3>
                    <div class="valores-nutricionales">
                        <div class="valores">
                            <p>Proteínas</p>
                            <p><?php echo number_format($proteinas_cena, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Carbohidratos</p>
                            <p><?php echo number_format($carbohidratos_cena, 2); ?> gr</p>
                        </div>
                        <div class="valores">
                            <p>Calorías totales</p>
                            <p><?php echo number_format($calorias_cena, 0); ?> kcal</p>
                        </div>
                        <!-- Botón para marcar la cena como completada -->
                        <?php
                            $meal = 'cena';
                            $last_completed = $completions[$meal];
                            $can_complete = true;
                            $button_text = 'COMPLETAR';
                            $button_class = 'btn';
                            
                            if ($last_completed) {
                                $last_completed_time = new DateTime($last_completed);
                                $interval = $current_time->diff($last_completed_time);
                                $hours_passed = ($interval->days * 24) + $interval->h + ($interval->i / 60);
                                
                                if ($hours_passed < 24) {
                                    $can_complete = false;
                                    $button_text = 'COMPLETADO';
                                    $button_class .= ' completed';
                                }
                            }
                        ?>
                        <button class="<?php echo $button_class; ?>" <?php echo !$can_complete ? 'disabled' : ''; ?> onclick="completeMeal('<?php echo $meal; ?>', this)">
                            <?php echo $button_text; ?>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Enlace al archivo JavaScript -->
    <script src="assets/javascript/dieta.js"></script>
</body>
</html>
