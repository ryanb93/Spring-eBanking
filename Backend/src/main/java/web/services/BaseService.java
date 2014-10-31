package web.services;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public abstract class BaseService {

    protected Validator validator;

    public BaseService(Validator validator) {
        this.validator = validator;
    }

    protected void validate(Object request) {
        Set<? extends ConstraintViolation<?>> constraintViolations = validator.validate(request);
//        if (constraintViolations.size() > 0) {
//            throw new ValidationException(constraintViolations);
//        }
    }

}
