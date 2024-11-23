document.getElementById("userProfileForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita el envío del formulario hasta que pase todas las validaciones.

    // Obtén los valores de los campos del formulario y elige el género seleccionado
    let nombre = document.getElementById("name").value.trim();
    let email = document.getElementById("email").value.trim();
    let fecha_de_nacimiento = document.getElementById("date").value.trim();
    let estatura = document.getElementById("stature").value.trim();
    let peso = document.getElementById("weight").value.trim();
    let condicion_especial = document.getElementById("special-condition").value.trim();
    let generoElement = document.querySelector('input[name="genero"]:checked');
    let genero = generoElement ? generoElement.value : '';

    // Inicia con una bandera de error como `false` y cámbiala a `true` si se encuentra un error
    let formIsValid = true;
    let errorMessages = [];

    // Validaciones para cada campo, con mensajes de alerta específicos.
    if (!nombre) {
        errorMessages.push("El campo de nombre es obligatorio.");
        formIsValid = false;
    } else if (!/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(nombre)) { // Permitir acentos y la letra ñ
        errorMessages.push("El nombre solo debe contener letras y espacios.");
        formIsValid = false;
    }

    if (!email) {
        errorMessages.push("El campo de correo es obligatorio.");
        formIsValid = false;
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        errorMessages.push("Por favor ingresa un correo electrónico válido.");
        formIsValid = false;
    }

    if (!fecha_de_nacimiento) {
        errorMessages.push("El campo de fecha de nacimiento es obligatorio.");
        formIsValid = false;
    } else {
        const birthDate = new Date(fecha_de_nacimiento);
        const today = new Date();
        if (birthDate >= today) {
            errorMessages.push("La fecha de nacimiento debe ser una fecha pasada.");
            formIsValid = false;
        }
    }

    if (!estatura) {
        errorMessages.push("El campo de estatura es obligatorio.");
        formIsValid = false;
    } else if (!/^\d{1,3}(\.\d{1,2})?$/.test(estatura)) { // Permitir hasta 3 dígitos enteros y 2 decimales
        errorMessages.push("La estatura debe ser un número válido (por ejemplo, 1.75).");
        formIsValid = false;
    }

    if (!peso) {
        errorMessages.push("El campo de peso es obligatorio.");
        formIsValid = false;
    } else if (!/^\d{1,3}(\.\d{1,2})?$/.test(peso)) { // Similar a estatura
        errorMessages.push("El peso debe ser un número válido (por ejemplo, 70.5).");
        formIsValid = false;
    }

    if (!condicion_especial) {
        errorMessages.push("El campo de condición especial es obligatorio.");
        formIsValid = false;
    } else if (!/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]*$/.test(condicion_especial)) { // Permitir acentos y la letra ñ
        errorMessages.push("La condición especial solo debe contener letras y espacios.");
        formIsValid = false;
    }

    if (!genero) {
        errorMessages.push("Por favor selecciona una opción de género.");
        formIsValid = false;
    }

    // Mostrar todos los mensajes de error de una vez
    if (!formIsValid) {
        alert(errorMessages.join("\n"));
        return;
    }

    // Si `formIsValid` es `true`, se envía el formulario
    var formData = new FormData();
    formData.append("nombre", nombre);
    formData.append("email", email);
    formData.append("fecha_de_nacimiento", fecha_de_nacimiento);
    formData.append("estatura", estatura);
    formData.append("peso", peso);
    formData.append("condicion_especial", condicion_especial);
    formData.append("genero", genero);

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
        alert("Ocurrió un error al actualizar los datos. Por favor, intenta de nuevo más tarde.");
    });
});
