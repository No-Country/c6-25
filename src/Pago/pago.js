function calc_total() {
  let sum = 0;
  $(".input-amount").each(function () {
    sum += parseFloat($(this).text());
  });
  $(".preview-total").text(sum);
}
$(document).on("click", ".input-remove-row", function () {
  let tr = $(this).closest("tr");
  tr.fadeOut(200, function () {
    tr.remove();
    calc_total();
  });
});

$(function () {
  $(".preview-add-button").click(function () {
    let form_data = {};
    form_data["concepto"] = $('.payment-form input[name="concept"]').val();
    form_data["descripcion"] = $(
      '.payment-form input[name="description"]'
    ).val();
    form_data["monto"] = parseFloat(
      $('.payment-form input[name="amount"]').val()
    ).toFixed(2);
    let row = $("<tr></tr>");
    $.each(form_data, function (type, value) {
      $('<td class="input-' + type + '"></td>')
        .html(value)
        .appendTo(row);
    });
    $(".preview-table > tbody:last").append(row);
    calc_total();
  });
});

$(document).ready(function () {});

async function pagarServicios() {
 
  let datos = {};
  datos.amount = document.getElementById("amount").value;
  datos.from = "MyWallet";
  datos.to = document.getElementById("concept").value;

  const request = await fetch("/api/clientes/newDebit", {
    method: "POST",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
      "Authorization": localStorage.getItem("token"),
    },
    body: JSON.stringify(datos),
  });
  const respuesta = await request.json();

  if (respuesta.errors == null) {
    Swal.fire({
      icon: "error",
      text: "No se realizo el pago correctamente",
    });
  } else {
     Swal.fire({
    icon: "success",
    title: "Ingreso correctamente tu pago",
    confirmButtonText: '<a href="/src/Dashboard/dashboard.html">Aceptar</a>',
    // confirmButtonText: "Aceptar",
    confirmButtonColor: "rgb(233, 169, 8) ",
    imageUrl: "/img/Logo.png",
    footer: "Copyright © 2022.Todos los derechos reservados. My Wallet",
  });

  }
}
