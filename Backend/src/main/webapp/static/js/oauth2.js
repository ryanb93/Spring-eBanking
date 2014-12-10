/* Define OAuth2 Singleton Object */
function oauth2() {}

/**
 * Post authentication request to OAuth 
 */
oauth2.login = function (data, clientId, success, error) {

  var clientSecret = '286924697e615a672a646a493545646c';
  var authorization =  CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(clientId + ':' + clientSecret));
  
  $.ajax({
    url: '/oauth/token',
    type: "POST",
    accept: "application/json",
    data: data,
    headers: {
      'Authorization' :'Basic ' + authorization
    },
    dataType: "json",
    success : success,
    error : error
  });

}