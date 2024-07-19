document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const correo = document.getElementById('correo').value;
    const contrasena = document.getElementById('contraseña').value;

    fetch('/server/users.json')
        .then(response => response.json())
        .then(users => {
            const user = users.find(u => u.correo === correo && u.contrasena === contrasena);
            if (user) {
                alert('Inicio de sesión exitoso');
                window.location.href = 'home.html';
            } else {
                alert('Correo o contraseña incorrectos');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
