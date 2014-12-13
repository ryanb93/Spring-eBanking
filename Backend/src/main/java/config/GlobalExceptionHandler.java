package config;

import core.exceptions.InsufficientFundsException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Insufficient Funds In Account") 
    @ExceptionHandler(InsufficientFundsException.class)
    public void handleInsufficientFunds(HttpServletRequest request, Exception ex){
        logger.error("InsufficientFundsException Occured:: URL="+request.getRequestURL());
    }

}