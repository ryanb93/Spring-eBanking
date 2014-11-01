package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminPanelController {
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView admin(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        ModelAndView modelAndView = new ModelAndView("adminPanel", "model", null);
        return modelAndView;
    }

    @RequestMapping("/addCustomer")
    public ModelAndView addCustomer(){
        //Add Customer in here, then return a default view
    ModelAndView modelAndView = new ModelAndView("adminPanel", "model", null);
    return modelAndView;
    };
    
    
}