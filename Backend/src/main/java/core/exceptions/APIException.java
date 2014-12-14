package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }
    
}
