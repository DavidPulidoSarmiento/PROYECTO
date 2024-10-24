document.getElementById("registerForm").addEventListener("submit", function(event) {
  event.preventDefault(); // Evitar el envío del formulario por defecto

  // Obtener los datos del formulario
  const formData = new FormData(this);

  // Validar que las contraseñas coincidan
  const password = formData.get("contraseña");
  const confirmPassword = formData.get("confirmar_contraseña");
  if (password !== confirmPassword) {
      alert("Las contraseñas no coinciden.");
      return;
  }

  // Enviar los datos del formulario a PHP
  fetch("register.php", {
      method: "POST",
      body: formData
  })
  .then(response => response.json())
  .then(data => {
      if (data.success) {
          alert("Usuario registrado exitosamente.");
          // Redirigir o limpiar el formulario si es necesario
          // location.href = "iniciar.html"; // Redirigir a la página de inicio de sesión
          document.getElementById("registerForm").reset();
      } else {
          alert("Error: " + data.message);
      }
  })
  .catch(error => console.error("Error:", error));
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
            window.location.href = "home.html"; // Redirigir a la página principal
        } else {
            alert(data); // Mostrar mensaje de error
        }
    })
    .catch(error => console.error('Error:', error));
});

