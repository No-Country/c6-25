function calc_total(){
    let sum = 0;
    $('.input-amount').each(function(){
        sum += parseFloat($(this).text());
    });
    $(".preview-total").text(sum);    
}
$(document).on('click', '.input-remove-row', function(){ 
    let tr = $(this).closest('tr');
    tr.fadeOut(200, function(){
    	tr.remove();
	   	calc_total()
	});
});

$(function(){
    $('.preview-add-button').click(function(){
        let form_data = {};
        form_data["concepto"] = $('.payment-form input[name="concept"]').val();
        form_data["descripcion"] = $('.payment-form input[name="description"]').val();
        form_data["monto"] = parseFloat($('.payment-form input[name="amount"]').val()).toFixed(2);
        form_data["estado"] = $('.payment-form #status option:selected').text();
        form_data["fecha"] = $('.payment-form input[name="date"]').val();
        form_data["remove-row"] = '<span class="glyphicon glyphicon-remove"></span>';
        let row = $('<tr></tr>');
        $.each(form_data, function( type, value ) {
            $('<td class="input-'+type+'"></td>').html(value).appendTo(row);
        });
        $('.preview-table > tbody:last').append(row); 
        calc_total();
        
    });  
});