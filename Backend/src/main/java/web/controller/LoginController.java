package web.controller;

import config.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller simply serves the Login JSP
 * All logic for logging in is handled in JavaScript as it is much 
 * simpler to post to our OAuth API that way
 */
@Controller
@RequestMapping(Routes.LOGIN)
public class LoginController {
    
    /**
     * Serves up the Login JSP
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("login", "model", null);
        return modelAndView;
    }
}
