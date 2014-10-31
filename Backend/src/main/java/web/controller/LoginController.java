package web.controller;

import ch.qos.logback.core.Context;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;
import java.net.URI;
import java.util.Collections;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import web.domain.ApiUser;
import web.domain.Role;
import web.events.users.CreateUserRequest;
import web.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView login(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        ModelAndView modelAndView = new ModelAndView("login", "model", null);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response signupUser(final CreateUserRequest request) {
        ApiUser user = userService.createUser(request);
        CreateUserRequest createUserResponse = new CreateUserRequest(user, createTokenForNewUser(
                user.getId(), request.getPassword().getPassword(), SecurityContextHolder.getContext().getAuthentication().getDetails().toString()));
        URI location = request.getAbsolutePathBuilder().path(createUserResponse.getApiUser().getId()).build();
        return Response.created(location).entity(createUserResponse).build();
    }

    
    private OAuth2AccessToken createTokenForNewUser(String userId, String password, String clientId) {
        String hashedPassword = passwordEncoder.encode(password);
        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                userId,
                hashedPassword, Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())));
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
        OAuth2Request oAuth2Request = createOAuth2Request(null, clientId,
                Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())),
                true, authenticatedClient.getScope(), null, null, null, null);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        return tokenServices.createAccessToken(oAuth2Authentication);
    }
    
    
}
