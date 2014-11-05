/*
 *	
 */

oauth2.method = {};

/**
 * Log the customer onto our API via OAuth2
 *
 * @param String username
 *	Username in plain text from the login.jsp form. Encodes in Base64 before being posted by OAuth2.js
 * @param String password
 *	Password in plain text from the login.jsp form. Encodes in Base64 before being posted by OAuth2.js
 * @param FUNCTION Callback. 
 *	Define function that will handle User auth error from OAuth
 */
oauth2.method.doLogin = function (email, password, callback) {
  oauth2.login(
    {
      "username" : email,
      "password" : password,
      "grant_type": "password"
    },
    function (response) {
    	// Nothing to be set. User being handed back token from server
      callback();
    },
    function(jqXHR, textStatus) {
      callback(jqXHR);
    }
  );
}