$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

async function ingresosDebito(){
   
    let datos = {};
    datos.amount = document.getElementById('amount2').value;
    datos.from = 'visa';
    datos.to = 'MyWallet';

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

    if(respuesta.errors != undefined){
    } else {
  Swal.fire({
  icon: 'success',
  title: 'Ingreso correctamente tu dinero',
  confirmButtonText: '<a href="../Dashboard/dashboard.html">Aceptar</a>',
  // confirmButtonText: 'Aceptar',
  confirmButtonColor: 'rgb(233, 169, 8) ',
  imageUrl:'/img/Logo.png',
  footer: 'Copyright © 2022.Todos los derechos reservados. My Wallet',
})
    }
}