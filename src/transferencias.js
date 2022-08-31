$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

async function transferenciasBanco(){
Swal.fire({
  icon: 'success',
  title: 'La transferencia se realizo correctmente',
  confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'../img/Logo.png',
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    let datos = {};
    datos.amount = document.getElementById('amount').value;
    datos.from = document.getElementById('cbuOrigen').value;
    datos.to = document.getElementById('cbuDestino').value;

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