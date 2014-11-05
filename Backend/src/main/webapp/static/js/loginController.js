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
							
							var accessToken = message.access_token;
							var redirect = getUrlVars()["redirect_uri"];

							if(accessToken) {
								if(redirect) {
            						window.location = unescape(redirect + "?access_token=" + accessToken);
            					}
            					else {
									$('#error_message').html('No redirect_uri found.');
            					}
							}
							else {
								$('#error_message').html('Invalid response from server, could not find Access Token');
							}
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

	/**
	 * A function to get URL 'GET' parameters.
	 * 
	 * Source: Ashley Ford (http://papermashup.com/read-url-get-variables-withjavascript/)
	 */
	function getUrlVars() {
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
	        vars[key] = value;
	    });
	    return vars;
	}


});