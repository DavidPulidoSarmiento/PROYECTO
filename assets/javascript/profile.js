document.getElementById("userProfileForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita el comportamiento predeterminado del formulario (recargar la página)

    // Obtiene los valores de los campos del formulario
    var nombre = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var fecha_de_nacimiento = document.getElementById("date").value;
    var estatura = document.getElementById("stature").value;
    var peso = document.getElementById("weight").value;
    var condicion_especial = document.getElementById("special-condition").value;
    var genero = document.querySelector('input[name="genero"]:checked').value;

    // Crea un objeto FormData para enviar los datos
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
        // Muestra un mensaje o recarga la página si la actualización fue exitosa
        alert(data); // Aquí puedes modificarlo para mostrar un mensaje más amigable
        if (data === "Datos actualizados con éxito") {
            window.location.reload(); // Recargar la página para ver los cambios
        }
    })
    .catch(error => {
        console.error("Error al actualizar los datos:", error);
    });
});
