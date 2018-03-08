package com.lovatsis.sandbox.examples.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<URL, String> {
    private String protocol;

    public void initialize(URL url) {
        this.protocol = url.protocol();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        // Checks if the protocol attribute has a valid value
        if (protocol != null && protocol.length() > 0 && !value.contains(protocol)) {
            return false;
        }
        return true;
    }
}
