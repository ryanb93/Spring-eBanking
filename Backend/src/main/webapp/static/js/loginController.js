$(document).ready(function () {

	/*
		When ENTER key is pressed, this event triggers jQuery to click the login 
		button. This then in turn calls the click event of the login button.
	*/
	$('input').on('keydown', function (event) {
		if (event.keyCode === 13)
			$('#login_button').click()
	});

	/*
		When login is pressed the OAuth js is called and credentials from form sent. 
		If there is no error then the window redirect the user to the desiered service. 
		If there is an error present then a message is shown to inform the user. 
	*/
	$('#login_button').on('click', function () {

		oauth2.method.doLogin($('#email').val(), $('#password').val(), function (authError) {	
			if (authError) {
				$('#error_message').html('Email and/or password did not match a user account.').show()
			}
		});

	});

});