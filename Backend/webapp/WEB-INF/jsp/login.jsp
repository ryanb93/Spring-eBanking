<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBanking - Login</title>
    </head>
    <body>
        <div class="container">

            <h1>eBanking</h1>

            <c:if test="${not empty param.authentication_error}">
                <h1>ERROR</h1>

                <p class="error">Your login attempt was not successful.</p>
            </c:if>
            <c:if test="${not empty param.authorization_error}">
                <h1>ERROR</h1>

                <p class="error">You are not permitted to access that resource.</p>
            </c:if>

            <div class="form-horizontal">
                <form action="<c:url value="/login.do"/>" method="post" role="form">
                    <fieldset>
                        <legend>
                            <h2>Login</h2>
                        </legend>
                        <div class="form-group">
                            <label for="username">Username:</label> <input id="username"
                                                                           class="form-control" type='text' name='j_username'
                                                                            />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label> <input id="password"
                                                                           class="form-control" type='text' name='j_password' />
                        </div>
                        <button class="btn btn-primary" type="submit">Login</button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </fieldset>
                </form>

            </div>

            <div class="footer">
                <p>eBanking</p>
            </div>

        </div>
    </body>
</html>
