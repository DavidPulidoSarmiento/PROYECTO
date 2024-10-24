document.addEventListener('DOMContentLoaded', function () {
    // Llamada para obtener los datos del perfil al cargar la página
    fetch('getUserProfile.php')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los datos del perfil');
            }
            return response.json();
        })
        .then(data => {
            // Rellenar el formulario con los datos del usuario
            document.getElementById('name').value = data.name;
            document.getElementById('email').value = data.email;
            document.getElementById('date').value = data.date;
            document.getElementById('stature').value = data.stature;
            document.getElementById('weight').value = data.weight;
            document.getElementById('special-condition').value = data.special_condition;

            // Rellenar el género
            if (data.genero) {
                const genders = data.genero.split(','); // Suponiendo que los géneros están separados por comas
                genders.forEach(gender => {
                    const checkbox = document.getElementById(gender);
                    if (checkbox) {
                        checkbox.checked = true;
                    }
                });
            }
        })
        .catch(error => console.error('Error:', error));

    // Manejo del evento de envío del formulario
    const userProfileForm = document.getElementById('userProfileForm');
    userProfileForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevenir el comportamiento por defecto del formulario

        // Recolectar los datos del formulario
        const formData = new FormData(userProfileForm);
        
        fetch('updateUserProfile.php', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar el perfil');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert('Perfil actualizado exitosamente');
            } else {
                alert('Error al actualizar el perfil: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
    });
});
