$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


$(document).ready(function() {

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
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    let datos = {};
    datos.amount = document.getElementById('amount').value;
    datos.from = document.getElementById('MyWallet').value;
    datos.to = document.getElementById('numeroCuenta').value;

    const request = await fetch('/api/clientes/{id}/externalPayment	', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

    if(respuesta == null){
        // alert("Ingreso el dinero correctamente");
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}