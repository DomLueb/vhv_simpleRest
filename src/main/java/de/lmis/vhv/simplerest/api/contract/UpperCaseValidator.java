package de.lmis.vhv.simplerest.api.contract;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCase, String> {
    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        return object.equals( object.toUpperCase() );
    }
}
