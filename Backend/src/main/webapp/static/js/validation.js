function isEmailValid(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}

function isPasswordValid(password) {
	var regex = /^[a-zA-Z0-9_!@£$%^&()]*$/;
	return regex.test(password);
}