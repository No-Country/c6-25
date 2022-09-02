$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

async function transferenciasBanco(){
    let idTo = document.getElementById("cbuDestino").value;
    let datos = {};
    datos.amount = parseInt(document.getElementById('amount').value);
    datos.from = 'MyWallet';
    datos.to = Number(idTo);

    const request = await fetch('/api/clientes?transferTo=' + idTo, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

  if (respuesta.errors != undefined){
          Swal.fire({
            icon: 'success',
            title: 'La transferencia se realizo correctmente',
            confirmButtonText: '<a href="/src/Dashboard/dashboard.html">Aceptar</a>',
            // confirmButtonText: 'Aceptar',
            confirmButtonColor: 'rgb(233, 169, 8) ',
            imageUrl:'../img/Logo.png',
            footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
          })
    } else {

    }
}