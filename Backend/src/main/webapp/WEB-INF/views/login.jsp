<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        
        <!-- Load Bootstrap -->
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css">
        <link rel="stylesheet" type="text/css" href="/css/fontawesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="/css/app.css">
        <link rel="stylesheet" type="text/css" href="/css/login.css">

        <title>eBanking - Login</title>
    </head>
    <body>
      <nav role="navigation" class="navbar topnavbar">
        <div class="navbar-header">
          <a href="#/" class="navbar-brand">
            <div class="brand-logo">
              eBanking
            </div>
          </a>
        </div>
      </nav>
        <div class="container">
            <form class="form-signin" role="form">
              <h2 class="form-signin-heading">Please sign in</h2>
              <input id="email" class="form-control" type="email" placeholder="Email" required autofocus>
              <input id="password" class="form-control" type="password" placeholder="Password" required>
              <div id="error_message" class="bg-danger"></div>
              <button class="btn btn-lg btn-primary btn-block" type="submit" id="login_button">Login</button>
            </form>
        </div>

    <script src="/js/jquery-2.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
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
