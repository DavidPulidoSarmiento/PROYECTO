<?php 

session_start();
require 'conexion.php';

$_SESSION["usuario_id"]=$usuario;

echo $_SESSION["usuario_id"];

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/global.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=K2D:wght@400;600;700&display=swap"/>
    <title>Home</title>
</head>
<body>
    <header>
        <div class="encabezado container">
            <div class="logo">
                <img src="assets/image/logoGiGa.svg" alt="GigaGains">
                <p>GigaGains</p>
            </div>
            <div class="enmedio">
                <a href="home.html" class="enmediose-btn">Inicio</a>
                <a href="personalizar.html" class="enmedio-btn">Personalizar</a>
                <a href="dieta.html" class="enmedio-btn">Dieta</a>
            </div>
            <a href="profile.html" class="profile-btn"><img src="assets/image/profile.svg" width="90" height="70"></a>
        </div>
    </header>
    <section class="sectionuno">
        <div class="container">
            <div class="sectionuno-uno">
                <h1>Bienvenido llevas una racha de XX días</h1>
                <p>Hoy tienes que entrenar: pecho, hombro y triceps.</p>
            </div>
            <div class="sectionuno-img">
                <div>
                    <img src="assets/image/pressbanca.png" class="img">
                </div>
                <div>
                    <img src="assets/image/pressbanca.png" class="img">
                </div>
                <div>
                    <img src="assets/image/pressbanca.png" class="img">
                </div>
                <div>
                    <img src="assets/image/pressbanca.png" class="img">
                </div>
            </div>
            <div>
                <button class="start-btn" type="btn" onclick="location.href='calentamiento.html'">COMENZAR AHORA</button>
            </div>
        </div>
    </section>
</body>
</html>