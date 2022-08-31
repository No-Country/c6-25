
$(document).ready(function() {

});

async function iniciarSesion(){
Swal.fire({
  icon: 'success',
  title: 'Bienvenido',
  confirmButtonText: '<a href="index.html">Aceptar</a>',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'../img/Logo.png',
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    
    const request = await fetch('/api/auth/login', {
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