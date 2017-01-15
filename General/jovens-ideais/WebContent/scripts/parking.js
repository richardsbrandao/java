$(document).ready(function() {
	ParkingFuncs.Validate.init();
	ParkingFuncs.SelectCase.init();
});

var ParkingFuncs = {
	'Validate' : {
		'init' : function() {
			$('form').bind('submit', function(e) {
				return ParkingFuncs.Validate.validateForm(e.target);
			});
		},
		'validateForm' : function(tForm) {
			var BoReturn = true;
			$("form input[type=text]").each(function() {
				if (BoReturn && $(this).val() == '') { 
					BoReturn = false;
					showErrorValidate($(this));
				}
			});
			return BoReturn;
		}
	},
	'SelectCase' : {
		'init' : function() {
			//$('.selectCase').bind('click', ParkingFuncs.SelectCase.doRequest);
		},
		'doRequest' : function(e) {
			switch($('form').prop('id')) {
				case 'saveEntryVehicleFlow':
					doRequest('ajax/selectCaseVehicleCustomer', 'vehicle.id='+$(e.target).val(), ParkingFuncs.SelectCase.append);
					break;
			}
			
		},
		'append' : function(response) {
			alert(response);
		}
	}
};

function doRequest(StUrl, StData, tSuccess) {
    $.ajax({
        type:'post',
        url:StUrl,
        data:StData,
        success:tSuccess
    });
}

function hasForm() {
	return $('form').size() > 0;
}

function hideError() {
    $('#message > p').attr('class', 'hide');
}

function showError( tError, BoComplete ) {
    StMessage = !BoComplete ? tError.StMessage : tError.toString();
    $('#message > p').attr('class', 'Error').html(StMessage).show('slow');
}

function showErrorValidate( tField ) {
    showError({
        StMessage: 'O Campo "'+$(tField).prop('class')+'" é obrigatório.'
    });
    $(tField).focus();
    return false;
}

function showSuccess( tSuccess ) {
    $('#message > p').attr('class', 'Success').html(tSuccess.StMessage).show('slow');
}


