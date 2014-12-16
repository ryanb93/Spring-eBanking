package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception object that is used by our application 
 * to throw error messages back to the API users. The exception takes
 * a message and returns the request as a BAD_REQUEST.
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class APIException extends Exception {

    /**
     * Constructor for the APIException, takes a message.
     * 
     * @param message - The message to show the user.
     */
    public APIException(String message) {
        super(message);
    }
    
}
