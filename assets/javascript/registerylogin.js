document.getElementById("registerForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto

    const form = event.target;
    const nombre = form.nombre.value.trim();
    const correo = form.correo.value.trim();
    const contraseña = form.contraseña.value;
    const confirmarContraseña = form.confirmar_contraseña.value;
    const fechaNacimiento = form.fecha_nacimiento.value;
    const estatura = form.estatura.value.trim();
    const peso = form.peso.value.trim();
    const condicionEspecial = form.condicion_especial.value.trim();
    const generoSeleccionado = Array.from(form["genero[]"]).filter(checkbox => checkbox.checked);

    // Verificar si hay campos vacíos
    if (!nombre || !correo || !contraseña || !confirmarContraseña || !fechaNacimiento || !estatura || !peso || !condicionEspecial || generoSeleccionado.length === 0) {
        alert("Por favor, completa todos los campos antes de enviar el formulario.");
        return;
    }

    // Validación de solo letras en el campo "nombre" y "condicion especial"
    const soloLetras = /^[a-zA-Z\s]+$/;
    if (!soloLetras.test(nombre)) {
        alert("El campo 'nombre' solo debe contener letras.");
        return;
    }

    if (!soloLetras.test(condicionEspecial)) {
        alert("El campo 'condición especial' solo debe contener letras.");
        return;
    }

    // Validación de solo números en "estatura" y "peso"
    const soloNumeros = /^[0-9]+$/;
    if (!soloNumeros.test(estatura)) {
        alert("El campo 'estatura' solo debe contener números.");
        return;
    }

    if (!soloNumeros.test(peso)) {
        alert("El campo 'peso' solo debe contener números.");
        return;
    }

    // Validación de selección única de género
    if (generoSeleccionado.length !== 1) {
        alert("Por favor, selecciona solo un género.");
        return;
    }

    // Validación de contraseñas iguales
    if (contraseña !== confirmarContraseña) {
        alert("Las contraseñas no coinciden.");
        return;
    }

    // Enviar los datos del formulario a PHP
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

document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const correo = document.getElementById("correo").value;
    const contraseña = document.getElementById("contraseña").value;

    // Validar que los campos no estén vacíos
    if (!correo || !contraseña) {
        alert("Por favor, completa todos los campos.");
        return;
    }

    // Enviar los datos al servidor
    fetch("login.php", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `correo=${encodeURIComponent(correo)}&contraseña=${encodeURIComponent(contraseña)}`
    })
    .then(response => response.text())
    .then(data => {
        // Manejar la respuesta del servidor
        if (data === "success") {
            window.location.href = "home.php"; // Redirigir a la página principal
        } else {
            alert(data); // Mostrar mensaje de error
        }
    })
    .catch(error => console.error('Error:', error));
});



