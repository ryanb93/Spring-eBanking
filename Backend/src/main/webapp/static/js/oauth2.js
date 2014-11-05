/* Define OAuth2 Singleton Object */
function oauth2() {}

(function() {

/**
 * Post authentication request to OAuth 
 */
oauth2.login = function (data, success, error) {

  var authorization =  CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse('353b302c44574f565045687e534e7d6a' + ':' + '286924697e615a672a646a493545646c'));
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

});