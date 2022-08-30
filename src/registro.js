
$(document).ready(function() {

});

async function iniciarRegistro(){
 Swal.fire({
  icon: 'success',
  title: 'Bievenido',
  text:'Te registraste correctamente',
  confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'../img/Logo.png',
	// imageWidth:'',
	// imageHeight:'',
	// imageAlt:'',
  // text: 'Nos pone muy contento que te sumes a nuestra App',
  footer: 'My Wallet',
})
    let datos = {};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
	datos.email = document.getElementById('txtEmail').value;
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
