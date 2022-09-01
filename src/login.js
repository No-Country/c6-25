
$(document).ready(function() {
});


let boton = document.getElementById("btn-enviar");
// let resultado = document.querySelector(".resultado");

boton.addEventListener("click",(e)=>{
    e.preventDefault();
    const email = document.getElementById("txtEmail").value;
    validarCorreo(email);
})

const validarCorreo = (email)=>{
    let expReg = /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/;
    let verificar = expReg.test(email);
    if(verificar){
        resultado.innerHTML = "El correo es válido";
    } else {
        resultado.innerHTML = "El correo NO es válido";
    }
}

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
    } else {
        localStorage.setItem("token", respuesta.token);
        window.location.href = 'index.html';
    }
}