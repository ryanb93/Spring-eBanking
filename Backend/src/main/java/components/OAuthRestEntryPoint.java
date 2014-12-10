package components;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * An AuthenticationEntryPoint implementation which allows us to define
 * a custom error message when an authorization attempt fails.
 * 
 * Used by ExceptionTranslationFilter to commence an authentication scheme.
 */
@Component
public class OAuthRestEntryPoint implements AuthenticationEntryPoint {

    /**
     * Commences the authentication scheme to add our custom error message.
     * 
     * @param request -  that resulted in an AuthenticationException
     * @param response - so that the user agent can begin authentication
     * @param authException - that caused the invocation
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad Credentials");
    }
}
