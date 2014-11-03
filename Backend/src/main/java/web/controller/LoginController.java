package web.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import web.domain.ApiUser;
import web.domain.Role;
import web.events.users.CreateUserRequest;
import web.events.users.CreateUserResponse;
import web.services.UserService;

@Controller
@RequestMapping("/oauth/users")
public class LoginController {
    
    private final UserService userService;
    private final DefaultTokenServices tokenServices;
    private final PasswordEncoder passwordEncoder;
    private final ClientDetailsService clientDetailsService;
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(final UserService userService,
                        final DefaultTokenServices defaultTokenServices,
                        final PasswordEncoder passwordEncoder, ClientDetailsService clientDetailsService) {
        this.userService = userService;
        this.tokenServices = defaultTokenServices;
        this.passwordEncoder = passwordEncoder;
        this.clientDetailsService = clientDetailsService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("index.html", "model", null);
        return modelAndView;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CreateUserRequest> signupUser(@RequestBody CreateUserRequest request) {
        ApiUser user = userService.createUser(request);
        String id = user.getId();
        String password = request.getPassword().getPassword();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String name = auth.getName();
        OAuth2AccessToken token = createTokenForNewUser(id, password, name);
        CreateUserResponse createUserResponse = new CreateUserResponse(user, token);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity(createUserResponse, headers, status);
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
    
    private OAuth2Request createOAuth2Request(Map<String, String> requestParameters, String clientId,
                                              Collection<? extends GrantedAuthority> authorities, boolean approved, Collection<String> scope,
                                              Set<String> resourceIds, String redirectUri, Set<String> responseTypes,
                                              Map<String, Serializable> extensionProperties) {
        return new OAuth2Request(requestParameters, clientId, authorities, approved, scope == null ? null
                : new LinkedHashSet<String>(scope), resourceIds, redirectUri, responseTypes, extensionProperties);
    }

    
    
}
