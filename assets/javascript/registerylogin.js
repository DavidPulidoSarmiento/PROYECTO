document.getElementById("registerForm").addEventListener("submit", function (event) {
    const form = event.target;
    event.preventDefault(); // Evitar el envío del formulario hasta haber hecho todas las validaciones
    console.log("Formulario enviado");
    // Definir las variables del formulario
    const nombre = form.nombre.value.trim();
    const correo = form.correo.value.trim();
    const contrasena = form.contrasena.value;
    const confirmarContrasena = form.confirmar_contrasena.value;
    const fechaNacimiento = form.fecha_nacimiento.value;
    const estatura = form.estatura.value.trim();
    const peso = form.peso.value.trim();
    const condicionEspecial = form.condicion_especial.value.trim();
    const generoSeleccionado = Array.from(form["genero[]"]).filter(checkbox => checkbox.checked);

    // Variable para saber si alguna validación falló
    let validacionFallida = false;

    // Verificar campos vacíos
    if (!nombre) {
        alert("Por favor, completa el campo de nombre.");
        validacionFallida = true;
    } else if (!correo) {
        alert("Por favor, completa el campo de correo.");
        validacionFallida = true;
    }else if (!contrasena) {
        alert("Por favor, completa el campo de contraseña.");
        validacionFallida = true;
    }else if (!fechaNacimiento) {
        alert("Por favor, completa el campo de fecha de nacimiento.");
        validacionFallida = true;
    }else if (!estatura) {
        alert("Por favor, completa el campo de estatura.");
        validacionFallida = true;
    }else if (!peso) {
        alert("Por favor, completa el campo de peso.");
        validacionFallida = true;
    }else if (!condicionEspecial) {
        alert("Por favor, completa el campo de condición especial.");
        validacionFallida = true;
    }else if (generoSeleccionado.length === 0) {
        alert("Por favor, selecciona un género.");
        validacionFallida = true;
    }

    // Validación de formato de correo electrónico
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(correo)) {
        alert("Por favor, ingresa un correo electrónico válido (ejemplo@dominio.com).");
        validacionFallida = true;
    }
    
    // Validación de selección única de género
    if (generoSeleccionado.length !== 1) {
        alert("Por favor, selecciona solo un género.");
        validacionFallida = true;
    }

    // Validación de contraseñas iguales
    if (contrasena !== confirmarContrasena) {
        alert("Las contraseñas no coinciden.");
        validacionFallida = true;
    }

    // Si alguna validación falló, no enviamos el formulario
    if (validacionFallida) {
        return; // Sale de la función sin enviar el formulario
    }

    // Si todo está bien, se envían los datos del formulario a PHP
    const formData = new FormData(form);
    fetch("register.php", {
        method: "POST",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Usuario registrado exitosamente.");
                form.reset(); // Limpia el formulario tras el registro exitoso

                // Redirigir a login.php después de registro exitoso
                window.location.href = "login.php"; // Aquí rediriges al login
            } else {
                alert("Error: " + data.message);
            }
        })
        .catch(error => console.error("Error:", error));
});

// Validación en tiempo real para campos específicos
document.getElementById("registerForm").nombre.addEventListener("input", function (event) {
    event.target.value = event.target.value.replace(/[^a-zA-Z\s]/g, ""); // Solo permite letras y espacios
});

document.getElementById("registerForm").condicion_especial.addEventListener("input", function (event) {
    event.target.value = event.target.value.replace(/[^a-zA-Z\s]/g, ""); // Solo permite letras y espacios
});

document.getElementById("registerForm").estatura.addEventListener("input", function (event) {
    event.target.value = event.target.value.replace(/[^0-9]/g, ""); // Solo permite números
});

document.getElementById("registerForm").peso.addEventListener("input", function (event) {
    event.target.value = event.target.value.replace(/[^0-9]/g, ""); // Solo permite números
});
