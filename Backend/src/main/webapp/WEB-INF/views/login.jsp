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
              eBanking Backend - Login
            </div>
          </a>
        </div>
      </nav>
        <div class="container">
            <form class="form-signin" role="form">
              <h2 class="form-signin-heading">Please sign in</h2>
              <input id="email" class="form-control" type="email" placeholder="Email" required autofocus>
              <input id="password" class="form-control" type="password" placeholder="Password" required>
              <button class="btn btn-lg btn-primary btn-block" type="submit" id="login_button">Login</button>
              <br><br>
              <div id="error_message" class="alert alert-danger"></div>
              <div id="token_message" class="bg-success"></div>
            </form>
        </div>
        <footer class="footer">
      <div class="container">
        <p>Created by Ryan Burke, Jorden Whitefield and Ben Fletcher</p>
        <p>Powered by Spring, MongoDB and Tomcat </p>
        <img class="ssl" src="img/ssl.png" alt="SSL logo">
      </div>
    </footer>

    <!-- JS Libs -->
    <script src="/js/libs/jquery-2.1.1.min.js"></script>
    <script src="/js/libs/bootstrap.min.js"></script>
    <script src="/js/libs/sha256.js"></script>
    <script src="/js/libs/enc-base64-min.js"></script>

    <!-- Custom JS -->
    <script src="/js/oauth2.js"></script> 
    <script src="/js/loginController.js"></script>
    <script src="/js/validation.js"></script>

    </body>
</html>