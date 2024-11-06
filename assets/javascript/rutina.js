document.addEventListener("DOMContentLoaded", function() {
    const siguienteBtn = document.getElementById('siguiente');

    // Mostrar el siguiente ejercicio al hacer click en el botón "SIGUIENTE"
    siguienteBtn.addEventListener('click', function() {
        ejercicioActualIndex++; // Incrementar el índice del ejercicio actual
        if (ejercicioActualIndex < ejercicios.length) {
            // Si hay más ejercicios, recargar la página para mostrar el siguiente ejercicio
            location.reload();  
        } else {
            // Si no hay más ejercicios, redirigir al final de la rutina
            location.href = "finrutina.php";  
        }
    });
});
