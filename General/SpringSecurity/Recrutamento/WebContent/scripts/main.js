$(document).ready(function() {
	if( $('body').prop('class') == 'admin' ) {
		Ce.formValidator.init();
		Ce.alert.init();
		Ce.date.init();
	} else if($('body').prop('class') == 'evaluation') {
		Ce.timer.init();
	}
});


var Ce = {
	'formValidator' : {
		'init' : function() {
			switch($('form').prop('name')) {
				case 'formCreateNewQuestion': 
					$('form').bind('submit', function() { return CeValidator.createEditQuestion.validate(); });
					break;
				case 'formEditQuestion':
					$('form').bind('submit', function() { return CeValidator.createEditQuestion.validate(); });
					break;
				case 'formCreateEvaluation':	
					$('form').bind('submit', function() { return CeValidator.createEvaluation.validate();});
					break;
				default:
			}
		}
	},
	'alert' : {
		'init' : function() {
			$(".close").bind('click', function() { hideLastError(); return false; });
		}
	},
	'timer' : {
		'regressiveDiv' : null,
		'timestampEnd' : null,
		'timeout' : false,
		'init' : function() {
			Ce.timer.regressiveDiv = $('#clock');
			Ce.timer.timestampEnd = (new Date()).getTime() + Ce.timer.addRegressive(Configs.hour, Configs.minute);
			Ce.timer.showCountDown();
		},
		'showCountDown' : function() {
			$('#countdown').countdown({
				timestamp	: Ce.timer.timestampEnd,
				callback	: function(days, hours, minutes, seconds){
					if($(Ce.timer.regressiveDiv).prop('id')) { 
						if(Ce.timer.timeout) {
							return false;
						}
						if(Ce.timer.isTheCounterReset(days, hours, minutes, seconds)) {
							Ce.timer.timeout = true;
							$('form').attr('action', Configs.baseUrl+'test/evaluationExpired');
							doRequest(Configs.baseUrl+'test/saveResult', Ce.timer.getAnswers(), Ce.timer.callback);
						} 
						Ce.timer.countDownCallback(hours, minutes, seconds);
					}
				}
			});
		},
		'callback' : function(response) {
			showSuccess("Prova enviada automaticamente");
		},
		'getAnswers' : function() {
			var ArParams = [];
			$('input[type=radio]:checked').each(function() {
				ArParams.push( $(this).prop('name')+'='+$(this).val() );
			});
			return ArParams.join('&');
		},
		'isTheCounterReset' : function(days, hours, minutes, seconds) {
			return days == 0 && hours == 0 && minutes == 0 && seconds == 0;
		},
		'countDownCallback' : function(hours, minutes, seconds) {
			var clock = hours + ":" + minutes + ":" + seconds;
			if( hours == 0 && minutes < 3  ) {
				clock = "<span class=\"final\">"+clock+"</span>";
			}
			Ce.timer.regressiveDiv.html(clock);
		},
		'addRegressive' : function(hour, minute) {
			hour = (hour == 0 || !hour) ? 1 : hour; 
			return hour*minute*60*1000;
//			return hour*1*15*1000;
		}
	},
	'date' : {
		'init' : function() {
			$('.datepicker').each(function(){
			    $(this).datepicker({dateFormat: 'dd/mm/yy'});
			});
		}
	}
};

var CeValidator = {
		'createEditQuestion' : {
			'validate' : function() {
				hideLastError();
				var isValidForm = true;
				$('#questionTitle, #questionDescription').each(function() {
				    if(isValidForm && $(this).val() == '') {
				        isValidForm = false;
				        showErrorValidate($(this));
				    }
				});
				if(!isValidForm) {
					return false;
				}
				
				var ItOptions = 0; 
				$('input[id*=questionOptions]').each(function() { 
					if($(this).val() != '') { 
						ItOptions++; 
					} 
				}); 
				if(ItOptions < 2) {
					showError("Pelo menos 2 op&ccedil;&otilde;es devem ser preenchidas.");
					return false;
				}
				
				if( $('input[type=radio]:checked').prop('name') == undefined ) {
					showError("Nenhuma op&ccedil;&atilde;o foi marcada como correta");
					return false;
				}
				if($('input[id=questionOptions'+$('input[type=radio]:checked').val()+'Answer]').val() == "") {
					showError("Op&ccedil;&atilde;o marcada como correta n&atilde;o foi preenchida.");
					return false;
				}
				
				
				return true;
			}
		},
		'createEvaluation' : {
			'validate': function() {
				var isValidForm = true;
				$('#candidateName, #candidateEmail, #candidateLevel').each(function() {
					if(isValidForm && $(this).val() == '') {
						isValidForm = false;
						showErrorValidate($(this));
					}
				});
				return isValidForm;
			}
		}
};

function showError(StMessage) {
	$('div[id*=Container]').attr('class', '');
	$('div[id*=Container] div p').html(StMessage);
}

function showErrorValidate(tInput) {
	showError("Formul&aacute;rio preenchido incorretamente.");
	$(tInput).focus();
}

function hideLastError() {
	$('div[id*=Container]').attr('class', 'hide');
}
function redirect(StUrl) {
	location.href=StUrl;
}
function showSuccess(StMessage) {
	$('#successContainer').attr('class', '');
	$('#successContainer p').html(StMessage);
}
function doRequest(StUrl, StData, tSuccess) {
    $.ajax({
        type:'post',
        url:StUrl,
        data:StData,
        success:tSuccess
    });
}