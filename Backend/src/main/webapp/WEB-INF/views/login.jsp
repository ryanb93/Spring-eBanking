<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBanking - Login</title>
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    </head>
    <body>
        <div>
            <h2>Login Brah!</h2>
            <div id="container">
                <input id="email" type="email" placeholder="Email">
                <input id="password" type="password" placeholder="Password">
                <div id="error_message"></div>
                <button class="small_button" type="submit" id="login_button">Login</button>
            </div>
        </div>
    <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/oauth2.js"></script>
    <script src="/js/sha256.js"></script>
    <script src="/js/enc-base64-min.js"></script>
    <script src="/js/cookie.js"></script>
    <script src="/js/user.js"></script>
    <script type="text/javascript">

    $(document).ready(function () {
      // Submit form on enter
      $('input').live('keydown', function (event) {
        if (event.keyCode === 13)
          $('#login_button').click()
      })

      if (oauth2.user.is_logged_in())
        window.location = 'dashboard.html'
      $('#login_button').on('click', function () {
        oauth2.user.login($('#email').val(), $('#password').val(), function (error) {
          if (!error)
            window.location = 'dashboard.html'
          else {
            console.log(error)
            $('#error_message').html('Email and/or password did not match a user account.').show()
          }
        })
      })

    })
    </script>
    </body>
</html>
