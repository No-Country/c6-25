$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

// Call the dataTables jQuery plugin
$(document).ready(function() {

//on ready
});

async function ingresos(){
 Swal.fire({
  icon: 'success',
  title: 'Ingreso correctamente tu dinero',
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
        alert("Ingreso el dinero correctamente");
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = respuesta.url;
    }
}