$(document).ready(function () {

	/*
		When login is pressed the OAuth js is called and credentials from form sent. 
		If there is no error then the window redirect the user to the desiered service. 
		If there is an error present then a message is shown to inform the user. 
	*/
	$('#login_button').on('click', function () {
		
		var email = isEmailValid($('#email').val());
		var password = isPasswordValid($('#password').val());

		if(email && password) {
			oauth2.method.doLogin($('#email').val(), $('#password').val(), function (authError) {	
				if (authError) {
					$('#error_message').html('Email and/or password did not match a user account.').show();
				}
			});
		} else {
			$('#error_message').html('Email and/or password are not valid.').show();
			return false;
		}

		

	});

});