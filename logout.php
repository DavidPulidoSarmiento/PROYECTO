<?php
session_start(); // Inicia la sesión

// Elimina todas las variables de sesión
$_SESSION = [];

// Si se desea, se puede destruir la sesión
session_destroy();

// Redirige al usuario a la página de inicio de sesión o a la página principal
header("Location: login.php");
exit();
?>
