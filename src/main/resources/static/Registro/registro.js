
$(document).ready(function() {

});

async function iniciarRegistro(){

    let datos = {};

    datos.firstName = document.getElementById('txtNombre').value;
    datos.lastName = document.getElementById('txtApellido').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.age = document.getElementById('txtEdad').value;
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

    if(respuesta.errors != undefined){
        Swal.fire({
        icon: 'error',
        text: 'Ingreso datos incorrectos'
})
    } else {
            Swal.fire({
              icon: 'success',
              title: 'Bievenido',
              text:'Te registraste correctamente',
              confirmButtonText: '<a href="../Dashboard/dashboard.html">Aceptar</a>',
              confirmButtonColor: 'rgb(233, 169, 8) ',
              imageUrl:'/img/Logo.png',
              footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
            })


        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}
