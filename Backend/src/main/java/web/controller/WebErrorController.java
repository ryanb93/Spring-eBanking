package web.controller;

import config.Routes;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller for handling errors on the web interface.
 */
@Controller
public class WebErrorController implements ErrorController {

    /**
     * Shows the 404 error page to the user instead of the default page.
     * 
     * @param req - The request object.
     * @param resp - The response object.
     * @return A Model and View.
     * @throws IOException 
     */
    @RequestMapping(value = Routes.ERROR)
    public ModelAndView errorPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView errorPage = new ModelAndView("errorPage");
        errorPage.addObject("status", resp.getStatus());
        return errorPage;    
    }

    @Override
    public String getErrorPath() {
        return Routes.ERROR;
    }
}