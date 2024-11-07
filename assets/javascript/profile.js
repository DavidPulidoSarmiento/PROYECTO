document.getElementById("userProfileForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita el envío del formulario hasta que pase todas las validaciones.

    // Obtén los valores de los campos del formulario y elige el género seleccionado
    var nombre = document.getElementById("name").value.trim();
    var email = document.getElementById("email").value.trim();
    var fecha_de_nacimiento = document.getElementById("date").value.trim();
    var estatura = document.getElementById("stature").value.trim();
    var peso = document.getElementById("weight").value.trim();
    var condicion_especial = document.getElementById("special-condition").value.trim();
    var genero = document.querySelector('input[name="genero"]:checked');

    // Inicia con una bandera de error como `false` y cámbiala a `true` si se encuentra un error
    let formIsValid = true;

    // Validaciones para cada campo, con mensajes de alerta específicos.
    if (!nombre) {
        alert("El campo de nombre es obligatorio.");
        formIsValid = false;
    } else if (!/^[A-Za-z\s]+$/.test(nombre)) {
        alert("El nombre solo debe contener letras y espacios.");
        formIsValid = false;
    }

    if (!email) {
        alert("El campo de correo es obligatorio.");
        formIsValid = false;
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Por favor ingresa un correo electrónico válido.");
        formIsValid = false;
    }

    if (!fecha_de_nacimiento) {
        alert("El campo de fecha de nacimiento es obligatorio.");
        formIsValid = false;
    }

    if (!estatura) {
        alert("El campo de estatura es obligatorio.");
        formIsValid = false;
    } else if (!/^\d+(\.\d+)?$/.test(estatura)) {
        alert("La estatura solo debe contener números (con un punto decimal opcional).");
        formIsValid = false;
    }

    if (!peso) {
        alert("El campo de peso es obligatorio.");
        formIsValid = false;
    } else if (!/^\d+(\.\d+)?$/.test(peso)) {
        alert("El peso solo debe contener números (con un punto decimal opcional).");
        formIsValid = false;
    }

    if (!condicion_especial) {
        alert("El campo de condición especial es obligatorio.");
        formIsValid = false;
    } else if (!/^[A-Za-z\s]*$/.test(condicion_especial)) {
        alert("La condición especial solo debe contener letras y espacios.");
        formIsValid = false;
    }

    if (!genero) {
        alert("Por favor selecciona una opción de género.");
        formIsValid = false;
    }

    // Si `formIsValid` es `true`, se envía el formulario; de lo contrario, se detiene el proceso.
    if (formIsValid) {
        var formData = new FormData();
        formData.append("nombre", nombre);
        formData.append("email", email);
        formData.append("fecha_de_nacimiento", fecha_de_nacimiento);
        formData.append("estatura", estatura);
        formData.append("peso", peso);
        formData.append("condicion_especial", condicion_especial);
        formData.append("genero", genero.value);

        // Enviar los datos al servidor mediante AJAX (Fetch API)
        fetch("profile.php", {
            method: "POST",
            body: formData
        })
        .then(response => response.text())
        .then(data => {
            alert(data); // Mostrar mensaje de éxito o error desde el servidor
            if (data === "Datos actualizados con éxito") {
                window.location.reload(); // Recargar la página para ver los cambios
            }
        })
        .catch(error => {
            console.error("Error al actualizar los datos:", error);
        });
    }
});
