<?php 
require 'conexion.php';
session_start();
var_dump($_SESSION);
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
                <div class="checksentrenamiento">
                    <label>
                        <input class="radio" type="radio" id="hipertrofia" value="hipertrofia" /> Hipertrofia
                    </label>
                    <label>
                        <input class="radio" type="radio" id="fuerza" value="fuerza" /> Fuerza
                    </label>
                    <label>
                        <input class="radio" type="radio" id="resistencia" value="resistencia" /> Resistencia
                    </label>
                    <button class="btn">ACEPTAR</button>
                </div>
                
                <h1>Personaliza tu dieta</h1>
                <h3>Cambia tu objetivo</h2>
                <div class="checksentrenamiento">
                    <label>
                        <input class="radio" type="radio" id="volumen" value="volumen" /> Volumen
                    </label>
                    <label>
                        <input class="radio" type="radio" id="definicion" value="definicion" /> Definición
                    </label>
                    <label>
                        <input class="radio" type="radio" id="recomposicion" value="recomposicion" /> Recomposicion Corporal
                    </label>
                    <button class="btn">ACEPTAR</button>
                </div>
            </div>
        </div>
    </section>
    
</body>
</html>