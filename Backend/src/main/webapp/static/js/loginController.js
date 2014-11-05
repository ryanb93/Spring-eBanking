$(document).ready(function () {

	/*
		When login is pressed the OAuth js is called and credentials from form sent. 
		If there is no error then the window redirect the user to the desiered service. 
		If there is an error present then a message is shown to inform the user. 
	*/
	$('#login_button').on('click', function () {
		
		var email = $('#email').val();
		var password = $('#password').val();

		var emailValid = isEmailValid(email);
		var passwordValid = isPasswordValid(password);

		if(emailValid && passwordValid) {

			oauth2.login({
						    "username" : email,
						    "password" : password,
						    "grant_type": "password"
						},
						function success(message) {
							console.log(message);
							$('#error_message').html('Success! Access token is <br>' + message.access_token).show();
						},
						function error(message) {
							$('#error_message').html(message.responseJSON.message).show();
						}
			);
			
		} 
		else {
			$('#error_message').html('Email and/or password are not valid.').show();
		}

		return false;

	});

});