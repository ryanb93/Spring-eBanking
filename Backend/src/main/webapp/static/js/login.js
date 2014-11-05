$(function() {
	$(document).ready(function () {
		
		//  Redirect the user to the service if already authorised with token
		if (oauth2.user.is_logged_in()) {
			window.location = unescape(getUrlVars()["redirect_uri"]);
		}

		/*
			When ENTER key is pressed, this event triggers jQuery to click the login 
			button. This then in turn calls the click event of the login button.
		*/
		$('input').live('keydown', function (event) {
			if (event.keyCode === 13)
				$('#login_button').click()
		});

		/*
			When login is pressed the OAuth js is called and credentials from form sent. 
			If there is no error then the window redirect the user to the desiered service. 
			If there is an error present then a message is shown to inform the user. 
		*/
		$('#login_button').on('click', function () {

			oauth2.user.login($('#email').val(), $('#password').val(), function (error) {
				
				if (!error)
					window.location = unescape(getUrlVars()["redirect_uri"]);
				else {
					console.log(error)
					$('#error_message').html('Email and/or password did not match a user account.').show()
				}

			});
		});

	});
});