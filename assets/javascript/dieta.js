function completeMeal(meal, button) {
    // Confirmar la acción
    if (!confirm(`¿Estás seguro de que deseas marcar "${meal.charAt(0).toUpperCase() + meal.slice(1)}" como completada?`)) {
        return;
    }

    // Enviar solicitud AJAX para completar la comida
    fetch('complete_meal.php', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest' // Para identificar la solicitud como AJAX
        },
        body: JSON.stringify({ meal: meal })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Cambiar el botón a "COMPLETADO" y deshabilitarlo
            button.textContent = 'COMPLETADO';
            button.classList.add('completed');
            button.disabled = true;
        } else {
            alert(data.message || 'Ocurrió un error al completar la comida.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Ocurrió un error al completar la comida.');
    });
}