$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


$(document).ready(function() {

});

async function ingresosDebito(){
Swal.fire({
  icon: 'success',
  title: 'Ingreso correctamente tu dinero',
  confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'../img/Logo.png',
  footer: 'My Wallet',
})
    let datos = {};
    datos.amount = document.getElementById('amount').value;
    datos.from = document.getElementById('MyWallet').value;
    datos.to = document.getElementById('numeroCard').value;

    const request = await fetch('/api/clientes/{id}/newDeposit', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

    if(respuesta == errors[0]){
        // alert("Ingreso el dinero correctamente");
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


$(document).ready(function() {

});
/*REVISAR CODIGO*/
async function ingresosBanco(){
Swal.fire({
  icon: 'success',
  title: 'Ingreso correctamente tu dinero',
  confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'../img/Logo.png',
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    let datos = {};
    datos.amount = document.getElementById('amount').value;
    datos.from = document.getElementById('MyWallet').value;
    datos.to = document.getElementById('numeroCard').value;

    const request = await fetch('/api/clientes/{id}/newDeposit', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

    if(respuesta == errors[0]){
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}