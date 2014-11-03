package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView login(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        ModelAndView modelAndView = new ModelAndView("login", "model", null);
        return modelAndView;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String doLogin(@RequestParam(value = "username") String username, @RequestParam(value="password") String password) {
        return "Username: " + username + " Password: " + password;
    }

    
}
