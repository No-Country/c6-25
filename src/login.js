
$(document).ready(function() {

});

async function iniciarSesion(){

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

    if(respuesta == null){
        Swal.fire({
        icon: 'error',
        text: 'Ingreso datos incorrectos'
})
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = respuesta.url;
    }
}