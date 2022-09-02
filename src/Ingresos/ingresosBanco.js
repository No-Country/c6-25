$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

// function nombreBanco(params) {
//   console.log(document.getElementById('amount').value);
// }
async function ingresosBanco(){

    let datos = {};
    datos.amount = document.getElementById('amount').value;
    datos.from = document.getElementById('nombreBanco').value;
    datos.to = "MyWallet";

    const request = await fetch('/api/clientes/newDeposit', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

    if(respuesta.errors == null){
    } else {
    
  Swal.fire({
  icon: 'success',
  title: 'Ingreso correctamente tu dinero',
  confirmButtonText: '<a href="/src/Dashboard/dashboard.html">Aceptar</a>',
  // confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'/img/Logo.png',
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    }
}