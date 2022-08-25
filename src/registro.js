
$(document).ready(function() {

});

async function iniciarRegistro(){

    let datos = {};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.dni = document.getElementById('txtDni').value;
	datos.edad = document.getElementById('txtEdad').value;
    datos.password = document.getElementById('txtPassword').value;
   
    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if(repetirPassword != datos.password){
        Swal.fire({
        icon: 'error',
        text: 'Ingreso contraseñas diferentes'
})
        return;
    }

    const request = await fetch('/api/auth/register', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const respuesta = await request.json();

    if(respuesta == errors[0]){ 
        Swal.fire({
        icon: 'error',
        text: 'Ingreso datos incorrectos'
})
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}
