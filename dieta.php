<?php 
require 'conexion.php';
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header("Location: login.php");
    exit();
}

$usuario_id = $_SESSION['usuario_id'];

// Obtener el peso del usuario y el tipo de dieta (ID del plan de dieta)
$stmt = $conexion->prepare("SELECT peso, id_plan FROM usuario WHERE ID = :usuario_id");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmt->execute();
$usuario = $stmt->fetch(PDO::FETCH_ASSOC);

// Asegurarnos de que el usuario tiene un peso y un plan de dieta asociado
if (!$usuario || !isset($usuario['peso']) || !isset($usuario['id_plan'])) {
    echo "Datos de usuario no encontrados o incompletos.";
    exit();
}

// Obtener el tipo de dieta según el ID del plan del usuario
$tipo_dieta = $usuario['id_plan'];

// Obtener los valores nutricionales de la dieta basada en el tipo
$stmt = $conexion->prepare("SELECT proteinas, carbohidratos, calorias FROM dietas WHERE ID = :id_plan");
$stmt->bindParam(':id_plan', $tipo_dieta, PDO::PARAM_INT);
$stmt->execute();
$dieta = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$dieta) {
    echo "Dieta no encontrada.";
    exit();
}

// Calcular las cantidades de nutrientes por comida
$proteinas_por_kg = $dieta['proteinas']; // en gramos por kg
$carbohidratos_por_kg = $dieta['carbohidratos']; // en gramos por kg
$calorias_por_kg = $dieta['calorias']; // en calorías por kg

$peso_usuario = $usuario['peso']; // Peso en kg

// Calcular los nutrientes totales según el peso del usuario
$proteinas_totales = $proteinas_por_kg * $peso_usuario;
$carbohidratos_totales = $carbohidratos_por_kg * $peso_usuario;
$calorias_totales = $calorias_por_kg * $peso_usuario;

// Distribuir los nutrientes por comida según los porcentajes
$proteinas_desayuno = $proteinas_totales * 0.40;
$carbohidratos_desayuno = $carbohidratos_totales * 0.40;
$calorias_desayuno = $calorias_totales * 0.40;

$proteinas_almuerzo = $proteinas_totales * 0.35;
$carbohidratos_almuerzo = $carbohidratos_totales * 0.35;
$calorias_almuerzo = $calorias_totales * 0.35;

$proteinas_cena = $proteinas_totales * 0.25;
$carbohidratos_cena = $carbohidratos_totales * 0.25;
$calorias_cena = $calorias_totales * 0.25;

// Obtener las últimas completaciones de comidas del usuario
$stmt = $conexion->prepare("SELECT meal, last_completed FROM meal_completions WHERE usuario_id = :usuario_id");
$stmt->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmt->execute();
$result_completions = $stmt->fetchAll(PDO::FETCH_ASSOC);

// Inicializar un array para almacenar las últimas completaciones
$completions = [
    'desayuno' => null,
    'almuerzo' => null,
    'cena' => null
];

// Llenar el array con los datos obtenidos
foreach ($result_completions as $row) {
    $completions[$row['meal']] = $row['last_completed'];
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dieta</title>
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/dieta.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
   
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
                <a href="personalizar.php" class="enmedio-btn">Personalizar</a>
                <a href="dieta.php" class="enmediose-btn">Dieta</a>
            </div>
            <a href="profile.php" class="profile-btn"><img src="assets/image/profile.svg" width="90" height="70"></a>
        </div>
    </header>

    <section>
        <div class="container">
            <div class="divcomidas">
                <h1>Tus comidas pendientes son:</h1> <!-- Cambia este número si es necesario -->
                
                <!-- Comida #1 (Desayuno) -->
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
                        <?php
                            $meal = 'desayuno';
                            $last_completed = $completions[$meal];
                            $can_complete = true;
                            $button_text = 'COMPLETAR';
                            $button_class = 'btn';
                            $current_time = new DateTime();
                            
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

                <!-- Comida #2 (Almuerzo) -->
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

                <!-- Comida #3 (Cena) -->
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

    <script src="assets/javascript/dieta.js"></script>
</body>
</html>
