<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBanking - Login</title>
    </head>
    <body>
        <div>
            <h2>Login Brah!</h2>
            <form name="input" action="/login" method="POST" >
                    <label>Username</label>
                    <input type="text" name="username" />
                    <label>Password</label>
                    <input type="password" name="password"/>
                    <input type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
